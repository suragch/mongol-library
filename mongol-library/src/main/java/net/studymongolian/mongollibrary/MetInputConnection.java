package net.studymongolian.mongollibrary;

import android.text.Editable;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;


// MongolEditText Input Connection
// Allows the MongolEditText to receive input from system keyboards

class MetInputConnection extends BaseInputConnection {

    private MongolEditText mMongolEditText;
    // private Editable mMongolTextStorage;

    MetInputConnection(View targetView, boolean fullEditor) {
        super(targetView, fullEditor);
        mMongolEditText = (MongolEditText) targetView;
        //mMongolTextStorage = mongolEditText.getTextStorage();
        // mongolEditText = (MongolEditText) targetView;
    }

    @Override
    public Editable getEditable() {
        // TODO should I just return getText? Do glyphs get updated?
        return mMongolEditText.getText();
    }

//    @Override
//    public boolean commitText(CharSequence text, int newCursorPosition) {
//        // TODO what do I need to do here?
//        //mongolEditText.insertText(text);
//        mMongolEditText.textChanged();
//        return super.commitText(text, newCursorPosition);
//    }

    // TODO override more methods. see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html
    // https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/view/inputmethod/BaseInputConnection.java


    // more help http://stackoverflow.com/a/5684661/3681880
}
