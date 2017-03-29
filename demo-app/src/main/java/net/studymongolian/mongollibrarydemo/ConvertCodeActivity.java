package net.studymongolian.mongollibrarydemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import net.studymongolian.mongollibrary.MongolCode;


public class ConvertCodeActivity extends AppCompatActivity {

    EditText etCodeWindow;
    MongolCode converter;
    Typeface tfMongolFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_code);

        // initialize renderer
        converter = MongolCode.INSTANCE;

        etCodeWindow = (EditText) findViewById(R.id.etCodeWindow);
        etCodeWindow.setText("ᠮᠤᠩᠭᠤᠯ"); // Mongol

        // set Mongol font
        //tfMongolFont = Typeface.createFromAsset(this.getAssets(), "fonts/MQG8F02.ttf");
        tfMongolFont = Typeface.createFromAsset(this.getAssets(), "fonts/MenksoftHawang.ttf");
        //etCodeWindow.setTypeface(tf);


    }

    // Button click methods

    public void unicodeToMenksoftClick(View view) {
        hideKeyboard();

        String menksoftString = converter.unicodeToMenksoft(etCodeWindow.getText().toString());

        etCodeWindow.setTypeface(tfMongolFont);
        etCodeWindow.setText(menksoftString);
    }

    public void menksoftToUnicodeClick(View view) {
        hideKeyboard();

        String unicodeString = converter.menksoftToUnicode(etCodeWindow.getText().toString());
        etCodeWindow.setText(unicodeString);
        etCodeWindow.setTypeface(null, Typeface.NORMAL);
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void copyClick(View view) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(etCodeWindow.getText());
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Mongol", etCodeWindow.getText());
            clipboard.setPrimaryClip(clip);
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void pasteClick(View view) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        CharSequence pasteString = "";
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            try {
                pasteString = clipboard.getText();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            if (clipboard.getPrimaryClip() != null) {
                android.content.ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                pasteString = item.getText();
            }

        }
        if (pasteString != null) {
            int start = Math.max(etCodeWindow.getSelectionStart(), 0);
            int end = Math.max(etCodeWindow.getSelectionEnd(), 0);
            etCodeWindow.getText().replace(Math.min(start, end), Math.max(start, end),
                    pasteString, 0, pasteString.length());
        }
    }

    public void clearClick(View view) {
        etCodeWindow.setText("");
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


}
