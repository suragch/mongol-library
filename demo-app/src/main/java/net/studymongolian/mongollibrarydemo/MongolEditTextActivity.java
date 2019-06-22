package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolMenu;
import net.studymongolian.mongollibrary.MongolMenuItem;
import net.studymongolian.mongollibrary.MongolToast;


public class MongolEditTextActivity extends AppCompatActivity implements MongolEditText.ContextMenuCallback {

    MongolEditText metDemoEditText;
    Button customMenuButton;
    private boolean isUsingDefaultContextMenu = false;

    private static final String[] SAMPLE_TEXT = {"ᠨᠢᠭᠡ", "ᠬᠣᠶᠠᠷ", "ᠭᠣᠷᠪᠠ", "ᠳᠥᠷᠪᠡ", "ᠲᠠᠪᠤ", "ᠵᠢᠷᠭᠤᠭ᠎ᠠ", "ᠳᠣᠯᠣᠭ᠎ᠠ", "ᠨᠠ‍ᠢᠮᠠ", "ᠶᠢᠰᠦ", "ᠠᠷᠪᠠ"};
    private int mSampleTextIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_edittext);

        metDemoEditText = findViewById(R.id.metExample);
        customMenuButton = findViewById(R.id.custom_menu_button);
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

    public void contextMenuClick(View view) {
        if (isUsingDefaultContextMenu) {
            metDemoEditText.setContextMenuCallbackListener(null);
            customMenuButton.setText("use custom context menu");
        } else {
            metDemoEditText.setContextMenuCallbackListener(this);
            Toast.makeText(this, "Long click the MongolEditText to see the custom menu", Toast.LENGTH_LONG).show();
            customMenuButton.setText("use default context menu");
        }
        isUsingDefaultContextMenu = !isUsingDefaultContextMenu;
    }

    @Override
    public MongolMenu getMongolEditTextContextMenu(MongolEditText met) {
        // This is a demo menu only
        // You will need to implement your own functionality
        // See the MongolEditText source code for examples
        MongolMenu menu = new MongolMenu(this);
        menu.add(new MongolMenuItem("ᠨᠢᠭᠡ", R.drawable.ic_sun));
        menu.add(new MongolMenuItem("ᠬᠤᠶᠠᠷ", R.drawable.ic_moon));
        menu.add(new MongolMenuItem("ᠭᠤᠷᠪᠠ", R.drawable.ic_star));
        menu.setOnMenuItemClickListener(new MongolMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MongolMenuItem item) {
                MongolToast.makeText(MongolEditTextActivity.this, item.getTitle(), MongolToast.LENGTH_SHORT).show();
                return true;
            }
        });
        return menu;
    }
}
