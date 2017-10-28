package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;


public class KeyboardActivity extends AppCompatActivity {

    //EditText editText;
    //MongolEditText mongolEditText;
    //KeyboardAeiou keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        EditText editText = findViewById(R.id.edittext);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // keyboards to include
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);

        // add keyboards to the IME container and apply any styles
        ImeContainer imeContainer = findViewById(R.id.keyboard);
        ImeContainer.Builder builder = new ImeContainer.Builder(this, qwerty);
        builder.addKeyboard(aeiou);
        imeContainer.apply(builder);

        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(editText);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
        mimm.startInput();





        // prevent system keyboard from appearing when EditText is tapped
        //int inputType = editText.getInputType();
        //editText.setRawInputType(inputType);
        //editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //editText.setTextIsSelectable(true);

        // prevent system keyboard from appearing when MongolEditText is tapped
        //mongolEditText.setAllowSystemKeyboard(false);

        // get the input connection from the currently focused edit text
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
//                    keyboard.setInputConnection(ic);
//                }
//            }
//        });
//        mongolEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    InputConnection ic = mongolEditText.onCreateInputConnection(new EditorInfo());
//                    keyboard.setInputConnection(ic);
//                }
//            }
//        });





    }
}
