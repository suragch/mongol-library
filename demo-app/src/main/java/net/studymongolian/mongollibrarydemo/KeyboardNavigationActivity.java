package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;

public class KeyboardNavigationActivity extends AppCompatActivity
        implements ImeContainer.OnNonSystemImeListener {

    ImeContainer imeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_navigation);

        imeContainer = findViewById(R.id.ime_container);
        imeContainer.setOnNonSystemImeListener(this);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);
        EditText editText = findViewById(R.id.edittext);
        mimm.addEditor(mongolEditText);
        mimm.addEditor(editText);
        mimm.setIme(imeContainer);

        editText.setText(getString(R.string.lorem_ipsum));
        mongolEditText.setText(getString(R.string.long_string));

        mongolEditText.requestFocus();

        showNavigationView();
    }

    private void showNavigationView() {
        imeContainer.post(new Runnable() {
            @Override
            public void run() {
                imeContainer.toggleNavigationView();
            }
        });
    }


    @Override
    public void onHideKeyboardRequest() {
        imeContainer.setVisibility(View.GONE);
    }

    public void onShowKeyboardButtonClick(View view) {
        imeContainer.setVisibility(View.VISIBLE);
    }
}
