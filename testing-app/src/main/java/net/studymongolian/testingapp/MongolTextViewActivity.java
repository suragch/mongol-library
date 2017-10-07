package net.studymongolian.testingapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolTextView;


public class MongolTextViewActivity extends AppCompatActivity {

    FrameLayout containerView;
    MongolTextView xmlTextView;
    boolean testPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_text_view);

        containerView = findViewById(R.id.container_view);
        xmlTextView = findViewById(R.id.xml_textview);


//        ClickableSpan span = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(MongolTextViewActivity.this, "span", Toast.LENGTH_SHORT).show();
//            }
//        };
//        SpannableStringBuilder myString = new SpannableStringBuilder("hello there");
//        myString.setSpan(span, 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        xmlTextView.setText(myString);
//        xmlTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MongolTextViewActivity.this, "toast", Toast.LENGTH_SHORT).show();
//            }
//        });
//        xmlTextView.setMovementMethod(LinkMovementMethod.getInstance());


    }

    public void runTests(View view) {
        clickListenerRespondsToClick();
    }

    // FIXME I couldn't get this to work
    private void clickListenerRespondsToClick() {
//        // setup
//        MongolTextView textView = new MongolTextView(this);
//        //View textView = new View(this);
//        textView.setLayoutParams(new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//        containerView.addView(textView);
//        testPassed = false;
//
//        // test
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                testPassed = true;
//            }
//        });
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        //textView.performClick();
//
//        int[] coordinates = new int[2];
//        textView.getLocationOnScreen(coordinates);
//
//        long downTime = SystemClock.uptimeMillis();
//        long eventTime = SystemClock.uptimeMillis();
//        int action = MotionEvent.ACTION_DOWN;
//        int x = coordinates[0];
//        int y = coordinates[1];
//        int metaState = 0;
//
//
//        MotionEvent event = MotionEvent.obtain(downTime, eventTime, action, x, y, metaState);
//        textView.dispatchTouchEvent(event);
//
//        action = MotionEvent.ACTION_UP;
//        eventTime = SystemClock.uptimeMillis();
//        MotionEvent event2 = MotionEvent.obtain(downTime, eventTime, action, x, y, metaState);
//        textView.dispatchTouchEvent(event2);
//
//        if (!testPassed) throw new RuntimeException();
//        Log.i("TAG", "clickListenerRespondsToClick: passed");
//
//        // teardown
//        containerView.removeAllViews();
    }
}
