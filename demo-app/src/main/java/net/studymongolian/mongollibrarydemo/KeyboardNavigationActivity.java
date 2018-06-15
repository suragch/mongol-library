package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;

public class KeyboardNavigationActivity extends AppCompatActivity
        implements ImeContainer.OnNonSystemImeListener {

    private static final String LONG_TEXT = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ ᠳᠥᠷᠪᠡ ᠲᠠᠪᠤ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠳᠣᠯᠣᠭ᠎ᠠ ᠨᠠ\u200dᠢᠮᠠ ᠶᠢᠰᠦ ᠠᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠨᠢᠭᠡ ᠠᠷᠪᠠᠨ ᠬᠣᠶᠠᠷ ᠠᠷᠪᠠᠨ ᠭᠣᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠳᠥᠷᠪᠡ ᠠᠷᠪᠠᠨ ᠲᠠᠪᠤ ᠠᠷᠪᠠᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠨᠠ\u200dᠢᠮᠠ ᠠᠷᠪᠠ ᠶᠢᠰᠦ ᠬᠣᠷᠢ \uD83D\uDE42 ᠬᠣᠷᠢᠨ ᠨᠢᠭᠡ ᠬᠣᠷᠢᠨ ᠬᠣᠶᠠᠷ ᠬᠣᠷᠢᠨ ᠭᠣᠷᠪᠠ ᠬᠣᠷᠢᠨ ᠳᠥᠷᠪᠡ ᠬᠣᠷᠢᠨ ᠲᠠᠪᠤ ᠬᠣᠷᠢᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠨᠠ\u200dᠢᠮᠠ ᠬᠣᠷᠢ ᠶᠢᠰᠦ  ᠭᠣᠴᠢ one two three four five six seven eight nine ten 一二三四五六七八九十\uD83D\uDE03\uD83D\uDE0A\uD83D\uDE1C\uD83D\uDE01\uD83D\uDE2C\uD83D\uDE2E\uD83D\uDC34\uD83D\uDC02\uD83D\uDC2B\uD83D\uDC11\uD83D\uDC10ᆾ①②③㉑㊿〖汉字〗한국어モンゴル語English?︽ᠮᠣᠩᠭᠣᠯ︖︾";


    ImeContainer imeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_navigation);

        imeContainer = findViewById(R.id.ime_container);
        imeContainer.setOnNonSystemImeListener(this);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);
        EditText editText = findViewById(R.id.edittext);
        mimm.addEditor(mongolEditText);
        mimm.addEditor(editText);
        mimm.setIme(imeContainer);

        editText.setText(getString(R.string.lorem_ipsum));
        mongolEditText.setText(LONG_TEXT);

        mongolEditText.requestFocus();

        showNavigationView();
    }

    private void showNavigationView() {
        imeContainer.post(new Runnable() {
            @Override
            public void run() {
                imeContainer.toggleNavigationView();
            }
        });
    }


    @Override
    public void onHideKeyboardRequest() {
        imeContainer.setVisibility(View.GONE);
    }

    public void onShowKeyboardButtonClick(View view) {
        imeContainer.setVisibility(View.VISIBLE);
    }
}
