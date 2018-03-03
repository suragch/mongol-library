package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyboardCandidateActivity extends AppCompatActivity implements ImeContainer.InputListener {

    ImeContainer imeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_candidate);

        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // keyboards to include (default style)
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);

        // request a candidates view for each keyboard (default is NONE)
        aeiou.setCandidatesPreference(Keyboard.CandidatesPreference.VERTICAL_LEFT);
        qwerty.setCandidatesPreference(Keyboard.CandidatesPreference.HORIZONTAL_TOP);

        // add keyboards to the IME container
        imeContainer = findViewById(R.id.keyboard);
        imeContainer.addKeyboard(aeiou); // first one is the default
        imeContainer.addKeyboard(qwerty);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
        mimm.startInput();
    }

    @Override
    public void onImeInput(String currentWord) {
        List<String> candidates = getCandidatesStartingWith(currentWord);
        imeContainer.updateCandidateWordList(candidates);
    }

    private List<String> getCandidatesStartingWith(String currentWord) {
        return Collections.emptyList();
    }

    public void onGiveKeyboardCandidatesButtonClick(View view) {
        List<String> animalNames = new ArrayList<>();
        animalNames.add("ᠮᠣᠷᠢ");
        animalNames.add("ᠦᠬᠡᠷ");
        animalNames.add("ᠲᠡᠮᠡᠭᠡ");
        animalNames.add("ᠬᠣᠨᠢ");
        animalNames.add("ᠢᠮᠠᠭ᠎ᠠ");
        imeContainer.updateCandidateWordList(animalNames);
    }

    public void onVerticalButtonClick(View view) {


        // test data

    }

    public void onHorizontalButtonClick(View view) {
//        List<String> animalNames = new ArrayList<>();
//        animalNames.add("Lion");
//        animalNames.add("Giraffe");
//        animalNames.add("Bear");
//        imeContainer.updateCandidateWordList(animalNames);
    }
}