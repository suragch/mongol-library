package net.studymongolian.mongollibrary;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A replacement for InputMethodManager when using a custom in-app keyboard.
 * This class moves the input connection to the current editor.
 */
public class MongolInputMethodManager implements MongolEditText.OnMongolEditTextInputEventListener {

    /**
     * @deprecated Use addEditor(View editor, boolean allowSystemKeyboard) instead.
     * This will be removed in the future.
     */
    @Deprecated
    public static final int NO_EDITORS = 0;

    /**
     * @deprecated Use addEditor(View editor, boolean allowSystemKeyboard) instead.
     * This will be removed in the future.
     */
    @Deprecated
    public static final int ALL_EDITORS = 1;

    /**
     * @deprecated Use addEditor(View editor, boolean allowSystemKeyboard) instead.
     * This will be removed in the future.
     */
    @Deprecated
    public static final int SYSTEM_EDITOR_ONLY = 2;

    /**
     * @deprecated Use addEditor(View editor, boolean allowSystemKeyboard) instead.
     * This will be removed in the future.
     */
    @Deprecated
    public static final int MONGOL_EDITOR_ONLY = 3;

    private static final boolean DEBUG = false;
    private static final String TAG = "MongolImeManager";

    private List<RegisteredEditor> mRegisteredEditors;
    private ImeContainer mImeContainer;

    // this is the edit text that is receiving input
    private View mCurrentEditor;
    private EditorInfo mCurrentEditorInfo;
    private int mCursorSelStart;
    private int mCursorSelEnd;
    private int mCursorCandidateStart;
    private int mCursorCandidateEnd;

    /**
     * @deprecated Use addEditor(View editor, boolean allowSystemKeyboard) instead.
     * This method will be removed in the future.
     *
     * @param allowSystemKeyboard, when the system keyboard is allowed to popup
     */
    @Deprecated
    public void setAllowSystemSoftInput(int allowSystemKeyboard) {
        //this.mAllowSystemKeyboard = allowSystemKeyboard;

        if (mRegisteredEditors == null || mRegisteredEditors.size() == 0) return;

        for (RegisteredEditor editor : mRegisteredEditors) {
            if (editor.view instanceof EditText) {
                EditText editText = (EditText) editor.view;

                // TODO this needs to be tested on lower versions!
                // https://stackoverflow.com/a/45229457

                //noinspection deprecation // todo refactor so that we don't need it
                boolean showSystemSoftIme = allowSystemKeyboard == ALL_EDITORS ||
                        allowSystemKeyboard == SYSTEM_EDITOR_ONLY;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // api 21+
                    editText.setShowSoftInputOnFocus(showSystemSoftIme);
                } else { // api 11+
                    if (showSystemSoftIme) {
                        // re-enable keyboard (see https://stackoverflow.com/a/45228867)
                        // FIXME this does not necessarily always work
                        editText.setTextIsSelectable(false);
                        editText.setFocusable(true);
                        editText.setFocusableInTouchMode(true);
                        editText.setClickable(true);
                        editText.setLongClickable(true);
                        editText.setMovementMethod(ArrowKeyMovementMethod.getInstance());
                        editText.setText(editText.getText(), TextView.BufferType.SPANNABLE);
                    } else {
                        // disable keyboard
                        editText.setTextIsSelectable(true);
                    }
                }
            } else if (editor.view instanceof MongolEditText) {
                //noinspection deprecation // todo refactor so that we don't need it
                boolean showSystemSoftIme =
                        allowSystemKeyboard == ALL_EDITORS ||
                        allowSystemKeyboard == MONGOL_EDITOR_ONLY;
                ((MongolEditText) editor.view).setAllowSystemKeyboard(showSystemSoftIme);
                if (!showSystemSoftIme) {
                    Activity activity = getActivity(editor.view.getContext());
                    if (activity != null) {
                        // TODO do I need to do this for EditText also?
                        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    }
                }
            }
        }
    }

    private static Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper) return getActivity(((ContextWrapper)context).getBaseContext());
        return null;
    }

    /**
     * convenience method for addEditor(View editor, boolean allowSystemKeyboard)
     * allowSystemKeyboard defaults to false
     *
     * @param editor EditText or MongolEditText
     */
    public void addEditor(View editor) {
        addEditor(editor, false);
    }

    /**
     * Registers an editor to receive input from the custom keyboard
     *
     * @param editor EditText or MongolEditText
     * @param allowSystemKeyboard sets whether the system keyboard will popup when an editor is focused
     */
    @SuppressWarnings("WeakerAccess")
    public void addEditor(View editor, boolean allowSystemKeyboard) {

        // editor must be MongolEditText or EditText
        if (!(editor instanceof EditText) && !(editor instanceof MongolEditText)) {
            throw new RuntimeException("MongolInputMethodManager " +
                    "only supports adding a MongolEditText or EditText " +
                    "at this time. You added: " + editor);
        }

        if (mRegisteredEditors == null) {
            mRegisteredEditors = new ArrayList<>();
        }

        // don't add the same view twice
        for (RegisteredEditor item : mRegisteredEditors) {
            if (item.view == editor) return;
        }

        // give the editor's input connection to the keyboard when editor is focused
        editor.setOnFocusChangeListener(focusListener);
        // TODO if hiding the keyboard on back button then may need to add a touch listener to edit texts too

        // get extra updates from MongolEditText
        // TODO is there any way for us to get these updates from EditText?
        if (editor instanceof MongolEditText) {
            ((MongolEditText) editor).setOnMongolEditTextUpdateListener(this);
        }

        // TODO set allow system keyboard to show if hasn't been set
        setAllowSystemKeyboard(editor, allowSystemKeyboard);

        // add editor
        mRegisteredEditors.add(new RegisteredEditor(editor, allowSystemKeyboard));
        mCurrentEditor = editor;
    }

    private void setAllowSystemKeyboard(View editor, boolean allowSystemKeyboard) {
        if (editor instanceof EditText) {
            setAllowSystemKeyboardOnEditText((EditText) editor, allowSystemKeyboard);
        } else if (editor instanceof MongolEditText) {
            ((MongolEditText) editor).setAllowSystemKeyboard(allowSystemKeyboard);
        }
    }

    private void setAllowSystemKeyboardOnEditText(EditText editText, boolean allowSystemKeyboard) {
        // TODO this needs to be tested on lower versions!
        // https://stackoverflow.com/a/45229457

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // api 21+
            editText.setShowSoftInputOnFocus(allowSystemKeyboard);
        } else { // api 11+
            if (allowSystemKeyboard) {
                // re-enable keyboard (see https://stackoverflow.com/a/45228867)
                // FIXME this does not necessarily always work
                editText.setTextIsSelectable(false);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.setClickable(true);
                editText.setLongClickable(true);
                editText.setMovementMethod(ArrowKeyMovementMethod.getInstance());
                editText.setText(editText.getText(), TextView.BufferType.SPANNABLE);
            } else {
                // disable keyboard
                editText.setTextIsSelectable(true);
            }
        }
    }

    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                setInputConnection(v);
                hideSystemKeyboardIfNeeded(v);
            }
        }

        private void setInputConnection(View v) {
            mCurrentEditor = v;
            EditorInfo tba = getEditorInfo(v);
            InputConnection ic = v.onCreateInputConnection(tba);
            mCurrentEditorInfo = tba;
            if (mImeContainer != null) {
                mImeContainer.setInputConnection(ic);
                mImeContainer.onStartInput(tba, false);
            }
        }

        private void hideSystemKeyboardIfNeeded(View v) {
            for (RegisteredEditor item : mRegisteredEditors) {
                if (item.view == v) {
                    if (!item.allowSystemKeyboard)
                        hideSystemKeyboard(v);
                    break;
                }
            }
        }

        private void hideSystemKeyboard(View v) {
            InputMethodManager imm = (InputMethodManager)
                    v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    };

    private EditorInfo getEditorInfo(View view) {
        EditorInfo editorInfo = new EditorInfo();
        editorInfo.packageName = view.getContext().getPackageName();
        editorInfo.fieldId = view.getId();
        return editorInfo;
    }

    public void setIme(ImeContainer imeContainer) {
        this.mImeContainer = imeContainer;
    }

    @Override
    public void updateSelection(View view, int selStart, int selEnd, int candidatesStart, int candidatesEnd) {
        if ((mCurrentEditor != view && mCurrentEditor == null)
                || mCurrentEditorInfo == null || mImeContainer == null) {
            return;
        }

        if (mCursorSelStart != selStart || mCursorSelEnd != selEnd
                || mCursorCandidateStart != candidatesStart
                || mCursorCandidateEnd != candidatesEnd) {

            if (DEBUG) Log.v(TAG, "SELECTION CHANGE: " + mImeContainer);
            final int oldSelStart = mCursorSelStart;
            final int oldSelEnd = mCursorSelEnd;
            mCursorSelStart = selStart;
            mCursorSelEnd = selEnd;
            mCursorCandidateStart = candidatesStart;
            mCursorCandidateEnd = candidatesEnd;
            mImeContainer.onUpdateSelection(oldSelStart, oldSelEnd,
                    selStart, selEnd, candidatesStart, candidatesEnd);
        }
    }

    //@Override
    //public void updateExtractedText(View view, int token, ExtractedText text) {
    //    // TODO currently unimplemented since ImeContainer doesn't have an extracted text view
    //}

    private class RegisteredEditor {
        View view;
        boolean allowSystemKeyboard;

        RegisteredEditor(View editor, boolean allowSystemKeyboard) {
            this.view = editor;
            this.allowSystemKeyboard = allowSystemKeyboard;
        }
    }

}
