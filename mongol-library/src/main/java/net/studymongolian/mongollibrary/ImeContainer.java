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
 * <p>
 * Currently it is a container/controller for Keyboards and suggested word candidates. In the future
 * it could also be a container for other IME views (like handwriting recognition or speech-to-text).
 * <p>
 * The word candidates may be arranged vertically on the left of horizontally at the top.
 * <p>
 * ImeContainer manages switching keyboards and handling communication between the keyboard and the
 * word suggestion candidates list.
 */
public class ImeContainer extends ViewGroup implements Keyboard.KeyboardListener {

    List<Keyboard> mKeyboardCandidates;
    Keyboard mCurrentKeyboard;
    private WeakReference<MongolInputMethodManager> mimm;

    // TODO for a custom keyboard let it set a custom pronunciation keyboard itself

    public ImeContainer(Context context) {
        super(context, null, 0);
    }

    public ImeContainer(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ImeContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        if (mCurrentKeyboard == null) return;


        this.removeView(mCurrentKeyboard);
        newKeyboard.setKeyboardListener(this);
        this.addView(newKeyboard);
        mCurrentKeyboard = newKeyboard;
        InputConnection ic = getInputConnection();
        mCurrentKeyboard.setInputConnection(ic);
    }

    @Override
    public PopupKeyCandidate[] getKeyboardCandidates() {
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
        return PopupKeyCandidate.createArray(names);
    }

    public void setInputMethodManager(MongolInputMethodManager inputMethodManager) {
        this.mimm = new WeakReference<>(inputMethodManager);
    }

    public void addKeyboard(Keyboard keyboard) {
        if (mKeyboardCandidates == null)
            mKeyboardCandidates = new ArrayList<>();

        mKeyboardCandidates.add(keyboard);

        // make the first keyboard added be the one that shows
        if (mKeyboardCandidates.size() == 1) {
            keyboard.setKeyboardListener(this);
            this.addView(keyboard);
            mCurrentKeyboard = keyboard;
        }
    }
}
