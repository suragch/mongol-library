package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Input Method Container
 *
 * Currently it is a container/controller for Keyboards and suggested word candidates. In the future
 * it could also be a container for other IME views (like handwriting recognition or speech-to-text).
 *
 * The word candidates may be arranged vertically on the left of horizontally at the top.
 *
 * ImeContainer manages switching keyboards and handling communication between the keyboard and the
 * word suggestion candidates list.
 */
public class ImeContainer extends ViewGroup implements Keyboard.KeyboardListener {


//    // This view group should have up to two children
//    // the child at index 0 is the current keyboard
//    // the child at index 1 is the punctuation keyboard
//    private static final int CURRENT_KEYBOARD_INDEX = 0;
//    private static final int PUNCTUATION_KEYBOARD_INDEX = 1;

    List<Keyboard> mKeyboardCandidates;
    Keyboard mCurrentKeyboard;
    private WeakReference<MongolInputMethodManager> mimm;
    //private boolean showingPunctuationKeyboard = false;

    // TODO for a custom keyboard let it set a custom pronunciation keyboard itself

    public ImeContainer(Context context) {
        this(context, null, 0);
        //this.mContext = context;
    }

    public ImeContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImeContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }



    private void init(Context context) {

//        float dpPadding = 2;
//        int pxPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                dpPadding, getResources().getDisplayMetrics());
//
//        Keyboard aeiou = new KeyboardAeiou(context);
//        Keyboard qwerty = new KeyboardQwerty(context);
//        //Keyboard aeiouPunc = new KeyboardAeiouPunctuation(context);
//        //aeiou.setBackgroundColor(Color.WHITE);
//        qwerty.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
//        this.addView(qwerty);
//        mCurrentKeyboard = qwerty;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        //   ___________________________         ___________________________
        //   | C |                     |         |       Candidates        |
        //   | a |                     |         |_________________________|
        //   | n |                     |         |                         |
        //   | d |                     |         |                         |
        //   | i |                     |         |                         |
        //   | d |     Keyboard        |    or   |        Keyboard         |
        //   | a |                     |         |                         |
        //   | t |                     |         |                         |
        //   | e |                     |         |                         |
        //   | s |                     |         |                         |
        //   ---------------------------         ---------------------------

        // TODO need to add a Candidates view (currently Keyboard is filling the entire layout)




        int count = getChildCount();
        if (count == 0) return;



        final int totalAvailableWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalAvailableHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        float x = getPaddingLeft();
        float y = getPaddingTop();
        int keyboardWidth = totalAvailableWidth;
        int keyboardHeight = totalAvailableHeight;

        // just choose the first keyboard for now
        View child = getChildAt(0);
//        if (showingPunctuationKeyboard) {
//            if (PUNCTUATION_KEYBOARD_INDEX >= count) return;
//            child = getChildAt(PUNCTUATION_KEYBOARD_INDEX);
//        } else {
//            if (CURRENT_KEYBOARD_INDEX >= count) return;
//            child = getChildAt(CURRENT_KEYBOARD_INDEX);
//        }

        child.measure(MeasureSpec.makeMeasureSpec(keyboardWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(keyboardHeight, MeasureSpec.EXACTLY));
        child.layout((int) x, (int) y, (int) (x + keyboardWidth), (int) (y + keyboardHeight));

    }

    // forward this on to the current keyboard
    public void setInputConnection(InputConnection inputConnection) {
        if (mCurrentKeyboard != null) {
            mCurrentKeyboard.setInputConnection(inputConnection);
        }
    }

    InputConnection getInputConnection() {
        MongolInputMethodManager imm = mimm.get();
        if (imm == null) return null;
        return imm.getCurrentInputConnection();
    }

    // forward this on to the current keyboard
    public void onUpdateSelection(int oldSelStart, int oldSelEnd, int selStart, int selEnd, int candidatesStart, int candidatesEnd) {
        if (mCurrentKeyboard != null) {
            mCurrentKeyboard.onUpdateSelection(oldSelStart, oldSelEnd, selStart, selEnd, candidatesStart, candidatesEnd);
        }
    }

    @Override
    public void onRequestNewKeyboard(String keyboardDisplayName) {

        Keyboard newKeyboard = null;
        for (Keyboard keyboard : mKeyboardCandidates) {
            if (keyboard.getDisplayName().equals(keyboardDisplayName)) {
                newKeyboard = keyboard;
            }
        }

        if (newKeyboard == null) return;

        this.removeView(mCurrentKeyboard);
        newKeyboard.setKeyboardListener(this);
        this.addView(newKeyboard);
        mCurrentKeyboard = newKeyboard;
        InputConnection ic = getInputConnection();
        mCurrentKeyboard.setInputConnection(ic);
    }

    @Override
    public String[] getKeyboardCandidates() {
        int numberOfOtherKeyboards = mKeyboardCandidates.size() - 1;
        if (numberOfOtherKeyboards < 1) return null;
        String[] names = new String[numberOfOtherKeyboards];
        int nameIndex = 0;
        for (int i = 0; i < mKeyboardCandidates.size(); i++) {
            Keyboard keyboard = mKeyboardCandidates.get(i);
            if (keyboard == mCurrentKeyboard) {
                continue;
            }
            names[nameIndex] = keyboard.getDisplayName();
            nameIndex++;
        }
        return names;
    }

    public void setInputMethodManager(MongolInputMethodManager inputMethodManager) {
        this.mimm = new WeakReference<>(inputMethodManager);
    }

    public void apply(Builder builder) {
        this.mKeyboardCandidates = builder.keyboardCandidates;
        Keyboard defaultKeyboard = mKeyboardCandidates.get(0);
        defaultKeyboard.setKeyboardListener(this);
        this.addView(defaultKeyboard);
        mCurrentKeyboard = defaultKeyboard;
    }

    public static class Builder {
        //private final Context context;
        //private final Keyboard defaultKeyboard;
        private final List<Keyboard> keyboardCandidates;

        public Builder(Keyboard defaultKeyboard) {
            //this.context = context;
            //this.defaultKeyboard = defaultKeyboard;
            this.keyboardCandidates = new ArrayList<>();
            this.keyboardCandidates.add(defaultKeyboard);
        }

        public Builder addKeyboard(Keyboard keyboard) {
            this.keyboardCandidates.add(keyboard);
            return this;
        }
    }
}
