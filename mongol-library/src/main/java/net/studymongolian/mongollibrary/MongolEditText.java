package net.studymongolian.mongollibrary;


import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;

public class MongolEditText extends MongolTextView {

    // This is a text offset based on the unicode (not glyph) position
    int mCursorLocation;

    public MongolEditText(Context context) {
        this(context, null);
        init();
    }

    public MongolEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCursorLocation = 0; // FIXME should be text length
    }

    // XXX adding the Editable methods here rather than returning
    // an Editable. This makes it easier to control the layout. But
    // it deviates from the standard EditText usage so should this
    // be changed?

    public void insertText(CharSequence text) {
        super.mTextStorage.insert(mCursorLocation, text);
        super.mLayout.setText(mTextStorage.getGlyphText());
        mCursorLocation += text.length();
        invalidate();
        requestLayout();
    }
}
