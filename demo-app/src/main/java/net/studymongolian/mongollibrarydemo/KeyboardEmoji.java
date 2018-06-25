package net.studymongolian.mongollibrarydemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import net.studymongolian.mongollibrary.Key;
import net.studymongolian.mongollibrary.KeyBackspace;
import net.studymongolian.mongollibrary.KeyImage;
import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.PopupKeyCandidate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardEmoji extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DEFAULT_DISPLAY_NAME = "ᠢᠮᠤᠵᠢ"; // smile emoji

    private static final String EMOJI_LIST = "\uD83D\uDE00 \uD83D\uDE17 \uD83D\uDE19 \uD83D\uDE11 \uD83D\uDE2E \uD83D\uDE2F \uD83D\uDE34 \uD83D\uDE1B \uD83D\uDE15 \uD83D\uDE1F \uD83D\uDE26 \uD83D\uDE27 \uD83D\uDE2C \uD83D\uDE42 \uD83D\uDE41 \uD83D\uDE01 \uD83D\uDE02 \uD83D\uDE03 \uD83D\uDE04 \uD83D\uDE05 \uD83D\uDE06 \uD83D\uDE09 \uD83D\uDE0A \uD83D\uDE0B \uD83D\uDE0E \uD83D\uDE0D \uD83D\uDE18 \uD83D\uDE1A \uD83D\uDE10 \uD83D\uDE36 \uD83D\uDE0F \uD83D\uDE23 \uD83D\uDE25 \uD83D\uDE2A \uD83D\uDE2B \uD83D\uDE0C \uD83D\uDE1C \uD83D\uDE1D \uD83D\uDE12 \uD83D\uDE13 \uD83D\uDE14 \uD83D\uDE32 \uD83D\uDE16 \uD83D\uDE1E \uD83D\uDE24 \uD83D\uDE22 \uD83D\uDE2D \uD83D\uDE28 \uD83D\uDE29 \uD83D\uDE30 \uD83D\uDE31 \uD83D\uDE33 \uD83D\uDE35 \uD83D\uDE21 \uD83D\uDE20 \uD83D\uDE37 \uD83D\uDE00 \uD83D\uDE17 \uD83D\uDE19 \uD83D\uDE11 \uD83D\uDE2E \uD83D\uDE2F \uD83D\uDE34 \uD83D\uDE1B \uD83D\uDE15 \uD83D\uDE1F \uD83D\uDE26 \uD83D\uDE27 \uD83D\uDE2C ☺️ ☹️ \uD83D\uDE07 \uD83D\uDE08 \uD83D\uDC7F \uD83D\uDC79 \uD83D\uDC7A \uD83D\uDC80 \uD83D\uDC7B \uD83D\uDC7D \uD83D\uDC7E \uD83D\uDCA9 \uD83D\uDE3A \uD83D\uDE38 \uD83D\uDE39 \uD83D\uDE3B \uD83D\uDE3C \uD83D\uDE3D \uD83D\uDE40 \uD83D\uDE3F \uD83D\uDE3E \uD83D\uDE48 \uD83D\uDE49 \uD83D\uDE4A \uD83D\uDC76 \uD83D\uDC66 \uD83D\uDC67 \uD83D\uDC71 \uD83D\uDC68 \uD83D\uDC69 \uD83D\uDC74 \uD83D\uDC75 \uD83D\uDC6E \uD83D\uDC82 \uD83D\uDC77 \uD83D\uDC78 \uD83D\uDC73 \uD83D\uDC72 \uD83D\uDC70 \uD83D\uDC7C \uD83C\uDF85 \uD83D\uDE4D \uD83D\uDE4E \uD83D\uDE45 \uD83D\uDE46 \uD83D\uDC81 \uD83D\uDE4B \uD83D\uDE47 \uD83D\uDC86 \uD83D\uDC87 \uD83D\uDEB6 \uD83C\uDFC3 \uD83D\uDC83 \uD83D\uDC6F \uD83D\uDEC0 \uD83D\uDC64 \uD83D\uDC65 \uD83C\uDFC7 \uD83C\uDFC2 \uD83C\uDFC4 \uD83D\uDEA3 \uD83C\uDFCA \uD83D\uDEB4 \uD83D\uDEB5 \uD83D\uDD75️ \uD83D\uDECC \uD83D\uDD74️ \uD83D\uDDE3️ \uD83C\uDFCC️ \uD83C\uDFCB️ \uD83C\uDFCE️ \uD83C\uDFCD️ \uD83D\uDD90️ \uD83D\uDC41️ \uD83D\uDDE8️ \uD83D\uDDEF️ \uD83D\uDD73️ \uD83D\uDD76️ \uD83D\uDECD️ \uD83D\uDC3F️ \uD83D\uDD4A️ \uD83D\uDD77️ \uD83D\uDD78️ \uD83C\uDFF5️ \uD83C\uDF36️ \uD83C\uDF7D️ \uD83D\uDDFA️ \uD83C\uDFD4️ \uD83C\uDFD5️ \uD83C\uDFD6️ \uD83C\uDFDC️ \uD83C\uDFDD️ \uD83C\uDFDE️ \uD83C\uDFDF️ \uD83C\uDFDB️ \uD83C\uDFD7️ \uD83C\uDFD8️ \uD83C\uDFDA️ \uD83C\uDFD9️ \uD83D\uDEE3️ \uD83D\uDEE4️ \uD83D\uDEE2️ \uD83D\uDEF3️ \uD83D\uDEE5️ \uD83D\uDEE9️ \uD83D\uDEEB \uD83D\uDEEC \uD83D\uDEF0️ \uD83D\uDECE️ \uD83D\uDD70️ \uD83C\uDF21️ \uD83C\uDF24️ \uD83C\uDF25️ \uD83C\uDF26️ \uD83C\uDF27️ \uD83C\uDF28️ \uD83C\uDF29️ \uD83C\uDF2A️ \uD83C\uDF2B️ \uD83C\uDF2C️ \uD83C\uDF97️ \uD83C\uDF9F️ \uD83C\uDF96️ \uD83C\uDFC5 \uD83D\uDD79️ \uD83D\uDDBC️ \uD83C\uDF99️ \uD83C\uDF9A️ \uD83C\uDF9B️ \uD83D\uDDA5️ \uD83D\uDDA8️ \uD83D\uDDB1️ \uD83D\uDDB2️ \uD83C\uDF9E️ \uD83D\uDCFD️ \uD83D\uDCF8 \uD83D\uDD6F️ \uD83D\uDDDE️ \uD83C\uDFF7️ \uD83D\uDDF3️ \uD83D\uDD8B️ \uD83D\uDD8A️ \uD83D\uDD8C️ \uD83D\uDD8D️ \uD83D\uDDC2️ \uD83D\uDDD2️ \uD83D\uDDD3️ \uD83D\uDD87️ \uD83D\uDDC3️ \uD83D\uDDD1️ \uD83D\uDDDD️ \uD83D\uDEE0️ \uD83D\uDDE1️ \uD83D\uDEE1️ \uD83D\uDDDC️ \uD83D\uDECF️ \uD83D\uDECB️ \uD83C\uDFF4 \uD83C\uDFF3️";
    private static final int DESIRED_COLUMN_WIDTH_DP = 50;
    private static final String FINISHED = "finished";

    protected KeyText mKeySpace;
    protected KeyBackspace mKeyBackspace;
    protected KeyImage mKeyFinished;
    protected GridView mEmojiGridView;
    private int mColumnWidth;

    public KeyboardEmoji(Context context) {
        super(context);
        init(context);
    }

    public KeyboardEmoji(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardEmoji(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public KeyboardEmoji(Context context, StyleBuilder style) {
        super(context, style);
        init(context);
    }

    protected void init(Context context) {

        // | del |                     |    Row 1
        // |space|     Emoji grid      |    Row 2
        // | kb  |                     |    Row 3

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{1, 1, 1};
        mKeyWeights = new float[]{
                1 / 5f,            // row 0
                1 / 5f,            // row 1
                1 / 5f};           // row 2


        instantiateKeys(context);
        setKeyImages();
        setKeyValues();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
        initGridView(context);
    }

    private void instantiateKeys(Context context) {
        mKeyBackspace = new KeyBackspace(context);
        mKeySpace = new KeyText(context);
        mKeyFinished = new KeyImage(context);
    }

    private void setKeyImages() {
        mKeyBackspace.setImage(getBackspaceImage(), getPrimaryTextColor());
        mKeyFinished.setImage(getBackImage(), getPrimaryTextColor());
    }

    private void setKeyValues() {
        mKeySpace.setText(" ");
        mKeyFinished.setText(FINISHED);
    }

    private void setListeners() {
        mKeyBackspace.setKeyListener(this);
        mKeySpace.setKeyListener(this);
        mKeyFinished.setKeyListener(this);
    }

    private void addKeysToKeyboard() {
        addView(mKeyBackspace);
        addView(mKeySpace);
        addView(mKeyFinished);
    }

    private void initGridView(Context context) {
        mEmojiGridView = new GridView(context);
        mEmojiGridView.setAdapter(new EmojiGridAdapter(getContext(), getEmojis()));
        addView(mEmojiGridView);
    }

    private List<String> getEmojis() {
        String[] splitStr = EMOJI_LIST.split(" ");
        return new ArrayList<>(Arrays.asList(splitStr));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        layoutGridView();
    }

    private void layoutGridView() {

        final int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        float keyWidth = totalWidth * mKeyWeights[0];
        float gridWidth = totalWidth - keyWidth;
        float x = getPaddingLeft() + keyWidth;
        float y = getPaddingTop();
        mColumnWidth = getIdealColumnWidth(gridWidth);
        mEmojiGridView.setColumnWidth(mColumnWidth);
        mEmojiGridView.setNumColumns(GridView.AUTO_FIT);
        mEmojiGridView.setStretchMode(GridView.NO_STRETCH);
        mEmojiGridView.measure(MeasureSpec.makeMeasureSpec((int) gridWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY));
        mEmojiGridView.layout((int) x, (int) y, (int) (x + gridWidth), (int) (y + totalHeight));
    }

    private int getIdealColumnWidth(float gridWidth) {
        float desiredColWidthPx = DESIRED_COLUMN_WIDTH_DP * getResources().getDisplayMetrics().density;
        int numberOfColumns = (int) (gridWidth / desiredColWidthPx);
        return (int) (gridWidth / numberOfColumns);
    }

    @Override
    public void onKeyInput(String text) {
        if (text.equals(FINISHED)) {
            finishKeyboard();
            return;
        }
        super.onKeyInput(text);
    }

    @Override
    public String getDisplayName() {
        return DEFAULT_DISPLAY_NAME;
    }

    @Override
    public void onKeyboardKeyClick() {}

    @Override
    public List<PopupKeyCandidate> getPopupCandidates(Key key) {
        return null;
    }

    class EmojiGridAdapter extends BaseAdapter implements Key.KeyListener {

        private Context mContext;
        private List<String> mEmojiList;

        EmojiGridAdapter(Context c, List<String> emojis) {
            this.mContext = c;
            this.mEmojiList = emojis;
        }

        public int getCount() {
            return mEmojiList.size();
        }

        public String getItem(int position) {
            return mEmojiList.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        class GridItemViewHolder {
            KeyText emojiKey;
            GridItemViewHolder(View v) {
                emojiKey = (KeyText) v;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View gridItem = convertView;
            GridItemViewHolder holder;

            if (gridItem == null) {
                gridItem = getEmojiKey();
                holder = new GridItemViewHolder(gridItem);
                gridItem.setTag(holder);
            } else { // recycling
                holder = (GridItemViewHolder) gridItem.getTag();
            }

            String emoji = getItem(position);
            holder.emojiKey.setText(emoji);

            return gridItem;
        }

        private KeyText getEmojiKey() {
            KeyText key = new KeyText(mContext);
            key.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));
            key.setTextSize(getPrimaryTextSize());
            key.setKeyColor(getKeyColor());
            key.setPressedColor(getKeyPressedColor());
            key.setBorderColor(getBorderColor());
            key.setBorderWidth(getBorderWidth());
            key.setBorderRadius(getBorderRadius());
            key.setIsRotatedPrimaryText(false);
            int spacing = getKeySpacing();
            key.setPadding(spacing, spacing, spacing, spacing);
            key.setKeyListener(this);
            return key;
        }

        // KeyListener methods

        @Override
        public void onKeyInput(String text) {
            KeyboardEmoji.this.onKeyInput(text);
        }

        @Override
        public void onBackspace() {}
        @Override
        public boolean getIsShowingPopup() {
            return false;
        }
        @Override
        public void showPopup(Key key, int xPosition) {}
        @Override
        public void updatePopup(int xPosition) {}
        @Override
        public void finishPopup(int xPosition) {}
        @Override
        public void onKeyboardKeyClick() {}
        @Override
        public void onNewKeyboardChosen(int xPositionOnPopup) {}
        @Override
        public void onShiftChanged(boolean isShiftOn) {}
    }
}
