package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardCyrillic;
import net.studymongolian.mongollibrary.KeyboardEnglish;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;


public class KeyboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadKeyboardsProgrammatically();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_keyboard_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_from_xml:
                loadKeyboardsFromXml();
                return true;
            case R.id.action_from_code:
                loadKeyboardsProgrammatically();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadKeyboardsFromXml() {
        // set content view without preloaded keyboards
        setContentView(R.layout.activity_keyboard_customized);

        // initialize views
        EditText editText = findViewById(R.id.edittext);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);
        ImeContainer imeContainer = findViewById(R.id.ime_container);

        // connect keyboards and editors
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(editText);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
    }

    // programmatically loaded keyboards will have the default style
    private void loadKeyboardsProgrammatically() {

        // set content view without preloaded keyboards
        setContentView(R.layout.activity_keyboard);

        // initialize editors
        EditText editText = findViewById(R.id.edittext);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // keyboards to include (default style)
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);
        Keyboard english = new KeyboardEnglish(this);
        Keyboard cyrillic = new KeyboardCyrillic(this);
        Keyboard custom = new CustomKeyboard(this);

        // add keyboards to the IME container
        ImeContainer imeContainer = findViewById(R.id.ime_container);
        imeContainer.addKeyboard(aeiou); // first one is the default
        imeContainer.addKeyboard(qwerty);
        imeContainer.addKeyboard(english);
        imeContainer.addKeyboard(cyrillic);
        imeContainer.addKeyboard(custom);

        // connect keyboards and editors
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(editText);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
    }


}
