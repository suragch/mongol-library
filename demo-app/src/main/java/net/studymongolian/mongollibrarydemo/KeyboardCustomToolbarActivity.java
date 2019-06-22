package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;

public class KeyboardCustomToolbarActivity extends AppCompatActivity {

    ImeContainer imeContainer;
    MongolEditText mongolEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_custom_toolbar);

        imeContainer = findViewById(R.id.ime_container);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mongolEditText = findViewById(R.id.mongoledittext);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);

        mongolEditText.requestFocus();

        // need to also disallow system keyboard in Manifest.xml
        // android:windowSoftInputMode="stateHidden" // todo make this unnecessary
    }

}