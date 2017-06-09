package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        mKeyA = new KeyText(context);
        mKeyA.setText(MongolCode.Uni.A);
        addView(mKeyA);

        mKeyE = new KeyText(context);
        mKeyE.setText(MongolCode.Uni.E);
        addView(mKeyE);

        mKeyI = new KeyText(context);
        mKeyI.setText(MongolCode.Uni.I);
        addView(mKeyI);

        mKeyO = new KeyText(context);
        mKeyO.setText(MongolCode.Uni.O);
        addView(mKeyO);

        mKeyU = new KeyText(context);
        String displayU = String.valueOf(MongolCode.Uni.UE) + String.valueOf(MongolCode.Uni.ZWJ);
        mKeyU.setText(displayU);
        addView(mKeyU);

        mKeyBackspace = new KeyText(context);
        mKeyBackspace.setText("del");
        addView(mKeyBackspace);

        // Row 2

        mKeyNA = new KeyText(context);
        mKeyNA.setText(MongolCode.Uni.NA);
        addView(mKeyNA);

        mKeyBA = new KeyText(context);
        mKeyBA.setText(MongolCode.Uni.BA);
        addView(mKeyBA);

        mKeyQA = new KeyText(context);
        mKeyQA.setText(MongolCode.Uni.QA);
        addView(mKeyQA);

        mKeyGA = new KeyText(context);
        mKeyGA.setText(MongolCode.Uni.GA);
        addView(mKeyGA);

        mKeyMA = new KeyText(context);
        mKeyMA.setText(MongolCode.Uni.MA);
        addView(mKeyMA);

        mKeyLA = new KeyText(context);
        mKeyLA.setText(MongolCode.Uni.LA);
        addView(mKeyLA);

        // Row 3

        mKeySA = new KeyText(context);
        mKeySA.setText(MongolCode.Uni.SA);
        addView(mKeySA);

        mKeyTADA = new KeyText(context);
        mKeyTADA.setText(MongolCode.Uni.TA);
        addView(mKeyTADA);

        mKeyCHA = new KeyText(context);
        mKeyCHA.setText(MongolCode.Uni.CHA);
        addView(mKeyCHA);

        mKeyJA = new KeyText(context);
        mKeyJA.setText(MongolCode.Uni.JA);
        addView(mKeyJA);

        mKeyYA = new KeyText(context);
        mKeyYA.setText(MongolCode.Uni.YA);
        addView(mKeyYA);

        mKeyRA = new KeyText(context);
        mKeyRA.setText(MongolCode.Uni.RA);
        addView(mKeyRA);

        // Row 4

        mKeyKeyboard = new KeyText(context);
        mKeyKeyboard.setText("kb");
        addView(mKeyKeyboard);

        mKeyComma = new KeyText(context);
        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        addView(mKeyComma);

        mKeySpace = new KeyText(context);
        mKeySpace.setText("");
        addView(mKeySpace);

        mKeySuffix = new KeyText(context);
        mKeySuffix.setText("ᠶ᠋ᠢ ᠳᠤ ᠤᠤ");
        addView(mKeySuffix);

        mKeyReturn = new KeyText(context);
        mKeyReturn.setText("ret");
        addView(mKeyReturn);



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

        // |  A |  E | I | O  | U  |    Row 1
        // | N | B | Q | G | M | L |    Row 2
        // | S | D | Ch| J | Y | R |    Row 3
        // |kb |,|nbs|space|ret|del|    Row 4

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
}
