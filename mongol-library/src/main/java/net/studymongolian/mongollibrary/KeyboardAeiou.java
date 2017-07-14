package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.StringBuilderPrinter;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class KeyboardAeiou extends ViewGroup {

    // Row 1
    private KeyText mKeyA;
    private KeyText mKeyE;
    private KeyText mKeyI;
    private KeyText mKeyO;
    private KeyText mKeyU;

    // Row 2
    private KeyText mKeyNA;
    private KeyText mKeyBA;
    private KeyText mKeyQA;
    private KeyText mKeyGA;
    private KeyText mKeyMA;
    private KeyText mKeyLA;

    // Row 3
    private KeyText mKeySA;
    private KeyText mKeyTADA;
    private KeyText mKeyCHA;
    private KeyText mKeyJA;
    private KeyText mKeyYA;
    private KeyText mKeyRA;

    // Row 4
    private KeyText mKeyKeyboard;
    private KeyText mKeyComma;
    private KeyText mKeySpace;
    private KeyText mKeySuffix;
    private KeyText mKeyBackspace;
    private KeyText mKeyReturn;

    // This will map the button resource id to the String value that we want to
    // input when that key is clicked.
    Map<KeyText, String> keyValues = new HashMap<>();

    // Our communication link to the EditText/MongolEditText
    InputConnection inputConnection;

    public KeyboardAeiou(Context context) {
        this(context, null, 0);
    }

    public KeyboardAeiou(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardAeiou(Context context,
                         AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void init(Context context) {

        Typeface typeface = MongolFont.get(MongolFont.QAGAN, context);
        float textSize = 24;
        int textColor = Color.BLACK;
        int keyColor = Color.LTGRAY;
        int pressedColor = Color.GRAY;
        int borderColor = Color.BLACK;
        int borderWidth = 0;
        int borderRadius = 5;
        int padding = 2;

        // Row 1

        mKeySuffix = new KeyText(context);
        //mKeySuffix.setText(MongolCode.Uni.NNBS, "ᠶ᠋ᠢ ᠳᠤ ᠤᠤ");
        mKeySuffix.setText("ᠶ᠋ᠢ ᠳᠤ ᠤᠤ");
        //mKeySuffix.setOnKeyClickListener(this);
        mKeySuffix.setOnTouchListener(textKeyTouchListener);
        addView(mKeySuffix);

        mKeyA = new KeyText(context);
        mKeyA.setText(MongolCode.Uni.A);
        //mKeyA.setOnKeyClickListener(this);
        mKeyA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyA, String.valueOf(MongolCode.Uni.A));
        //mKeyA.setGestureDetector();
        addView(mKeyA);

        mKeyE = new KeyText(context);
        mKeyE.setText(MongolCode.Uni.E);
        //mKeyE.setOnKeyClickListener(this);
        mKeyE.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyE, String.valueOf(MongolCode.Uni.E));
        addView(mKeyE);

        mKeyI = new KeyText(context);
        mKeyI.setText(MongolCode.Uni.I);
        //mKeyI.setOnKeyClickListener(this);
        mKeyI.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyI, String.valueOf(MongolCode.Uni.I));
        addView(mKeyI);

        mKeyO = new KeyText(context);
        mKeyO.setText(MongolCode.Uni.U);
        //mKeyO.setOnKeyClickListener(this);
        mKeyO.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyO, String.valueOf(MongolCode.Uni.U));
        addView(mKeyO);

        mKeyU = new KeyText(context);
        String displayU =
                String.valueOf(MongolCode.Uni.UE) +
                        String.valueOf(MongolCode.Uni.ZWJ);
        mKeyU.setText(displayU);
        //mKeyU.setText(MongolCode.Uni.UE, displayU);
        //mKeyU.setOnKeyClickListener(this);
        mKeyU.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyU, String.valueOf(MongolCode.Uni.UE));
        addView(mKeyU);

        // Row 2

        mKeyNA = new KeyText(context);
        mKeyNA.setText(MongolCode.Uni.NA);
        //mKeyNA.setOnKeyClickListener(this);
        mKeyNA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyNA, String.valueOf(MongolCode.Uni.NA));
        addView(mKeyNA);

        mKeyBA = new KeyText(context);
        mKeyBA.setText(MongolCode.Uni.BA);
        //mKeyBA.setOnKeyClickListener(this);
        mKeyBA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyBA, String.valueOf(MongolCode.Uni.BA));
        addView(mKeyBA);

        mKeyQA = new KeyText(context);
        mKeyQA.setText(MongolCode.Uni.QA);
        //mKeyQA.setOnKeyClickListener(this);
        mKeyQA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyQA, String.valueOf(MongolCode.Uni.QA));
        addView(mKeyQA);

        mKeyGA = new KeyText(context);
        mKeyGA.setText(MongolCode.Uni.GA);
        //mKeyGA.setOnKeyClickListener(this);
        mKeyGA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyGA, String.valueOf(MongolCode.Uni.GA));
        addView(mKeyGA);

        mKeyMA = new KeyText(context);
        mKeyMA.setText(MongolCode.Uni.MA);
        //mKeyMA.setOnKeyClickListener(this);
        mKeyMA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyMA, String.valueOf(MongolCode.Uni.MA));
        addView(mKeyMA);

        mKeyLA = new KeyText(context);
        mKeyLA.setText(MongolCode.Uni.LA);
        //mKeyLA.setOnKeyClickListener(this);
        mKeyLA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyLA, String.valueOf(MongolCode.Uni.LA));
        addView(mKeyLA);

        // Row 3

        mKeySA = new KeyText(context);
        mKeySA.setText(MongolCode.Uni.SA);
        //mKeySA.setOnKeyClickListener(this);
        mKeySA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeySA, String.valueOf(MongolCode.Uni.SA));
        addView(mKeySA);

        mKeyTADA = new KeyText(context);
        mKeyTADA.setText(MongolCode.Uni.TA);
        //mKeyTADA.setOnKeyClickListener(this);
        mKeyTADA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyTADA, String.valueOf(MongolCode.Uni.TA));
        addView(mKeyTADA);

        mKeyCHA = new KeyText(context);
        mKeyCHA.setText(MongolCode.Uni.CHA);
        //mKeyCHA.setOnKeyClickListener(this);
        mKeyCHA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyCHA, String.valueOf(MongolCode.Uni.CHA));
        addView(mKeyCHA);

        mKeyJA = new KeyText(context);
        mKeyJA.setText(MongolCode.Uni.JA);
        //mKeyJA.setOnKeyClickListener(this);
        mKeyJA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyJA, String.valueOf(MongolCode.Uni.JA));
        addView(mKeyJA);

        mKeyYA = new KeyText(context);
        mKeyYA.setText(MongolCode.Uni.YA);
        //mKeyYA.setOnKeyClickListener(this);
        mKeyYA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyYA, String.valueOf(MongolCode.Uni.YA));
        addView(mKeyYA);

        mKeyRA = new KeyText(context);
        mKeyRA.setText(MongolCode.Uni.RA);
        //mKeyRA.setOnKeyClickListener(this);
        mKeyRA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyRA, String.valueOf(MongolCode.Uni.RA));
        addView(mKeyRA);

        // Row 4

        mKeyKeyboard = new KeyText(context);
        mKeyKeyboard.setText("kb");
        //mKeyKeyboard.setOnKeyClickListener(this);
        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
        addView(mKeyKeyboard);

        mKeyComma = new KeyText(context);
        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        //mKeyComma.setOnKeyClickListener(this);
        mKeyComma.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyComma, String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA));
        addView(mKeyComma);

        mKeySpace = new KeyText(context);
        mKeySpace.setText(" ");
        //mKeySpace.setOnKeyClickListener(this);
        mKeySpace.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeySpace, " ");
        addView(mKeySpace);

        mKeyReturn = new KeyText(context);
        //mKeyReturn.setText("\n", "ret");
        mKeyReturn.setText("ret");
        //mKeyReturn.setOnKeyClickListener(this);
        mKeyReturn.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyReturn, "\n");
        addView(mKeyReturn);

        mKeyBackspace = new KeyText(context);
        mKeyBackspace.setText("del");
        //mKeyBackspace.setOnKeyClickListener(this);
        mKeyBackspace.setOnTouchListener(textKeyTouchListener);
        addView(mKeyBackspace);


        for (int i = 0; i < getChildCount(); i++) {
            KeyText child = (KeyText) getChildAt(i);
            child.setTypeFace(typeface);
            child.setTextSize(textSize);
            child.setTextColor(textColor);
            child.setKeyColor(keyColor);
            child.setPressedColor(pressedColor);
            child.setBorderColor(borderColor);
            child.setBorderWidth(borderWidth);
            child.setBorderRadius(borderRadius);
            child.setPadding(padding, padding, padding, padding);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // | A | E | I | O | U |nbs|    Row 1
        // | N | B | Q | G | M | L |    Row 2
        // | S | D | Ch| J | Y | R |    Row 3
        // |kb | , | space |ret|del|    Row 4

        final int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        int[] numberOfKeysInRow = {6, 6, 6, 5};
        int weightUnit = 6; // all row weights must sum to this
        float[] keyWeight = {
                1, 1, 1, 1, 1, 1,           // row 1
                1, 1, 1, 1, 1, 1,           // row 2
                1, 1, 1, 1, 1, 1,           // row 3
                1, 1, 2.0f, 1, 1};          // row 4
        int numberOfRows = numberOfKeysInRow.length;

        int x = getPaddingLeft();
        int y = getPaddingTop();
        int keyIndex = 0;
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {


            int end = keyIndex + numberOfKeysInRow[rowIndex];
            for (int i = keyIndex; i < end; i++) {
                View child = getChildAt(keyIndex);

                int keyWidth = (int) ((totalWidth / weightUnit) * keyWeight[keyIndex]);
                int keyHeight = totalHeight / numberOfRows;
                child.measure(MeasureSpec.makeMeasureSpec(keyWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(keyHeight, MeasureSpec.EXACTLY));

                child.layout(x, y, x + keyWidth, y + keyHeight);
                x += keyWidth;
                keyIndex++;
            }

            x = getPaddingLeft();
            y += totalHeight / numberOfRows;
        }

    }

//    private View.OnTouchListener keyTouchListener = new View.OnTouchListener(){
//        public KeyText key;
//        Context context = getContext();
//        public boolean onTouch(View v, MotionEvent event) {
//            key = (KeyText) v;
//            int action = event.getActionMasked();
//
//            switch(action) {
//                case (MotionEvent.ACTION_DOWN) :
//                    key.setPressedState(true);
//                    break;
//                case (MotionEvent.ACTION_CANCEL) :
//                case (MotionEvent.ACTION_OUTSIDE) :
//                case (MotionEvent.ACTION_UP) :
//                    key.setPressedState(false);
//                    break;
//            }
//            return gestureDetector.onTouchEvent(event);
//        }
//
//        GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
//
//            @Override
//            public boolean onDown(MotionEvent event) {
//                // triggers first for both single tap and long press
//                return true;
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                Log.i("MYLOG","onSingleTapUp in view: "+key);
//                if (key == mKeyBackspace) {
//                    inputConnection.deleteSurroundingText(1, 0);
//                } else if (key == mKeyKeyboard) {
//
//                } else {
//                    String inputText = keyValues.get(key);
//                    inputConnection.commitText(inputText, inputText.length());
//                }
//
//                return true;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//                Log.i("MYLOG","LongPress in view: "+key);
//
//                String[] items = {"a", "b", "c"};
//                View popupView = getPopupView(context, items);
//                final PopupWindow popupWindow = getPopupWindow(popupView);
//
//                // show the popup window
//                popupWindow.showAtLocation(key, Gravity.CENTER, 0, 0);
//            }
//
//
//
//
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                if (e1.getX()<e2.getX())
//                    Log.i("MYLOG","Fling Right in view: "+key);
//                else
//                    Log.i("MYLOG","Fling Left in view: "+key);
//                return true;
//            }
//        });
//    };
//
//    private View getPopupView(Context context, String[] items) {
//
//        LinearLayout parent = new LinearLayout(context);
//        parent.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        parent.setOrientation(LinearLayout.HORIZONTAL);
//        parent.setBackgroundColor(Color.RED);
//
//        TextView tv1 = new TextView(context);
//        TextView tv2 = new TextView(context);
//        TextView tv3 = new TextView(context);
//
//        tv1.setText(items[0]);
//        tv2.setText(items[1]);
//        tv3.setText(items[2]);
//
//        parent.addView(tv1);
//        parent.addView(tv2);
//        parent.addView(tv3);
//
//        // TODO
//        return parent;
//    }
//
//    private PopupWindow getPopupWindow(View popupView) {
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // TODO
//        return popupWindow;
//    }


    protected View.OnTouchListener textKeyTouchListener = new View.OnTouchListener() {

        Handler handler;
        final int LONGPRESS_THRESHOLD = 500; // milliseconds


        View popupView;
        int popupWidth;
        PopupWindow popupWindow;
        //CurrentFvsSelection currentFvsSelection = CurrentFvsSelection.FVS1;


        @Override
        public boolean onTouch(View view, MotionEvent event) {

            // show fvs chooser view on touch down
            // update hilighted on touch move
            // hide fvs chooser view and send fvs char on touch up

            final KeyText key = (KeyText) view;
            int action = MotionEventCompat.getActionMasked(event);

            switch (action) {
                case (MotionEvent.ACTION_DOWN):

                    key.setPressedState(true);
                    handler = new Handler();

                    Runnable runnableCode = new Runnable() {
                        @Override
                        public void run() {

                            // get the popup view
                            PopupKeyCandidates popupView = new PopupKeyCandidates(getContext());
                            popupView.setBackgroundColor(Color.BLUE);
                            String[] candidates = {"a", "b", "c"};
                            popupView.init(candidates, 30);

                            popupWindow = new PopupWindow(popupView,
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            int location[] = new int[2];
                            key.getLocationOnScreen(location);
                            int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                            popupView.measure(measureSpec, measureSpec);
                            popupWidth = popupView.getMeasuredWidth();
                            popupWindow.showAtLocation(key, Gravity.NO_GRAVITY, location[0], location[1] - popupView.getMeasuredHeight());


                        }
                    };

                    handler.postDelayed(runnableCode, LONGPRESS_THRESHOLD);
                    return true;
                case (MotionEvent.ACTION_MOVE):

                    float x = event.getX();


                    return true;
                case (MotionEvent.ACTION_UP):
                    // allow to fall through to the default (dismiss the popup window)

                    if (key == mKeyBackspace) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else if (key == mKeyKeyboard) {

                    } else {
                        String inputText = keyValues.get(key);
                        inputConnection.commitText(inputText, inputText.length());
                    }
                default:
                    key.setPressedState(false);
                    handler.removeCallbacksAndMessages(null);
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                    return false;
            }
        }
    };


    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

}
