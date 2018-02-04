package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;


public class TestingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


    }


    public void onButtonClick(View view) {
        String unicode = "ᠰ \u200Dᠰ";
        String text = MongolCode.INSTANCE.unicodeToMenksoft(unicode);
        MongolEditText editText = findViewById(R.id.editText);
        editText.setText(unicode);
    }
}
