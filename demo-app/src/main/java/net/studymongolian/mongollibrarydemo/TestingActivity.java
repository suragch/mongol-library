package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolTextView;


public class TestingActivity extends AppCompatActivity {

    KeyText key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        key = (KeyText) findViewById(R.id.textKey);

    }

    public void buttonClick(View view) {
        //textView.setText("hello");
        //textView.setTextSize(30);

        //testRendering();
        key.invalidate();
    }


}
