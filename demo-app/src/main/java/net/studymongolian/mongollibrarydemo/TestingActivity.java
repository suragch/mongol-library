package net.studymongolian.mongollibrarydemo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;


public class TestingActivity extends AppCompatActivity {

    MongolEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        editText = findViewById(R.id.editText);

    }


    public void onButtonClick(View view) {

        if (editText.isCursorVisible()) {
            editText.setCursorVisible(false);
        } else {
            editText.setCursorVisible(true);
        }
    }

    public void onCopyButtonClick(View view) {
        String text = editText.getText().toString();

        int startSelection=editText.getSelectionStart();
        int endSelection=editText.getSelectionEnd();
        String selectedText = editText.getText().toString().substring(startSelection, endSelection);
        if (!TextUtils.isEmpty(selectedText)) {
            text = selectedText;
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", text);
        clipboard.setPrimaryClip(clip);
    }
}
