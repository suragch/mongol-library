package net.studymongolian.mongollibrarydemo;

import android.inputmethodservice.InputMethodService;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.PopupKeyCandidate;

import java.util.List;

public class KeyboardInputMethodService extends InputMethodService implements Keyboard.OnKeyboardListener {

    private static final int MAX_CHARS_BEFORE_CURSOR = 128;

    @Override
    public View onCreateInputView() {
        Keyboard customKeyboard = new CustomKeyboard(this);
        customKeyboard.setOnKeyboardListener(this);
        return customKeyboard;
    }

    // Keyboard.OnKeyboardListener methods

    @Override
    public List<PopupKeyCandidate> getAllKeyboardNames() {
        // only one keyboard
        return null;
    }

    @Override
    public void onRequestNewKeyboard(String keyboardDisplayName) {
        // show other system keyboards
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showInputMethodPicker();
    }

    @Override
    public void onKeyboardInput(String text) {
        if (TextUtils.isEmpty(text)) return;
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        ic.commitText(text, 1);
    }

    @Override
    public void onKeyPopupChosen(PopupKeyCandidate choice) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        if (choice == null) return;
        String unicode = choice.getUnicode();
        onKeyboardInput(unicode);
    }

    @Override
    public void onBackspace() {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
    }

    @Override
    public CharSequence getTextBeforeCursor(int numberOfChars) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return "";
        return ic.getTextBeforeCursor(numberOfChars, 0);
    }

    @Override
    public CharSequence getTextAfterCursor(int numberOfChars) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return "";
        return ic.getTextAfterCursor(numberOfChars, 0);
    }

    @Override
    public String getPreviousMongolWord(boolean allowSingleSpaceBeforeCursor) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return "";
        CharSequence previous = ic.getTextBeforeCursor(MAX_CHARS_BEFORE_CURSOR, 0);
        if (TextUtils.isEmpty(previous)) return "";
        int endIndex = previous.length();
        if (allowSingleSpaceBeforeCursor && endsWithSpace(previous)) {
            endIndex--;
        }
        int startIndex = getStartIndex(endIndex, previous);
        return previous.subSequence(startIndex, endIndex).toString();
    }

    private boolean endsWithSpace(CharSequence text) {
        int length = text.length();
        if (length < 1) return false;
        char lastChar = text.charAt(length - 1);
        return (lastChar == ' ' || lastChar == MongolCode.Uni.NNBS);
    }

    private int getStartIndex(int endIndex, CharSequence previous) {
        int startIndex = endIndex;
        for (int i = endIndex - 1; i >= 0; i--) {
            char previousChar = previous.charAt(i);
            if (MongolCode.isMongolian(previousChar)) {
                startIndex = i;
            } else if (previousChar == MongolCode.Uni.NNBS) {
                startIndex = i;
                break;
            } else {
                break;
            }
        }
        return startIndex;
    }
}