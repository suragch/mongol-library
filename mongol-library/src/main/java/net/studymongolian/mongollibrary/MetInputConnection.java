package net.studymongolian.mongollibrary;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;

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
            case android.R.id.selectAll:
                return selectAll();
        }
        return false;
    }

    private boolean copySelectedTextToClipboard() {
        CharSequence selectedText = mMongolEditText.getSelectedText();
        if (TextUtils.isEmpty(selectedText))
            return false;
        Context context = mMongolEditText.getContext();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(null, selectedText);
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
        ClipData clip = clipboard.getPrimaryClip();
        if (clip == null) return false;
        ClipData.Item item = clip.getItemAt(0);
        if (item == null) return false;
        CharSequence text = item.getText();
        if (text == null) return false;
        commitText(text, 1);
        return true;
    }

    private boolean selectAll() {
        int length = mMongolEditText.getText().length();
        mMongolEditText.setSelection(0, length);
        return false;
    }

    @Override
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        if (request == null)
            return null;

        // TODO how to monitor extracted text?
        //if ((flags & GET_EXTRACTED_TEXT_MONITOR) != 0)
        //    mExtractedTextRequest = request;

        Editable editable = getEditable();
        if (editable == null) {
            return null;
        }
        int selStart = Selection.getSelectionStart(editable);
        int selEnd = Selection.getSelectionEnd(editable);

        ExtractedText extract = new ExtractedText();
        extract.flags = 0;
        extract.partialStartOffset = -1;
        extract.partialEndOffset = -1;
        extract.selectionStart = selStart;
        extract.selectionEnd = selEnd;
        extract.startOffset = 0;
        if ((request.flags & GET_TEXT_WITH_STYLES) != 0) {
            extract.text = new SpannableString(editable);
        } else {
            extract.text = editable.toString();
        }
        return extract;
    }

    @Override
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        return super.requestCursorUpdates(cursorUpdateMode);
    }

    // TODO commitCompletion: after adding completion choices
    // https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#commitCompletion(android.view.inputmethod.CompletionInfo)
    // TODO commitCorrection: add spell correction at some future date
    // TODO? performEditorAction
}
