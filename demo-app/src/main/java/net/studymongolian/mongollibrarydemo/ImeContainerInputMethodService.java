package net.studymongolian.mongollibrarydemo;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardCyrillic;
import net.studymongolian.mongollibrary.KeyboardEnglish;
import net.studymongolian.mongollibrary.KeyboardQwerty;

public class ImeContainerInputMethodService extends InputMethodService implements ImeContainer.OnSystemImeListener {

    @Override
    public View onCreateInputView() {
        ImeContainer ime = new ImeContainer(this);
        ime.setBackgroundColor(Color.BLACK);
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);
        Keyboard english = new KeyboardEnglish(this);
        Keyboard cyrillic = new KeyboardCyrillic(this);
        Keyboard ipa = new CustomKeyboardTwo(this);
        ime.addKeyboard(aeiou);
        ime.addKeyboard(qwerty);
        ime.addKeyboard(english);
        ime.addKeyboard(cyrillic);
        ime.addKeyboard(ipa);
        ime.showSystemKeyboardsOption("ᠰᠢᠰᠲ᠋ᠧᠮ");
        ime.setOnSystemImeListener(this);
        return ime;
    }

    @Override
    public InputConnection getInputConnection() {
        return getCurrentInputConnection();
    }

    @Override
    public void onChooseNewSystemKeyboard() {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showInputMethodPicker();
    }
}
