package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        // load keyboards into IME container (choose one of the following two methods)
        ImeContainer imeContainer = loadKeyboardsFromXml();
        //ImeContainer imeContainer = loadKeyboardsProgrammatically();

        EditText editText = findViewById(R.id.edittext);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // The MongolInputMethodManager handles communication between the keyboards and
        // the MongolEditText (or EditText).
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(editText);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
    }

    private ImeContainer loadKeyboardsFromXml() {
        setContentView(R.layout.activity_keyboard_customized);
        return findViewById(R.id.ime_container);
    }

    // programmatically loaded keyboards will have the default style
    private ImeContainer loadKeyboardsProgrammatically() {

        // set content view without preloaded keyboards
        setContentView(R.layout.activity_keyboard);

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

        return imeContainer;
    }


}
