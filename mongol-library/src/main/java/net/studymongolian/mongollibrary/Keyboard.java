package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;


public abstract class Keyboard extends ViewGroup {

    // Standard keyboard types
//    public static final int AEIOU = 0;
//    public static final int AEIOU_PUNCTUATION = 1;
//    public static final int QWERTY = 2;
//    public static final int QWERTY_PUNCTUATION = 3;
//    public static final int LATIN = 4;
//    public static final int LATIN_PUNCTUATION = 5;
//    public static final int CYRILLIC = 6;
//    public static final int CYRILLIC_PUNCTUATION = 7;

    // Our communication link to the EditText/MongolEditText
    protected InputConnection inputConnection;
    protected StringBuilder mComposing = new StringBuilder();

    protected KeyboardListener mKeyboardController = null;

    public Keyboard(Context context) {
        this(context, null, 0);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context,
                         AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface KeyboardListener {
        public void punctuationSwitch();

    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.mKeyboardController = listener;
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    public void onUpdateSelection(int oldSelStart,
                                  int oldSelEnd,
                                  int newSelStart,
                                  int newSelEnd,
                                  int candidatesStart,
                                  int candidatesEnd) {

        // TODO in the Android source InputMethodService also handles Extracted Text here

        // currently we are only using composing for popup glyph selection. If we want to be more
        // like the standard keyboards we could do composing on the whole word.
        if (mComposing.length() > 0 && (newSelStart != candidatesEnd
                || newSelEnd != candidatesEnd)) {
            mComposing.setLength(0);
            // TODO updateCandidates();
            if (inputConnection != null) {
                inputConnection.finishComposingText();
            }
        }
    }
}
