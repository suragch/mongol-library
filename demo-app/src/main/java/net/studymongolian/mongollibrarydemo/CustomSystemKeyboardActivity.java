package net.studymongolian.mongollibrarydemo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CustomSystemKeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_system_keyboard);
    }

    public void onActivateKeyboardsButtonClick(View view) {
        Intent inputSettings = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        startActivityForResult(inputSettings, 0);
    }

    public void onChooseKeyboardsButtonClick(View view) {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showInputMethodPicker();
    }
}
