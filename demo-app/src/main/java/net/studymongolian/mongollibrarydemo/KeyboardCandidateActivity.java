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
import java.util.List;

public class KeyboardCandidateActivity extends AppCompatActivity implements ImeContainer.DataSource {

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

        // provide words for candidate selection
        imeContainer.setDataSource(this);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
        mimm.startInput();

        mongolEditText.requestFocus(); // FIXME is this a bug to need to explicitly request focus?
    }

    public void onGiveKeyboardCandidatesButtonClick(View view) {

    }

    public void onVerticalButtonClick(View view) {

    }

    public void onHorizontalButtonClick(View view) {

    }


    // ImeContainer.DataSource methods

    @Override
    public List<String> onRequestWordsStartingWith(String text) {
        // This is a dummy list.
        // In a production app you would use `text` to look up words
        // from a database.
        List<String> animalNames = new ArrayList<>();
        animalNames.add(text);
        animalNames.add("ᠮᠣᠷᠢ");
        animalNames.add("ᠦᠬᠡᠷ");
        animalNames.add("ᠲᠡᠮᠡᠭᠡ");
        return animalNames;
    }

    @Override
    public List<String> onRequestWordsFollowing(String word) {
        // This is a dummy list.
        // In a production app you would use `word` to look up in a database others words
        // that could follow `word`.
        List<String> animalNames = new ArrayList<>();
        animalNames.add("ᠬᠣᠨᠢ");
        animalNames.add("ᠢᠮᠠᠭ᠎ᠠ");
        return animalNames;
    }
}