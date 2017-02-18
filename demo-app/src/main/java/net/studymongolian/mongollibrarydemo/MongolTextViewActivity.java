package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.studymongolian.mongollibrary.MongolTextLine;
import net.studymongolian.mongollibrary.MongolTextView;


public class MongolTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_textview);

        MongolTextView textView1 = (MongolTextView) findViewById(R.id.mongol_textview_matchparent);
        MongolTextView textView2 = (MongolTextView) findViewById(R.id.mongol_textview_wrapcontent);



    }
}
