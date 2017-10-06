package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import net.studymongolian.mongollibrary.MongolEditText;


public class MongolEditTextActivity extends AppCompatActivity {

    MongolEditText metDemoEditText;

    private static final String[] SAMPLE_TEXT = {"ᠨᠢᠭᠡ", "ᠬᠣᠶᠠᠷ", "ᠭᠣᠷᠪᠠ", "ᠳᠥᠷᠪᠡ", "ᠲᠠᠪᠤ", "ᠵᠢᠷᠭᠤᠭ᠎ᠠ", "ᠳᠣᠯᠣᠭ᠎ᠠ", "ᠨᠠ‍ᠢᠮᠠ", "ᠶᠢᠰᠦ", "ᠠᠷᠪᠠ"};
    private int mSampleTextIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_edittext);

        metDemoEditText = findViewById(R.id.metExample);
    }

    public void inputTextClick(View view) {

        // get a sample text string to insert
        String sample = SAMPLE_TEXT[mSampleTextIndex];
        mSampleTextIndex++;
        if (mSampleTextIndex >= SAMPLE_TEXT.length) mSampleTextIndex = 0;

        // insert text padded with a space
        int start = Math.max(metDemoEditText.getSelectionStart(), 0);
        int end = Math.max(metDemoEditText.getSelectionEnd(), 0);
        if (start != end) {
            metDemoEditText.getText().delete(start, end);
        }
        metDemoEditText.getText().insert(start, sample + " ");
    }

    public void deleteClick(View view) {
        int start = Math.max(metDemoEditText.getSelectionStart(), 0);
        int end = Math.max(metDemoEditText.getSelectionEnd(), 0);
        if (start == end) {
            if (start > 0) {
                metDemoEditText.getText().delete(start - 1, start);
            }
        } else {
            metDemoEditText.getText().delete(start, end);
        }
    }

    public void keyboardClick(View view) {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showInputMethodPicker();
    }


    public void selectTextClick(View view) {
        metDemoEditText.selectAll();
    }
}
