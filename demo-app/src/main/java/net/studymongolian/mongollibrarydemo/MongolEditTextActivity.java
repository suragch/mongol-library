package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.studymongolian.mongollibrary.MongolEditText;


public class MongolEditTextActivity extends AppCompatActivity {

    MongolEditText metDemoEditText;

    private static final String[] SAMPLE_TEXT = {"ᠨᠢᠭᠡ", "ᠬᠣᠶᠠᠷ", "ᠭᠣᠷᠪᠠ", "ᠳᠥᠷᠪᠡ", "ᠲᠠᠪᠤ", "ᠵᠢᠷᠭᠤᠭ᠎ᠠ", "ᠳᠣᠯᠣᠭ᠎ᠠ", "ᠨᠠ‍ᠢᠮᠠ", "ᠶᠢᠰᠦ", "ᠠᠷᠪᠠ"};
    private int mSampleTextIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_edittext);

        metDemoEditText = (MongolEditText) findViewById(R.id.metExample);
    }

    public void inputTextClick(View view) {

        // get a sample text string to insert
        String sample = SAMPLE_TEXT[mSampleTextIndex];
        mSampleTextIndex++;
        if (mSampleTextIndex >= SAMPLE_TEXT.length) mSampleTextIndex = 0;

        // insert text padded with spaces
        metDemoEditText.insertText(sample + " ");
    }

    public void deleteClick(View view) {
    }
}
