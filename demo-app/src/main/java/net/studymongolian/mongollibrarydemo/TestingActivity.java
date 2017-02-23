package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.studymongolian.mongollibrary.MongolTextView;


public class TestingActivity extends AppCompatActivity {

    MongolTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        textView = (MongolTextView) findViewById(R.id.textingView);
    }

    public void buttonClick(View view) {
        //textView.setText("hello");
        textView.setTextSize(30);
    }
}
