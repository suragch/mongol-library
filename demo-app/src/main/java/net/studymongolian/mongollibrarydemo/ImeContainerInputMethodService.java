package net.studymongolian.mongollibrarydemo;

import android.graphics.Color;
import android.inputmethodservice.ExtractEditText;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardCyrillic;
import net.studymongolian.mongollibrary.KeyboardLatin;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolFont;

public class ImeContainerInputMethodService extends InputMethodService
        implements ImeContainer.OnSystemImeListener {

    ImeContainer ime;

    @Override
    public View onCreateInputView() {
        ime = new ImeContainer(this);
        ime.setBackgroundColor(Color.BLACK);
        Keyboard aeiou = new KeyboardAeiou(this);
        aeiou.setCandidatesLocation(Keyboard.CandidatesLocation.VERTICAL_LEFT);
        Keyboard qwerty = new KeyboardQwerty(this);
        Keyboard english = new KeyboardLatin(this);
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
    public void onComputeInsets(InputMethodService.Insets outInsets) {
        super.onComputeInsets(outInsets);

        // This gives an invisible padding at the top so that key popups will show in API 28+
        // Touch events on this padding are passed on to whatever views are below it.
        outInsets.visibleTopInsets = ime.getVisibleTop();
        outInsets.contentTopInsets = ime.getVisibleTop();
    }

    @Override
    public InputConnection getInputConnection() {
        return getCurrentInputConnection();
    }

    @Override
    public void onSystemKeyboardRequest() {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showInputMethodPicker();
    }

    @Override
    public void onHideKeyboardRequest() {
        requestHideSelf(0);
    }

    @Override
    public View onCreateExtractTextView() {
        View extractedView = super.onCreateExtractTextView();
        ExtractEditText editText = extractedView.findViewById(android.R.id.inputExtractEditText);
        editText.setTypeface(MongolFont.get(MongolFont.QAGAN, getApplicationContext()));
        return extractedView;
    }
}
