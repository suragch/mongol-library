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
        String unicode = "ᡐᡆᡑᡆ ᡋᡅᡔᡅᡎ ᠠᠷᠠᡑ ᡐᡉᡏᡄᠠ ᠰᡄᡑᡍᡄᠯ ᡍᠠᠨᡇᡏᡓᡅᡐᠠᡅ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᡎᡅ ᠯᠠᡉᡑᡇᡉᠠ ᡋᡆᡑᡅᡐᠠᡅ ᡋᡄᡃᠷ ᡄᠷᡍᡄᠯᡄᡍᡉ ᡍᡄᠷᡄᡎᡐᡄᡅ ᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊᠊ ᡄᡋᡄᠷᡄᡃᠠ ᠴᠠᠰᠠᡍᡇ ᡆᠷᡆᠨᡅ ᠨᠠᡏᡅᡅ᠋ᠠ ᡍᡆᠷᡆᡃᠠ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᠨᠠᡏ ᡅᠠ ᠠᡓᡅᠯ ᡅᠠ ᡍᡆᠷᡆᡃᠠ ᡑᡄᡑ ᠱᡇᡓᡅ ︑ ᡄᡋᡄᠷᡄᡃᠠᠴᠠᠰᠠᡍᡇ ᡆᠷᡆᠨᡅ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡐᡄᡊᡍᡄᡏ ᡅᠠ ᠨᠠᡏᡅᡅ᠋ᠠ ᡇᡑᡇᠷᡅᡑᡍᡇ ᡑᡇᡎᡇᡅ᠋ᠯᠠᡊ ᡎᡅᡅ᠋ᠠ ᠱᡇᡓᡅ ᠯᡅᡕᠠᡊ ᡒᠠᡉ ᡎᡅ ᠰᡇᠷᡋᡇᠯᡓᡅᠯᠠᡋᠠ ᡐᡇᠰ ᠰᡆᠨᡅᠨᡅ ᠰᡇᠷᡋᡇᠯᡓᡅᠯᠠᡎᡒᡅ ᡓᠠᡉ ᠱᡅ ᡕᠠ          ᠠᠯᡇᠰ ᡍᡆᠯᡆ ᡅᠠ ᡋᡆᡑᡆᠯᡎᠠ ᠨᡅ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡕᡄᡃᠷ ᡉᠠᡑᡉᠰᡉ ᡋᡆᠯᡎᡆᡑᠠᡎ ︒ ᠨᠠᡏᡅᡅ᠋ᠠ 19 ᡑᡉᡎᡄᡃᠷ ᡕᡄᡍᡄ ᡍᡇᠷᠠᠯ ᡅᠠ ᡅᠯᡄᡑᡍᡄᠯ ᡑᡉ ᡐᡆᡑᡆᠷᡍᠠᡅ ᠴᠠᡃᡓᡅ ᡎᠠᠷᡎᠠᡎᠰᠠᠠ ᠨᡅ  ︐ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡕᡄᡃᠷ ᡍᡉᡒᡉᠷᡍᡄᡎ ᡇᠯᡇᠰ ᡅᡎᡅ ᡋᡆᡐᡄᡃᠠ ᡋᠠᡅ᠋ᡎᡇᡉᠯᡍᡇ ᠨᡅ ᡑᡇᡏᡑᠠᡑᡇ ᡅᠠ ᡉᠠᡑᡉᠰᡉᡐᡄᠨᡅ ᠠᡎᡇᡉ ᡕᡄᡍᡄ ᠰᡄᠷᡎᡄᠯ ᡏᠠᠠᡑᡇᠯ ᡅᠠ ᠰᡇᡉᠷᡅ ᡅᠠᡓᡅᠨᡄᠷᡅᡊ ᡋᡆᠯᡇᠨᠠᡅ ︐ ᠰᡇᠷᡎᠠᠠᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡉᡅ᠋ᠯᡄᠰ ᡅᡎᡅ ᡄᠷᡍᡄᡋᡅᠱᡅ ᡐᡄᠷᡅᡎᡉᡉᠠ ᡋᠠᡅ᠋ᠷᡅᠠᡑᡇ ᡐᠠᡋᡅᡓᡅ ︐ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡈᡃᠷᡒᡅᠯᡄᠯᡐᡉ ᡅᡎᡅ ᡎᡉᡉᡓᡅᠷᡉᡉᠯᡉᠠ ︐ ᠰᡇᠷᡎᠠᠠ  ᡍᡉᡏᡉᡓᡅᠯ  ᡅᠠ  ᡈᡑᡈᡎᡄᡃᡓᡅᠯᡄᠯᡐᡉᡅᡎᡅ ᡐᡉᠷᡎᡄᡑᡍᡄᡓᡅ ︐ ᠠᠷᠠᡑ ᡐᡉᡏᡄᠠ ᠰᡄᡑᡍᡄᠯ ᡍᠠᠨᡇᡏᡓᡅᡐᠠᡅ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᡎᡅ ᠰᠠᡅ᠋ᠨᠠᡃᠷ ᡄᠷᡍᡄᠯᡄᡍᡉ ᡍᡄᠷᡄᡎᡐᡄᡅ ︒         ᡕᠠᡎᠠᡓᡅ ﹃ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡉᡅ᠋ᠯᡄᠰ ᡅᡎᡅ ᡇᠷᡅᡑᠠᡃᠷ ᡍᡈᡎᡓᡉᡉᠯᡍᡉ ﹄ ᡆᡅ᠋ᠯᡎᠠᡎᡑᠠᡍᡇᠠ ᡅᡎᡅ ᡓᡅᡊᡍᡅᠨᡄᡃᠷ ᡋᡆᡑᡅᡐᠠᡅ᠋ᠷᡇᡉᠯᡍᡇ  ᡋᡆᡅ ︖  ᠱᡅᡑᡄᠷ  ᠰᡇᠷᡋᡇᠯᡓᡅᠯᠠᡎᡒᡅ  ᡄᡋᡄᠷᡄᡃᠠᠴᠠᠰᠠᡍᡇ ᡆᠷᡆᠨᡅ ᠨᠠᡏᡅᡅ᠋ᠠ ᡍᡆᠷᡆᡃᠠ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᠨᠠᡏᡅᡅ᠋ᠠ ᠠᡓᡅᠯ ᡅᠠ ᡍᡆᠷᡆᡃᠠ ᡑᡄᡑ ᠱᡇᡓᡅ ︑ ᡄᡋᡄᠷᡄᡃᠠ ᠴᠠᠰᠠᡍᡇ ᡆᠷᡆᠨᡅ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡐᡄᡊᡍᡄᡏ ᡅᠠ  ᠨᠠᡏᡅᡅ᠋ᠠᡇᡑᡇᠷᡅᡑᡍᡇ ᡑᡇᡎᡇᡅ᠋ᠯᠠᡊ ᡎᡅᡅ᠋ᠠ ᠱᡇᡓᡅ ᠯᡅᡕᠠᡊ ᡒᠠᡉ ᡎᡅ ᡐᡇᠰᡎᠠᡅ᠋ᠯᠠᠠ ᠰᡇᠷᡋᡇᠯᡓᡅᠯᠠᡋᠠ ︒         ﹃ ᠨᠠᡏᡅᡅ᠋ᠠ 19 ᡑᡉᡎᡄᡃᠷ ᡕᡄᡍᡄ ᡍᡇᠷᠠᠯ ᡅᠠ ᡅᠯᡄᡑᡍᡄᠯ ᡑᡉ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡉᡅ᠋ᠯᡄᠰ ᡅᠠ ᡍᡈᡎᡓᡅᠯᡐᡉ ᡅᡎᡅ ᡇᠷᡅᡑᠠ ᡈᡏᡈᠨᡈ ᡋᠠᡅ᠋ᡎᠠᡃᡑ ᡉᡎᡄᡅ ᡇᠯᡇᠰ  ᡐᡈᠷᡈᡅ᠋ᠠ  ᡑᡄᡃᡑᡉᡍᡄᡏᡓᡄᡃᠠᡑᡉ ᡍᡉᠷᡎᡄᡋᡄᡅ ︐ ᡄᠨᡄ ᠨᡅ ᠱᡅ ᡓᡅᠠ ᡌᡅᡊ ᠨᡈᡍᡉᠷ ᡕᡄᡃᠷ ᡎᡆᠯ ᡋᡆᠯᡎᡆᡎᠰᠠᠠ ᠨᠠᡏᡅᡅ᠋ᠠ ᡐᡈᡋ ᡍᡆᠷᡆᡃᠠ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᠠᡓᡅᠯ ᡑᡇ ᡋᡆᠯᡇᡎᠰᠠᠠ ᡑᡄᡃᡑᡉ ᡍᡄᡏᡓᡄᡃᠨᡅ ᡒᡅᡍᡇᠯᠠᡒᡅᠯᠠᡎᠠᡋᠠ ᡄᠷᡄᠰ ᡋᠠᡐᡇ ᡇᡑᡇᠷᡅᡑᡇᠯᡎᠠ ᡅᡎᡅ ᡎᡉᡅ᠋ᡔᡄᠰ ᡅᠯᡄᠷᡉᡉᠯᡋᡄᡅ ﹄ ︒ ᠯᡅᡕᠠᡊ ᡒᠠᡉ ᡏᡄᡑᡉᡉᠯᡉᡎᠰᡄᠠ ᠨᡅ ︐ ᡋᡆᡍᡉ ᡆᠷᡆᠨᡅ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᠰᡄᠰᡐᡄᡏ ᠱᡅ ᡓᡅᠠ  ᡌᡅᡊ  ᡎᡅᡅ᠋ᠠ  ᠱᡅᠨᡄᡔᠠᡎ ᡉᡕᡄᡅ᠋ᠠ ᡑᡇᡏᡑᠠᡑᡇ ᡇᠯᡇᠰ ᡅᠠ ᡆᠠᡔᡆᠯᡅᡎᡐᠠᡅ ᠨᡄᡅ᠋ᡎᡄᡏ ᡓᡇᠷᡇᡏ ᡅᠠ ᡉᠴᡄᠯ ᠰᠠᠨᡃᠠ ᡋᠠ ᠨᠠᡏᡅᡅ᠋ᠠ 19 ᡑᡉᡎᡄᡃᠷ ᡕᡄᡍᡄ ᡍᡇᠷᠠᠯ ᡅᠠ ᠴᡆᠷᡅᡎ ᠰᠠᠨᠠᡃᡎᡅ ᠰᡇᠷᡇᠯᡔᠠᠠ ᡐᡇᡉᠱᠯᡇᡉᠯᡍᡇ  ᡋᡄᡃᠷᡎᡆᠯ ᠱᡇᡎᡇᡏ ᡋᡆᠯᡎᡆᡓᡅ ︐ ᠨᡄᡅ᠋ᡎᡄᡏ ᡓᡇᠷᡇᡏ ᡅᠠ ᠰᡇᠷᡎᡇᡉᠯᡅ ᡄᠷᡍᡄᠯᡄᡍᡉ ᡒᡅᡎᠯᡄᠯ ᡅᡎᡅ ᡋᠠᠷᡅᡏᡐᡇᠯᠠᠠ ︐ ᡕᡄᠷᡉᡊᡍᡄᡅ ᡍᠠᠷᠠᠯᡐᡇ ᡑᡇ ᠠᡊᡍᠠᡃᠷᡇᠯ ᡕᡄᡃᠠ ᡋᠠᡐᡇ ᡋᡄᡍᡄ ᡐᡈᡉᠯᡄᠷᡉᡉᠯᡓᡅ ︐ ᠰᡇᠷᡎᠠᠠᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡎᡆᠯ ᡉᡉᠷᡎᡄ ᠯᡉᡎᡄᡃ ᠨᡅᡎᡐᠠ ᡋᠠᠷᡅᠯᡑᡇᡉᠯᡓᡅ ︐ ᠰᡇᠷᡎᠠᠠ ᡍᡉᡏᡉᡓᡅᠯ ᡅᠠ ᡈᡃᠷᡒᡅᠯᡄᠯᡐᡉ ᡅᡎᡅ ᡋᡆᡍᡉ ᡐᠠᠯᠠᡃᠷ ᡎᡉᡉᡓᡅᠷᡉᡉᠯᡉᠠ ︐ ᠠᠷᠠᡑ  ᡐᡉᡏᡄᠠ  ᠰᡄᡑᡍᡄᠯ ";
        String unicode2 = "ᡆᠷᡆᠨᡅ ᠨᠠᡏᡅᡅ᠋ᠠ ᡍᡆᠷᡆᡃᠠ";
        String unicode3 = "\uE2C1\uE26D\uE281\uE2B6᠎\uE26A \uE2C1\uE26D\uE321\uE27E\uE2B6᠎\uE26A \uE2B1\uE26C\uE27E\u180C\uE2F5\uE268";
        String unicode4 = "\uE2B1\uE26C\uE27E\u180C\uE2F5\uE268";

        String text = MongolCode.INSTANCE.unicodeToMenksoft(unicode);

        editText.setText(unicode3);
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
