package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
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
import android.view.KeyEvent;
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

public class KeyboardAeiou extends Keyboard {

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
    private KeyImage mKeyKeyboard;
    private KeyText mKeyComma;
    private KeyText mKeySpace;
    private KeyText mKeySuffix;
    private KeyImage mKeyBackspace;
    private KeyImage mKeyReturn;

    // This will map the button resource id to the String value that we want to
    // input when that key is clicked.
    private Map<Key, String> keyValues = new HashMap<>();

    // Our communication link to the EditText/MongolEditText
    private InputConnection inputConnection;
    private StringBuilder mComposing = new StringBuilder();

    private int mPopupBackgroundColor = Color.WHITE;
    private int mPopupHighlightColor = Color.GRAY;

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
        float subTextSize = textSize / 2;
        int textColor = Color.BLACK;
        int subTextColor = Color.parseColor("#61000000"); // alpha black
        int keyColor = Color.LTGRAY;
        int pressedColor = Color.GRAY;
        int borderColor = Color.BLACK;
        int borderWidth = 0;
        int borderRadius = 5;
        int padding = 2;

        // Row 1

        mKeyA = new KeyText(context);
        mKeyA.setText(MongolCode.Uni.A);
        mKeyA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyA, String.valueOf(MongolCode.Uni.A));
        addView(mKeyA);

        mKeyE = new KeyText(context);
        mKeyE.setText(MongolCode.Uni.E);
        mKeyE.setSubText(MongolCode.Uni.EE);
        mKeyE.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyE, String.valueOf(MongolCode.Uni.E));
        addView(mKeyE);

        mKeyI = new KeyText(context);
        mKeyI.setText(MongolCode.Uni.I);
        mKeyI.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyI, String.valueOf(MongolCode.Uni.I));
        addView(mKeyI);

        mKeyO = new KeyText(context);
        mKeyO.setText(MongolCode.Uni.U);
        mKeyO.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyO, String.valueOf(MongolCode.Uni.U));
        addView(mKeyO);

        mKeyU = new KeyText(context);
        String displayU =
                String.valueOf(MongolCode.Uni.UE) +
                        String.valueOf(MongolCode.Uni.ZWJ);
        mKeyU.setText(displayU);
        mKeyU.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyU, String.valueOf(MongolCode.Uni.UE));
        addView(mKeyU);

        // Row 2

        mKeyNA = new KeyText(context);
        mKeyNA.setText(MongolCode.Uni.NA);
        mKeyNA.setSubText(MongolCode.Uni.ANG);
        mKeyNA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyNA, String.valueOf(MongolCode.Uni.NA));
        addView(mKeyNA);

        mKeyBA = new KeyText(context);
        mKeyBA.setText(MongolCode.Uni.BA);
        //String subTextDisplay = "" + MongolCode.Uni.PA + " " + MongolCode.Uni.FA;
        mKeyBA.setSubText(MongolCode.Uni.PA);
        mKeyBA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyBA, String.valueOf(MongolCode.Uni.BA));
        addView(mKeyBA);

        mKeyQA = new KeyText(context);
        mKeyQA.setText(MongolCode.Uni.QA);
        mKeyQA.setSubText(MongolCode.Uni.HAA);
        mKeyQA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyQA, String.valueOf(MongolCode.Uni.QA));
        addView(mKeyQA);

        mKeyGA = new KeyText(context);
        mKeyGA.setText(MongolCode.Uni.GA);
        mKeyGA.setSubText(MongolCode.Uni.KA);
        mKeyGA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyGA, String.valueOf(MongolCode.Uni.GA));
        addView(mKeyGA);

        mKeyMA = new KeyText(context);
        mKeyMA.setText(MongolCode.Uni.MA);
        mKeyMA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyMA, String.valueOf(MongolCode.Uni.MA));
        addView(mKeyMA);

        mKeyLA = new KeyText(context);
        mKeyLA.setText(MongolCode.Uni.LA);
        mKeyLA.setSubText(MongolCode.Uni.LHA);
        mKeyLA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyLA, String.valueOf(MongolCode.Uni.LA));
        addView(mKeyLA);

        // Row 3

        mKeySA = new KeyText(context);
        mKeySA.setText(MongolCode.Uni.SA);
        mKeySA.setSubText(MongolCode.Uni.SHA);
        mKeySA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeySA, String.valueOf(MongolCode.Uni.SA));
        addView(mKeySA);

        mKeyTADA = new KeyText(context);
        mKeyTADA.setText(MongolCode.Uni.TA);
        mKeyTADA.setSubText(MongolCode.Uni.DA);
        mKeyTADA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyTADA, String.valueOf(MongolCode.Uni.DA)); // make DA the default
        addView(mKeyTADA);

        mKeyCHA = new KeyText(context);
        mKeyCHA.setText(MongolCode.Uni.CHA);
        //subTextDisplay = "" + MongolCode.Uni.TSA + " " + MongolCode.Uni.CHI;
        mKeyCHA.setSubText(MongolCode.Uni.TSA);
        mKeyCHA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyCHA, String.valueOf(MongolCode.Uni.CHA));
        addView(mKeyCHA);

        mKeyJA = new KeyText(context);
        mKeyJA.setText(MongolCode.Uni.JA);
        //subTextDisplay = "" + MongolCode.Uni.ZA + " " + MongolCode.Uni.ZHI;
        mKeyJA.setSubText(MongolCode.Uni.ZA);
        mKeyJA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyJA, String.valueOf(MongolCode.Uni.JA));
        addView(mKeyJA);

        mKeyYA = new KeyText(context);
        mKeyYA.setText(MongolCode.Uni.YA);
        mKeyYA.setSubText(MongolCode.Uni.WA);
        mKeyYA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyYA, String.valueOf(MongolCode.Uni.YA));
        addView(mKeyYA);

        mKeyRA = new KeyText(context);
        mKeyRA.setText(MongolCode.Uni.RA);
        mKeyRA.setSubText(MongolCode.Uni.ZRA);
        mKeyRA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyRA, String.valueOf(MongolCode.Uni.RA));
        addView(mKeyRA);

        // Row 4

        mKeyKeyboard = new KeyImage(context);
        mKeyKeyboard.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_keyboard_black_48dp));
        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
        addView(mKeyKeyboard);

        mKeyComma = new KeyText(context);
        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        mKeyComma.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyComma, String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA));
        addView(mKeyComma);

        mKeySpace = new KeyText(context);
        mKeySpace.setText(" ");
        String subtextDisplay = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";
        mKeySpace.setSubText(subtextDisplay);
        mKeySpace.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeySpace, " ");
        addView(mKeySpace);

        mKeyReturn = new KeyImage(context);
        mKeyReturn.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_keyboard_return_black_48dp));
        mKeyReturn.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyReturn, "\n");
        addView(mKeyReturn);

        mKeyBackspace = new KeyImage(context);
        mKeyBackspace.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_keyboard_backspace_black_48dp));
        mKeyBackspace.setOnTouchListener(handleBackspace);
        addView(mKeyBackspace);


        for (int i = 0; i < getChildCount(); i++) {
            Key child = (Key) getChildAt(i);
            if (child instanceof KeyText) {
                ((KeyText) child).setTypeFace(typeface);
                ((KeyText) child).setTextSize(textSize);
                ((KeyText) child).setSubTextSize(subTextSize);
                ((KeyText) child).setTextColor(textColor);
                ((KeyText) child).setSubTextColor(subTextColor);
            } else if (child instanceof KeyImage) {

            }

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

        // | A  | E  | I |  O |  U |    Row 1
        // | N | B | Q | G | M | L |    Row 2
        // | S | D | Ch| J | Y | R |    Row 3
        // |kb | , | space |ret|del|    Row 4

        final int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        int[] numberOfKeysInRow = {5, 6, 6, 5};
        // the key weights for each row should sum to 1
        float[] keyWeight = {
                1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f,           // row 0
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,     // row 1
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,     // row 2
                1 / 6f, 1 / 6f, 2 / 6f, 1 / 6f, 1 / 6f};          // row 3
        int numberOfRows = numberOfKeysInRow.length;

        float x = getPaddingLeft();
        float y = getPaddingTop();
        int keyIndex = 0;
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {

            int end = keyIndex + numberOfKeysInRow[rowIndex];
            for (int i = keyIndex; i < end; i++) {
                View child = getChildAt(keyIndex);

                float keyWidth = totalWidth * keyWeight[keyIndex];
                float keyHeight = totalHeight / numberOfRows;
                child.measure(MeasureSpec.makeMeasureSpec((int) keyWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec((int) keyHeight, MeasureSpec.EXACTLY));

                child.layout((int) x, (int) y, (int) (x + keyWidth), (int) (y + keyHeight));
                //x += keyWidth;
                x += keyWidth;
                keyIndex++;
            }

            x = getPaddingLeft();
            y += (float) totalHeight / numberOfRows;
        }

    }

    protected View.OnTouchListener handleBackspace = new View.OnTouchListener() {

        private Handler handler;
        final int INITIAL_DELAY = 500;
        final int REPEAT_DELAY = 50;

        @Override
        public boolean onTouch(View view, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    view.setPressed(true);
                    doBackspace();
                    if (handler != null)
                        return true;
                    handler = new Handler();
                    handler.postDelayed(actionBackspace, INITIAL_DELAY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                default:
                    view.setPressed(false);
                    if (handler == null)
                        return true;
                    handler.removeCallbacks(actionBackspace);
                    handler = null;
                    break;
            }

            return true;
        }

        private void doBackspace(){
            if (inputConnection == null) return;

            if (mComposing.length() > 0) {
                inputConnection.commitText("", 1);
                mComposing.setLength(0);
            } else {

                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));

                // We could also do this with inputConnection.deleteSurroundingText(1, 0)
                // but then we would need to be careful of not deleting too much
                // and not deleting half a serogate pair.
                // see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#deleteSurroundingText(int,%20int)
                // see also https://stackoverflow.com/a/45182401
            }


        }

        Runnable actionBackspace = new Runnable() {
            @Override
            public void run() {
                doBackspace();
                handler.postDelayed(this, REPEAT_DELAY);
            }
        };

    };

    protected View.OnTouchListener textKeyTouchListener = new View.OnTouchListener() {

        Handler handler;
        final int LONGPRESS_THRESHOLD = 500; // milliseconds


        PopupKeyCandidates popupView;
        int popupWidth;
        PopupWindow popupWindow;
        //CurrentFvsSelection currentFvsSelection = CurrentFvsSelection.FVS1;


        @Override
        public boolean onTouch(View view, final MotionEvent event) {

            if (event.getPointerCount() > 1) return false;

            final Key key = (Key) view;
            int action = event.getActionMasked();

            switch (action) {
                case (MotionEvent.ACTION_DOWN):

                    key.setPressed(true);

                    Candidates candidates = getPopupCandidates(key);
                    if (candidates != null && candidates.unicode != null
                            && candidates.unicode.length > 0) {
                        int x = (int) event.getRawX();
                        preparePopup(key, candidates, x);
                    }


                    return true;
                case (MotionEvent.ACTION_MOVE):

                    if (popupView != null) {
                        int x = (int) event.getRawX();
                        popupView.updateTouchPosition(x);
                    }


                    return true;
                case (MotionEvent.ACTION_UP):

                    if (inputConnection == null) {
                        dismissPopup(key, event);
                        return true;
                    }


                    // handle popups
                    if (popupView != null) {
                        dismissPopup(key, event);
                    }
                    // normal keys

                    else if (view == mKeyKeyboard) {

                    } else {
                        String inputText = keyValues.get(key);

                        // handle composing
                        if (MongolCode.isMongolian(inputText.charAt(0))) {
                            inputConnection.commitText(mComposing, 1);
                        } else {
                            inputConnection.finishComposingText();
                        }
                        mComposing.setLength(0);

                        // TA/DA defaults to DA except in the INITIAL location
                        char prevChar = getPreviousChar();
                        if (inputText.equals(String.valueOf(MongolCode.Uni.DA))
                                && !MongolCode.isMongolian(prevChar)) {
                            inputText = String.valueOf(MongolCode.Uni.TA);
                        }


                        inputConnection.commitText(inputText, 1);
                    }

                    key.setPressed(false);
                    if (handler != null) handler.removeCallbacksAndMessages(null);
                    return true;
                default:
                    dismissPopup(key, event);
                    return false;
            }
        }

        private void preparePopup(final Key key, final Candidates candidates, final int xPosition) {

            //if (handler != null) handler.removeCallbacks(null);


            final Runnable runnableCode = new Runnable() {
                @Override
                public void run() {

                    if (popupWindow != null) return;

                    // get the popup view
                    popupView = new PopupKeyCandidates(getContext());
                    popupView.setBackgroundColor(mPopupBackgroundColor);

                    // update the popup view with the candidate choices
                    if (candidates == null || candidates.unicode == null) return;
                    popupView.setCandidates(candidates.unicode);
                    if (candidates.display == null) {
                        popupView.setDisplayCandidates(candidates.unicode, PopupKeyCandidates.DEFAULT_TEXT_SIZE);
                    } else {
                        popupView.setDisplayCandidates(candidates.display, PopupKeyCandidates.DEFAULT_TEXT_SIZE);
                    }

                    //setCandidates(key, popupView);
                    popupView.setHighlightColor(mPopupHighlightColor);
                    //popupView.setHeight(key.getHeight());
                    //String[] candidates = getDisplayCandidates(key);
                    //int height = key.getHeight();
                    //popupView.init(candidates, 30, height, mPopupHighlightColor);

                    popupWindow = new PopupWindow(popupView,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    int location[] = new int[2];
                    key.getLocationOnScreen(location);
                    int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    popupView.measure(measureSpec, measureSpec);
                    popupWidth = popupView.getMeasuredWidth();
                    int spaceAboveKey = key.getHeight() / 4;
                    int x = xPosition - popupWidth / popupView.getChildCount() / 2;
                    //int locationX = location[0]
                    popupWindow.showAtLocation(key, Gravity.NO_GRAVITY,
                            x, location[1] - popupView.getMeasuredHeight() - spaceAboveKey);


                    // highlight current item (after the popup window has loaded)
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int x = xPosition;
                            popupView.updateTouchPosition(x);
                        }
                    });

                }
            };

            if (handler != null) handler.removeCallbacksAndMessages(null);
            else handler = new Handler();
            handler.postDelayed(runnableCode, LONGPRESS_THRESHOLD);
        }

        private void dismissPopup(Key key, MotionEvent event) {

            key.setPressed(false);

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            if (popupWindow == null) return;

            int x = (int) event.getRawX();

            CharSequence selectedItem = popupView.getCurrentItem(x);
            if (!TextUtils.isEmpty(selectedItem)) {

                inputConnection.beginBatchEdit();

                if (mComposing.length() > 0) {
                    inputConnection.commitText(mComposing, 1);
                    mComposing.setLength(0);
                }

                // add composing text for certain medials to avoid confusion with finals
                if (selectedItem.equals(MEDIAL_A_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_A_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_A_FVS1);
//                } else if (selectedItem.equals(INITIAL_I_SUFFIX)) {
//                    inputConnection.setComposingText(INITIAL_I_SUFFIX_COMPOSING, 1);
//                    mComposing.append(INITIAL_I_SUFFIX);
                } else if (selectedItem.equals(MEDIAL_I_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_I_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_I_FVS1);
                } else if (selectedItem.equals(MEDIAL_ZWJ_I)) {
                    inputConnection.setComposingText(MEDIAL_ZWJ_I_COMPOSING, 1);
                    mComposing.append(MEDIAL_ZWJ_I);
                } else if (selectedItem.equals(MEDIAL_U_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_U_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_U_FVS1);
                } else if (selectedItem.equals(MEDIAL_UE_FVS2)) {
                    inputConnection.setComposingText(MEDIAL_UE_FVS2_COMPOSING, 1);
                    mComposing.append(MEDIAL_UE_FVS2);
                } else if (selectedItem.equals(MEDIAL_DOTTED_NA)) {
                    inputConnection.setComposingText(MEDIAL_DOTTED_NA_COMPOSING, 1);
                    mComposing.append(MEDIAL_DOTTED_NA);
                } else if (selectedItem.equals(MEDIAL_TA_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_TA_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_TA_FVS1);
                } else if (selectedItem.equals(YA_FVS1)) {
                    inputConnection.setComposingText(YA_FVS1_COMPOSING, 1);
                    mComposing.append(YA_FVS1);
                } else {
                    inputConnection.commitText(selectedItem, 1);
                }

                inputConnection.endBatchEdit();

            }


            popupWindow.dismiss();
            popupView = null;
            popupWindow = null;

        }


    };

    public void onUpdateSelection(int oldSelStart,
                                  int oldSelEnd,
                                  int newSelStart,
                                  int newSelEnd,
                                  int candidatesStart,
                                  int candidatesEnd) {

        // in the Android source InputMethodService also handles Extracted Text here
        https:
        //android.googlesource.com/platform/frameworks/base/+/fb13abd800cd610c7f46815848545feff83e5748/core/java/android/inputmethodservice/InputMethodService.java#1529
        // https://android.googlesource.com/platform/development/+/master/samples/SoftKeyboard/src/com/example/android/softkeyboard/SoftKeyboard.java#274

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

    private char getPreviousChar() {
        if (inputConnection == null) return 0;
        CharSequence previous = inputConnection.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    private Candidates getPopupCandidates(Key key) {
        // these are the choices to display in the popup (and corresponding unicode values)
        Candidates candidates = null;

        // get the appropriate candidates based on the key pressed
        if (key == mKeyA) {
            candidates = getCandidatesForA(isIsolateOrInitial());
        } else if (key == mKeyE) {
            candidates = getCandidatesForE(isIsolateOrInitial());
        } else if (key == mKeyI) {
            candidates = getCandidatesForI(isIsolateOrInitial());
        } else if (key == mKeyO) {
            candidates = getCandidatesForO(isIsolateOrInitial());
        } else if (key == mKeyU) {
            candidates = getCandidatesForU(isIsolateOrInitial());
        } else if (key == mKeyNA) {
            candidates = getCandidatesForNA(isIsolateOrInitial());
        } else if (key == mKeyBA) {
            candidates = getCandidatesForBA();
        } else if (key == mKeyQA) {
            candidates = getCandidatesForQA();
        } else if (key == mKeyGA) {
            candidates = getCandidatesForGA(isIsolateOrInitial());
        } else if (key == mKeyLA) {
            candidates = getCandidatesForLA();
        } else if (key == mKeySA) {
            candidates = getCandidatesForSA();
        } else if (key == mKeyTADA) {
            candidates = getCandidatesForTADA(isIsolateOrInitial());
        } else if (key == mKeyCHA) {
            candidates = getCandidatesForCHA();
        } else if (key == mKeyJA) {
            candidates = getCandidatesForJA();
        } else if (key == mKeyYA) {
            candidates = getCandidatesForYA(isIsolateOrInitial());
        } else if (key == mKeyRA) {
            candidates = getCandidatesForRA();
        } else if (key == mKeyComma) {
            candidates = getCandidatesForComma();
        } else if (key == mKeySpace) {
            candidates = getCandidatesForSpace();
//        } else {
//            candidates = new Candidates();
//            candidates.unicode = new String[]{"A", "a"};

        }

        return candidates;
    }

//    private void setCandidates(Key key, PopupKeyCandidates popupView) {
//
//        Candidates candidates = getPopupCandidates(key);
//
//        // update the popup view with the candidate choices
//        if (candidates == null || candidates.unicode == null) return;
//        popupView.setCandidates(candidates.unicode);
//        if (candidates.display == null) {
//            popupView.setDisplayCandidates(candidates.unicode, PopupKeyCandidates.DEFAULT_TEXT_SIZE);
//        } else {
//            popupView.setDisplayCandidates(candidates.display, PopupKeyCandidates.DEFAULT_TEXT_SIZE);
//        }
//    }

    private boolean isIsolateOrInitial() {
        if (inputConnection == null) return true;
        CharSequence before = inputConnection.getTextBeforeCursor(2, 0);
        CharSequence after = inputConnection.getTextAfterCursor(2, 0);
        if (before == null || after == null) return true;
        // get Mongol word location at cursor input
        MongolCode.Location location = MongolCode.getLocation(before, after);
        return location == MongolCode.Location.ISOLATE ||
                location == MongolCode.Location.INITIAL;
        //return isIsolateOrInitial;
    }

    private Candidates getCandidatesForA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
        } else { // medial || final
            char previousChar = getPreviousChar();
            if (MongolCode.isMvsConsonant(previousChar)) {
                // include MVS
                can.unicode = new String[]{
                        "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
                        "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
            } else {
                can.unicode = new String[]{
                        "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
            }

        }
        return can;
    }

    private Candidates getCandidatesForE(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
            can.unicode = new String[]{"" + MongolCode.Uni.EE};
        } else { // medial || final
            char previousChar = getPreviousChar();
            if (MongolCode.isMvsConsonant(previousChar)
                    && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
                // include MVS
                can.unicode = new String[]{
                        "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
                        "" + MongolCode.Uni.EE};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E,
                        "" + MongolCode.Uni.EE};
            } else {
                can.unicode = new String[]{"" + MongolCode.Uni.EE};
            }

        }
        return can;
    }

    private Candidates getCandidatesForI(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
//            can.unicode = new String[]{
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I,
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I};
//            can.display = new String[]{
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I,
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I + MongolCode.Uni.ZWJ};
        } else {// medial/final
            char prevChar = getPreviousChar();
            if (MongolCode.isVowel(prevChar)) {
                can.unicode = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I, // override double tooth I after vowel
                        "" + MongolCode.Uni.I + MongolCode.Uni.FVS1};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
            } else {
                can.unicode = new String[]{"" +
                        "" + MongolCode.Uni.I + MongolCode.Uni.FVS1};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
            }

        }
        return can;
    }

    private Candidates getCandidatesForO(boolean isIsolateOrInitial) {
        if (!isIsolateOrInitial) { // medial/final
            Candidates can = new Candidates();
            can.unicode = new String[]{"" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.U + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1};
            return can;
        }
        return null;
    }

    private Candidates getCandidatesForU(boolean isIsolateOrInitial) {
        if (!isIsolateOrInitial) { // medial/final
            Candidates can = new Candidates();
            can.unicode = new String[]{"" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                    "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1};
            return can;
        }
        return null;
    }

    private Candidates getCandidatesForNA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
            can.unicode = new String[]{"" + MongolCode.Uni.ANG};
        } else { // medial/final
            can.unicode = new String[]{"" + MongolCode.Uni.ANG,
                    "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ, // only(?) way to override dotted N before vowel in Unicode 9.0
                    "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.ANG,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
        }
        return can;
    }

    private Candidates getCandidatesForBA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{"" + MongolCode.Uni.PA, "" + MongolCode.Uni.FA};
        return can;
    }

    private Candidates getCandidatesForQA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{"" + MongolCode.Uni.HAA};
        return can;
    }

    private Candidates getCandidatesForGA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
            can.unicode = new String[]{"" + MongolCode.Uni.KA};
        } else { // medial/final
            can.unicode = new String[]{"" + MongolCode.Uni.KA,
                    "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1, // see note on MongolCode(FINA_GA_FVS1)
                    "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2}; // see note on MongolCode(FINA_GA_FVS2)
            can.display = new String[]{
                    "" + MongolCode.Uni.KA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2};
        }
        return can;
    }

    private Candidates getCandidatesForLA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{"" + MongolCode.Uni.LHA};
        return can;
    }

    private Candidates getCandidatesForSA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{"" + MongolCode.Uni.SHA};
        return can;
    }

    private Candidates getCandidatesForTADA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
            char prevChar = getPreviousChar();
            if (prevChar == MongolCode.Uni.NNBS) {
                can.unicode = new String[]{"" + MongolCode.Uni.DA};
            } else {
                can.unicode = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            }
            can.display = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        } else { // medial/final
            can.unicode = new String[]{
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.TA,
                    "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                            MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
        }
        return can;
    }

    private Candidates getCandidatesForCHA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{
                "" + MongolCode.Uni.TSA,
                "" + MongolCode.Uni.CHI,};
        return can;
    }

    private Candidates getCandidatesForJA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{
                "" + MongolCode.Uni.ZA,
                "" + MongolCode.Uni.ZHI};
        return can;
    }

    private Candidates getCandidatesForYA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();
        if (isIsolateOrInitial) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
        } else { // medial/final
            can.unicode = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
        }

        return can;
    }

    private Candidates getCandidatesForRA() {
        Candidates can = new Candidates();
        can.unicode = new String[]{"" + MongolCode.Uni.ZRA};
        return can;
    }

    private Candidates getCandidatesForComma() {
        Candidates can = new Candidates();
        can.unicode = new String[]{
                "" + MongolCode.Uni.VERTICAL_QUESTION_MARK,
                "" + MongolCode.Uni.MONGOLIAN_FULL_STOP,
                "" + MongolCode.Uni.VERTICAL_EXCLAMATION_MARK};
        return can;
    }

    private Candidates getCandidatesForSpace() {
        Candidates can = new Candidates();
        String previousWord = getPreviousMongolWord();
        if (TextUtils.isEmpty(previousWord)) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.NNBS};
            return can;

        }
        char lastChar = previousWord.charAt(previousWord.length() - 1);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
        if (gender == null) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.NNBS};
            return can;
        }
        String duTuSuffix = MongolCode.getSuffixTuDu(gender, lastChar);
        String iYiSuffix = MongolCode.getSuffixYiI(lastChar);
        String yinUnUSuffix = MongolCode.getSuffixYinUnU(gender, lastChar);
        String achaSuffix = MongolCode.getSuffixAchaEche(gender);
        String barIyarSuffix = MongolCode.getSuffixBarIyar(gender, lastChar);
        String taiSuffix = MongolCode.getSuffixTaiTei(gender);
        String uuSuffix = MongolCode.getSuffixUu(gender);
        String banIyanSuffix = MongolCode.getSuffixBanIyan(gender, lastChar);
        String udSuffix = MongolCode.getSuffixUd(gender);

        can.unicode = new String[]{
                "" + MongolCode.Uni.NNBS,
                uuSuffix,
                yinUnUSuffix,
                iYiSuffix,
                duTuSuffix,
                barIyarSuffix,
                banIyanSuffix,
                achaSuffix,
                udSuffix};
        return can;
    }

    // this may not actually return a whole word if the word is very long
    private String getPreviousMongolWord() {
        if (inputConnection == null) return "";
        int numberOfCharsToFetch = 20;
        CharSequence previous = inputConnection.getTextBeforeCursor(numberOfCharsToFetch, 0);
        if (TextUtils.isEmpty(previous)) return "";
        int startIndex = previous.length() - 1;
        char charAtIndex = previous.charAt(startIndex);
        if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) startIndex--;
        StringBuilder mongolWord = new StringBuilder();
        for (int i = startIndex; i >= 0; i--) {
            charAtIndex = previous.charAt(i);
            if (MongolCode.isMongolian(charAtIndex)) {
                mongolWord.append(charAtIndex);
            } else if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) {
                break;
            }
        }
        return mongolWord.toString();
    }

    private class Candidates {
        String[] unicode;
        String[] display;
    }


    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    // These are special popup chose characters. They are being converted to a
    // temporary medial composing form so that the automatic Unicode rendering
    // will not confuse users (because they would get displayed as a final otherwise).

    private static final String MEDIAL_A_FVS1 = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1;
    private static final String MEDIAL_A_FVS1_COMPOSING = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    //    private static final String INITIAL_I_SUFFIX = "" + MongolCode.Uni.NNBS + MongolCode.Uni.I;
//    private static final String INITIAL_I_SUFFIX_COMPOSING = "" + MongolCode.Uni.NNBS + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_I_FVS1 = "" + MongolCode.Uni.I + MongolCode.Uni.FVS1;
    private static final String MEDIAL_I_FVS1_COMPOSING = "" + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_ZWJ_I = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
    private static final String MEDIAL_ZWJ_I_COMPOSING = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ;

    private static final String MEDIAL_U_FVS1 = "" + MongolCode.Uni.U + MongolCode.Uni.FVS1;
    private static final String MEDIAL_U_FVS1_COMPOSING = "" + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_UE_FVS2 = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2;
    private static final String MEDIAL_UE_FVS2_COMPOSING = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_DOTTED_NA = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1;
    private static final String MEDIAL_DOTTED_NA_COMPOSING = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_TA_FVS1 = "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1;
    private static final String MEDIAL_TA_FVS1_COMPOSING = "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String YA_FVS1 = "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1;
    private static final String YA_FVS1_COMPOSING = "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
}
