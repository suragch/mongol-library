package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolTextView;


public class TestingActivity extends AppCompatActivity {

    MongolEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        editText = (MongolEditText) findViewById(R.id.metMongolWord);

    }

    public void buttonClick(View view) {
        //editText
        editText.setText("asdf");
        Log.i("TAG", "editText length: " + editText.getText().length());
    }


}
