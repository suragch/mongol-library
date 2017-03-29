package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolTextView;
import net.studymongolian.mongollibrary.MongolUnicodeRenderer;


public class TestingActivity extends AppCompatActivity {

    MongolTextView textView;
    MongolCode renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        textView = (MongolTextView) findViewById(R.id.textingView);

        renderer = MongolCode.INSTANCE;
    }

    public void buttonClick(View view) {
        //textView.setText("hello");
        //textView.setTextSize(30);

        testRendering();
    }

    public void testRendering() {
        StringBuilder text = new StringBuilder();
        //text.append(MongolUnicodeRenderer.Uni.ZWJ);
        //text.append(MongolUnicodeRenderer.Uni.JA);
        //text.append(MongolUnicodeRenderer.Uni.I);
        //text.append(MongolUnicodeRenderer.Uni.JA);
        text.append("ᠵᠢᠵ  JIJ");
        //text.append(MongolUnicodeRenderer.Uni.MVS);
        //text.append(MongolUnicodeRenderer.Uni.A);
        //text.append(MongolUnicodeRenderer.Uni.FVS1);
        //text.append(MongolUnicodeRenderer.Uni.ZWJ);

        String renderedText = renderer.unicodeToMenksoft(text.toString());
        Log.i("TAG", "testRendering: " + renderedText);
    }
}
