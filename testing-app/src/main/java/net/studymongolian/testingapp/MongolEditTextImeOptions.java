package net.studymongolian.testingapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import net.studymongolian.mongollibrary.MongolEditText;

public class MongolEditTextImeOptions extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_edit_text_ime_options);


        EditText etSearch = findViewById(R.id.et_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(MongolEditTextImeOptions.this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        MongolEditText metSearch = findViewById(R.id.met_search);
        metSearch.setOnEditorActionListener(new MongolEditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(MongolEditText v, int actionId, KeyEvent event) {
                Toast.makeText(MongolEditTextImeOptions.this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


}
