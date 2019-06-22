package net.studymongolian.testingapp;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolTextView;


public class MongolTextViewActivity extends AppCompatActivity {

    MongolTextView xmlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_text_view);

        xmlTextView = findViewById(R.id.xml_textview);
    }

    public void runTests(View view) {
        spansAreClearedWhenNewTextIsSet();
    }

    private void spansAreClearedWhenNewTextIsSet() {
        // add a span
        SpannableStringBuilder text = new SpannableStringBuilder(xmlTextView.getText().toString());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        text.setSpan(colorSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        xmlTextView.setText(text);

        // change the text
        String newText = "new text";
        xmlTextView.setText(newText);

        // see if the span is still there
        CharSequence check = xmlTextView.getText();
        if (check instanceof SpannableStringBuilder) {
            ForegroundColorSpan span[] = ((SpannableStringBuilder) check).getSpans(
                    0, check.length(), ForegroundColorSpan.class);
            if (span.length > 0) {
                Toast.makeText(this,
                        "failed: " + span.length, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "passed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
