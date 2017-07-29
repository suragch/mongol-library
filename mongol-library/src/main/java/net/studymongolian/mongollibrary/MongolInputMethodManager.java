package net.studymongolian.mongollibrary;


import android.graphics.Rect;
import android.os.Build;
import android.os.IBinder;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MongolInputMethodManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "MongolImeManager";

    //public final static MongolInputMethodManager INSTANCE = new MongolInputMethodManager();
    public MongolInputMethodManager() {
    }

    private List<View> mRegisteredEditors;
    //private List<View> mRegisteredKeyboards;
    private ImeContainer mImeContainer;
    private boolean mAllowSystemKeyboard = false;

    // this is the edit text that is receiving input
    private View mCurrentEditor;
    //private KeyboardAeiou mCurrentKeyboard; // TODO change to Keyboard
    private EditorInfo mCurrentEditorInfo;
    private InputConnection mCurrentInputConnection;
    // Cursor position on the screen.
    Rect mTmpCursorRect = new Rect();
    Rect mCursorRect = new Rect();
    int mCursorSelStart;
    int mCursorSelEnd;
    int mCursorCandStart;

    int mCursorCandEnd;



    public enum Ime {
        AEIOU,
        QWERTY,
        ENGLISH,
        CYRILLIC;

    }


    public void setAllowSystemKeyboard(boolean allowSystemKeyboard) {
        this.mAllowSystemKeyboard = allowSystemKeyboard;

        if (mRegisteredEditors == null || mRegisteredEditors.size() == 0) return;

        for (View editor : mRegisteredEditors) {
            if (editor instanceof EditText) {
                EditText editText = (EditText) editor;

                // TODO this needs to be tested on lower versions!
                // https://stackoverflow.com/a/45229457

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // api 21+
                    editText.setShowSoftInputOnFocus(allowSystemKeyboard);
                } else { // api 11+
                    if (allowSystemKeyboard) {
                        // re-enable keyboard (see https://stackoverflow.com/a/45228867)
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
                ((MongolEditText) editor).setAllowSystemKeyboard(allowSystemKeyboard);
            }
        }
    }


    public void setInputMethod(IBinder token, String id) {
        // TODO
    }

    // show the soft input IME (keyboard)
    // return: if the soft input was
    public boolean showSoftInput(View view, int flags) {
        // TODO not implemented
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L979
        return false;
    }

    public boolean hideSoftInput(int flags) {
        // TODO not implemented
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L1058
        return false;
    }

    public void hideSoftInputFromInputMethod(int flags) {

    }

    public void showSoftInputFromInputMethod(IBinder token, int flags) {

    }

    public InputMethodSubtype getCurrentInputMethodSubtype() {
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L2058
        return null;
    }

    public boolean setCurrentInputMethodSubtype(InputMethodSubtype subtype) {
        // todo
        return false;
    }

    public void toggleSoftInput(int showFlags, int hideFlags) {
        // TODO not implemented
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L1088
    }

    public boolean startInput() {

        if (mRegisteredEditors == null || mRegisteredEditors.size() == 0) {
            throw new RuntimeException("You must add at least one editor.");
        }

        if (mImeContainer == null) {
            //mCurrentInputMethod = (KeyboardAeiou) mRegisteredKeyboards.get(0);
            throw new RuntimeException("You must set the IME container.");
        }

        // mCurrentEditor will be added when it gets focus

        return true;
    }

    void finishInput() {
        // TODO what else do I need to do here? When do I call this?
        mCurrentEditor = null;
        mCurrentEditorInfo = null;
    }

    public void windowDismissed() {


        // todo


    }

    /**
     * Call this when a view receives focus.
     *
     * @hide
     */
    public void focusIn(View view) {
        // TODO
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L1345
    }

    /**
     * Call this when a view loses focus.
     *
     * @hide
     */
    public void focusOut(View view) {
        // TODO
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L1369
    }

    /**
     * Call this when a view is being detached from a {@link android.view.Window}.
     *
     * @hide
     */
    public void onViewDetachedFromWindow(View view) {
        // TODO
        // https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/inputmethod/InputMethodManager.java#L1392
    }

    /**
     * Notify the event when the user tapped or clicked the text view.
     */
    public void viewClicked(View view) {
        // TODO
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
                EditorInfo tba = new EditorInfo();
                tba.packageName = v.getContext().getPackageName();
                tba.fieldId = v.getId();
                InputConnection ic = v.onCreateInputConnection(tba);
                mCurrentEditorInfo = tba;
                if (mImeContainer != null) {
                    mImeContainer.setInputConnection(ic);
                }
            }
        }
    };

    private MongolEditText.OnMongolEditTextInputEventListener mongolEditTextListener =
            new MongolEditText.OnMongolEditTextInputEventListener() {

                // the editor needs to call this every time the selection changes
                // this method is an adaptation of Android source InputMethodManager#updateSelection
                @Override
                public void updateSelection(View view, int selStart, int selEnd,
                                            int candidatesStart, int candidatesEnd) {

                    //Log.i("TAG", "updateSelection: selection updated");
                    //if (mCurrentInputMethod == null) return;

                    if ((mCurrentEditor != view && mCurrentEditor == null)
                            || mCurrentEditorInfo == null || mImeContainer == null) {
                        return;
                    }

                    if (mCursorSelStart != selStart || mCursorSelEnd != selEnd
                            || mCursorCandStart != candidatesStart
                            || mCursorCandEnd != candidatesEnd) {

                        if (DEBUG) Log.v(TAG, "SELECTION CHANGE: " + mImeContainer);
                        final int oldSelStart = mCursorSelStart;
                        final int oldSelEnd = mCursorSelEnd;
                        mCursorSelStart = selStart;
                        mCursorSelEnd = selEnd;
                        mCursorCandStart = candidatesStart;
                        mCursorCandEnd = candidatesEnd;
                        mImeContainer.onUpdateSelection(oldSelStart, oldSelEnd,
                                selStart, selEnd, candidatesStart, candidatesEnd);
                    }

                }
            };


    // TODO
    private void addIme(Ime keyboard) {
        // Benefit of having this method: The developer would not have to
        // add anything special to the layout or create the keyboard beforehand.
        // May need to convert the keyboard to some sort of popup window
        // before we can do this. Because currently the keyboard needs to
        // be part of the activity layout.
        switch (keyboard) {
            case AEIOU:

                break;
            case QWERTY:

                break;
            case ENGLISH:

                break;
            case CYRILLIC:

                break;
        }
    }

//    public void addIme(KeyboardAeiou keyboard) { // TODO change this to Keyboard later
//        // TODO let the user add their own keyboard subclass
//        if (mRegisteredKeyboards == null) {
//            mRegisteredKeyboards = new ArrayList<>();
//        }
//        // don't add the same view twice
//        for (View view : mRegisteredKeyboards) {
//            if (view == keyboard) return;
//        }
//        // add keyboard
//        mRegisteredKeyboards.add(keyboard);
//    }

    public void setIme(ImeContainer imeContainer) {
        this.mImeContainer = imeContainer;
    }

}
