package net.studymongolian.mongollibrarydemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolInputMethodManager;
import net.studymongolian.mongollibrary.MongolTextView;

import java.io.File;


public class TestingActivity extends AppCompatActivity {

    MongolTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        textView = findViewById(R.id.test_mtv);
        textView.setText(" asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asd asdf" +
                "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asd asdf asdf asdf asdf asdf " +
                "asdf asdf asdf asdf asdf asdf asdf asd asdf asdf asdf asdf asdf asdf asdf asdf" +
                " asdf asdf asdf asdf asd asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf " +
                "asdf asd asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asd asdf " +
                "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asd asdf asdf asdf asdf" +
                " asdf asdf asdf asdf asdf asdf asdf asdf asd asdf asdf asdf asdf asdf asdf asdf" +
                " asdf asdf asdf asdf asdf asd asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf " +
                "asdf asdf asd asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asd " +
                "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asd");
    }


    public void onButtonClick(View view) {
        textView.setTextSize(56);
    }
}
