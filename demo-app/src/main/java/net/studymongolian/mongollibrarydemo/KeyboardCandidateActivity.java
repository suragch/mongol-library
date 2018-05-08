package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;
import net.studymongolian.mongollibrary.MongolToast;

import java.util.ArrayList;
import java.util.List;

public class KeyboardCandidateActivity extends AppCompatActivity implements ImeContainer.DataSource {

    ImeContainer imeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // select one of the two following methods to load the Keyboards
        //loadKeyboardsProgrammatically();
        loadKeyboardsFromXml();

        // provide words for candidate selection
        imeContainer.setDataSource(this);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);

        // FIXME is this a bug to need to explicitly request focus?
        mongolEditText.requestFocus();
    }

    private void loadKeyboardsFromXml() {
        setContentView(R.layout.activity_keyboard_candidate_customized);
        imeContainer = findViewById(R.id.ime_container);
    }

    // programmatically loaded keyboards will have the default style
    private void loadKeyboardsProgrammatically() {

        // set a content view without preloaded keyboards
        setContentView(R.layout.activity_keyboard_candidate);

        // keyboards to include (default style)
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);

        // request a candidates view for each keyboard (default is NONE)
        aeiou.setCandidatesLocation(Keyboard.CandidatesLocation.VERTICAL_LEFT);
        qwerty.setCandidatesLocation(Keyboard.CandidatesLocation.HORIZONTAL_TOP);

        // add keyboards to the IME container
        imeContainer = findViewById(R.id.ime_container);
        imeContainer.addKeyboard(aeiou);
        imeContainer.addKeyboard(qwerty);
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
        // In a production app you would use `word` to look up in a database other words
        // that could follow `word`.
        List<String> animalNames = new ArrayList<>();
        animalNames.add("ᠬᠣᠨᠢ");
        animalNames.add("ᠢᠮᠠᠭ᠎ᠠ");
        return animalNames;
    }

    @Override
    public void onCandidateLongClick(int position, String text) {
        MongolToast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}