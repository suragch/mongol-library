package net.studymongolian.mongollibrarydemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class CustomImeContainer extends ImeContainer {

    KeyboardEmoji mEmojiKeyboard;

    public CustomImeContainer(Context context) {
        super(context);
    }

    public CustomImeContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImeContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected List<Drawable> getToolButtonItems() {
        List<Drawable> images = new ArrayList<>();
        images.add(getKeyboardNavigationImage());
        images.add(getEmojiImage());
        return images;
    }

    @Override
    public void onToolItemClick(int position) {
        switch (position) {
            case 0:
                toggleTempKeyboardView(getNavigationView());
                break;
            case 1:
                toggleTempKeyboardView(getEmojiKeyboard());
                break;
            default:
                throw new IllegalArgumentException("Undefined tool item");
        }
    }

    private Drawable getKeyboardNavigationImage() {
        return ContextCompat.getDrawable(this.getContext(), R.drawable.ic_navigation_32dp);
    }

    private Drawable getEmojiImage() {
        return ContextCompat.getDrawable(this.getContext(), R.drawable.ic_keyboard_emoji_32dp);
    }

    protected KeyboardEmoji getEmojiKeyboard() {
        if (mEmojiKeyboard != null) return mEmojiKeyboard;
        Keyboard currentKeyboard = getCurrentKeyboard();
        Keyboard.StyleBuilder builder = new Keyboard.StyleBuilder();
        builder.typeface(currentKeyboard.getTypeface())
                .primaryTextSizePx(currentKeyboard.getPrimaryTextSize())
                .primaryTextColor(currentKeyboard.getPrimaryTextColor())
                .keyColor(currentKeyboard.getKeyColor())
                .keyPressedColor(currentKeyboard.getKeyPressedColor())
                .keyBorderColor(currentKeyboard.getBorderColor())
                .keyBorderRadius(currentKeyboard.getBorderRadius())
                .keyBorderWidth(currentKeyboard.getBorderWidth())
                .keySpacing(currentKeyboard.getKeySpacing())
                .popupBackgroundColor(currentKeyboard.getPopupBackgroundColor())
                .popupHighlightColor(currentKeyboard.getPopupHighlightColor())
                .popupTextColor(currentKeyboard.getPopupTextColor())
                .candidatesLocation(currentKeyboard.getCandidatesLocation());
        KeyboardEmoji keyboardEmoji = new KeyboardEmoji(getContext(), builder);
        keyboardEmoji.setOnKeyboardListener(this);
        mEmojiKeyboard = keyboardEmoji;
        return keyboardEmoji;
    }
}
