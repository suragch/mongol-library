package net.studymongolian.mongollibrary;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;

// MongolEditText Input Connection
// Allows the MongolEditText to receive input from system keyboards

class MetInputConnection extends BaseInputConnection {

    private MongolEditText mMongolEditText;

    MetInputConnection(View targetView, boolean fullEditor) {
        super(targetView, fullEditor);
        if (!(targetView instanceof MongolEditText)) {
            throw new RuntimeException("MetInputConnection is only set up to work with a MongolEditText, " +
                    "not with " + targetView);
        }
        mMongolEditText = (MongolEditText) targetView;
    }

    @Override
    public Editable getEditable() {
        return mMongolEditText.getText();
    }

    @Override
    public boolean beginBatchEdit() {
        return mMongolEditText != null && mMongolEditText.beginBatchEdit();
    }

    @Override
    public boolean endBatchEdit() {
        return mMongolEditText != null && mMongolEditText.endBatchEdit();
    }

    @Override
    public void closeConnection() {
        // trying to apply docs here. Is this right?
        // see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#closeConnection()
        super.closeConnection();
        mMongolEditText.ensureEndedBatchEdit();
    }

    @Override
    public boolean performContextMenuAction(int id) {
        switch (id) {
            case android.R.id.copy:
                return copySelectedTextToClipboard();
            case android.R.id.cut:
                return cutSelectedText();
            case android.R.id.paste:
                return pasteTextFromClipboard();
        }
        return false;
    }

    private boolean copySelectedTextToClipboard() {
        CharSequence selectedText = mMongolEditText.getSelectedText();
        Context context = mMongolEditText.getContext();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("MongolEditText", selectedText);
        if (clipboard == null) return false;
        clipboard.setPrimaryClip(clip);
        return true;
    }

    private boolean cutSelectedText() {
        boolean copiedSuccessfully = copySelectedTextToClipboard();
        if (copiedSuccessfully)
            commitText("", 1);
        return copiedSuccessfully;
    }

    private boolean pasteTextFromClipboard() {
        Context context = mMongolEditText.getContext();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null) return false;
        CharSequence text = clipboard.getPrimaryClip().getItemAt(0).getText();
        if (text == null) return false;
        commitText(text, 1);
        return true;
    }


    // TODO commitCompletion: after adding completion choices
    // https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#commitCompletion(android.view.inputmethod.CompletionInfo)
    // TODO commitCorrection: add spell correction at some future date
    // TODO? performEditorAction
}
