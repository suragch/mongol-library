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

public class MongolInputMethodManager {

    public static final int NO_EDITORS = 0;
    public static final int ALL_EDITORS = 1;
    public static final int SYSTEM_EDITOR_ONLY = 2;
    public static final int MONGOL_EDITOR_ONLY = 3;

    private static final boolean DEBUG = false;
    private static final String TAG = "MongolImeManager";

    public MongolInputMethodManager() {}

    private List<View> mRegisteredEditors;
    private ImeContainer mImeContainer;
    private int mAllowSystemKeyboard = NO_EDITORS;

    // this is the edit text that is receiving input
    private View mCurrentEditor;
    private EditorInfo mCurrentEditorInfo;
    private int mCursorSelStart;
    private int mCursorSelEnd;
    private int mCursorCandidateStart;
    private int mCursorCandidateEnd;

    public void setAllowSystemSoftInput(int allowSystemKeyboard) {
        this.mAllowSystemKeyboard = allowSystemKeyboard;

        if (mRegisteredEditors == null || mRegisteredEditors.size() == 0) return;

        for (View editor : mRegisteredEditors) {
            if (editor instanceof EditText) {
                EditText editText = (EditText) editor;

                // TODO this needs to be tested on lower versions!
                // https://stackoverflow.com/a/45229457

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
            } else if (editor instanceof MongolEditText) {
                boolean showSystemSoftIme =
                        allowSystemKeyboard == ALL_EDITORS ||
                        allowSystemKeyboard == MONGOL_EDITOR_ONLY;
                ((MongolEditText) editor).setAllowSystemKeyboard(showSystemSoftIme);
                if (!showSystemSoftIme) {
                    Activity activity = getActivity(editor.getContext());
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

    public void addEditor(View editor) {

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
        for (View view : mRegisteredEditors) {
            if (view == editor) return;
        }

        // give the editor's input connection to the keyboard when editor is focused
        editor.setOnFocusChangeListener(focusListener);
        // TODO if hiding the keyboard on back button then may need to add a touch listener to edit texts too

        // get extra updates from MongolEditText
        // TODO is there any way for us to get these updates from EditText?
        if (editor instanceof MongolEditText) {
            ((MongolEditText) editor).setOnMongolEditTextUpdateListener(mongolEditTextListener);
        }

        // TODO set allow system keyboard to show if hasn't been set

        // add editor
        mRegisteredEditors.add(editor);
        mCurrentEditor = editor;
    }

    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                mCurrentEditor = v;
                EditorInfo tba = getEditorInfo(v);
                InputConnection ic = v.onCreateInputConnection(tba);
                mCurrentEditorInfo = tba;
                if (mImeContainer != null) {
                    mImeContainer.setInputConnection(ic);
                }

                if (mAllowSystemKeyboard == SYSTEM_EDITOR_ONLY
                        && v instanceof MongolEditText) {
                    InputMethodManager imm = (InputMethodManager)
                            v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    if (imm != null)
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
    };

    private EditorInfo getEditorInfo(View view) {
        EditorInfo editorInfo = new EditorInfo();
        editorInfo.packageName = view.getContext().getPackageName();
        editorInfo.fieldId = view.getId();
        return editorInfo;
    }

    private MongolEditText.OnMongolEditTextInputEventListener mongolEditTextListener =
            new MongolEditText.OnMongolEditTextInputEventListener() {

                // the editor needs to call this every time the selection changes
                // this method is an adaptation of Android source InputMethodManager#updateSelection
                @Override
                public void updateSelection(View view, int selStart, int selEnd,
                                            int candidatesStart, int candidatesEnd) {

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
            };

    public void setIme(ImeContainer imeContainer) {
        this.mImeContainer = imeContainer;
    }

}
