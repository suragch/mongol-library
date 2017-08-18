package net.studymongolian.mongollibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;

import java.lang.ref.WeakReference;


// FIXME acts strangely when button text is too long
// FIXME doesn't dismiss on orientation change
// TODO add Theme support or at least color customization

public class MongolAlertDialog extends Dialog implements DialogInterface {

    private CharSequence mTitle;
    private MongolTextView mTitleView;

    protected CharSequence mMessage;
    protected MongolTextView mMessageView;
    protected HorizontalScrollView mScrollView;

    private MongolTextView mButtonPositive;
    private CharSequence mButtonPositiveText;
    private Message mButtonPositiveMessage;

    private MongolTextView mButtonNegative;
    private Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;

    private MongolTextView mButtonNeutral;
    private CharSequence mButtonNeutralText;
    private Message mButtonNeutralMessage;

    public MongolAlertDialog(Context context) {
        this(context, 0);
    }

    public MongolAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mHandler = new ButtonHandler(this);
    }

    protected MongolAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        this(context, 0);

        setCancelable(cancelable);
        setOnCancelListener(cancelListener);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mongol_alert_dialog_layout);

        setupTitle();
        setupMessage();
        setupButtons();
    }



    protected void setupTitle() {

        final boolean hasTextTitle = !TextUtils.isEmpty(mTitle);
        if (hasTextTitle) {
            // Display the title if a title is supplied, else hide it.
            mTitleView = (MongolTextView) getWindow().findViewById(R.id.mongol_dialog_title);
            mTitleView.setText(mTitle);
        } else {
            final ViewGroup titlePanel = (ViewGroup) getWindow().findViewById(R.id.mongol_dialog_title_panel);
            titlePanel.setVisibility(View.GONE);
        }

    }

    protected void setupMessage() {
        final ViewGroup contentPanel = (ViewGroup) getWindow().findViewById(R.id.mongol_dialog_content_panel);
        mScrollView = (HorizontalScrollView) contentPanel.findViewById(R.id.mongol_dialog_content_scrollview);
        mScrollView.setFocusable(false);
        mMessageView = (MongolTextView) getWindow().findViewById(R.id.mongol_dialog_message);
        final boolean hasTextTitle = !TextUtils.isEmpty(mMessage);
        if (hasTextTitle) {
            mMessageView.setText(mMessage);
        } else {
            mMessageView.setVisibility(View.GONE);
            mScrollView.removeView(mMessageView);
        }
    }

    protected void setupButtons() {

        int BIT_BUTTON_POSITIVE = 1;
        int BIT_BUTTON_NEGATIVE = 2;
        int BIT_BUTTON_NEUTRAL = 4;
        int whichButtons = 0;

        ViewGroup buttonPanel = (ViewGroup) getWindow().findViewById(R.id.mongol_dialog_button_panel);
        mButtonPositive = (MongolTextView) buttonPanel.findViewById(R.id.mongol_dialog_button_positive);
        mButtonPositive.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonPositiveText)) {
            mButtonPositive.setVisibility(View.GONE);
        } else {
            mButtonPositive.setText(mButtonPositiveText);
            mButtonPositive.setVisibility(View.VISIBLE);
            whichButtons = whichButtons | BIT_BUTTON_POSITIVE;
        }

        mButtonNegative = (MongolTextView) buttonPanel.findViewById(R.id.mongol_dialog_button_negative);
        mButtonNegative.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonNegativeText)) {
            mButtonNegative.setVisibility(View.GONE);
        } else {
            mButtonNegative.setText(mButtonNegativeText);
            mButtonNegative.setVisibility(View.VISIBLE);

            whichButtons = whichButtons | BIT_BUTTON_NEGATIVE;
        }

        mButtonNeutral = (MongolTextView) buttonPanel.findViewById(R.id.mongol_dialog_button_neutral);
        mButtonNeutral.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonNeutralText)) {
            mButtonNeutral.setVisibility(View.GONE);
        } else {
            mButtonNeutral.setText(mButtonNeutralText);
            mButtonNeutral.setVisibility(View.VISIBLE);

            whichButtons = whichButtons | BIT_BUTTON_NEUTRAL;
        }

        final boolean hasButtons = whichButtons != 0;
        if (!hasButtons) {
            buttonPanel.setVisibility(View.GONE);
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    public void setMessage(CharSequence message) {
        mMessage = message;
    }

    public void setButton(int whichButton, CharSequence text,
                          DialogInterface.OnClickListener listener, Message msg) {

        if (msg == null && listener != null) {
            msg = mHandler.obtainMessage(whichButton, listener);
        }

        switch (whichButton) {

            case DialogInterface.BUTTON_POSITIVE:
                mButtonPositiveText = text;
                mButtonPositiveMessage = msg;
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                mButtonNegativeText = text;
                mButtonNegativeMessage = msg;
                break;

            case DialogInterface.BUTTON_NEUTRAL:
                mButtonNeutralText = text;
                mButtonNeutralMessage = msg;
                break;

            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }


    Handler mHandler;

    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Message m;
            if (v == mButtonPositive && mButtonPositiveMessage != null) {
                m = Message.obtain(mButtonPositiveMessage);
            } else if (v == mButtonNegative && mButtonNegativeMessage != null) {
                m = Message.obtain(mButtonNegativeMessage);
            } else if (v == mButtonNeutral && mButtonNeutralMessage != null) {
                m = Message.obtain(mButtonNeutralMessage);
            } else {
                m = null;
            }

            if (m != null) {
                m.sendToTarget();
            }

            // Post a message so we dismiss after the above handlers are executed
            mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, MongolAlertDialog.this)
                    .sendToTarget();
        }
    };

    private static final class ButtonHandler extends Handler {
        // Button clicks have Message.what as the BUTTON{1,2,3} constant
        private static final int MSG_DISMISS_DIALOG = 1;

        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case DialogInterface.BUTTON_POSITIVE:
                case DialogInterface.BUTTON_NEGATIVE:
                case DialogInterface.BUTTON_NEUTRAL:
                    ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                    break;

                case MSG_DISMISS_DIALOG:
                    ((DialogInterface) msg.obj).dismiss();
            }
        }
    }



    @Override
    public void show() {
        super.show();

        float dp = 500;
        int dialogHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());

        Rect displayRectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        int windowHeight = displayRectangle.height();

        dialogHeight = Math.min(dialogHeight, (int) (0.9 * windowHeight));

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, dialogHeight);
    }

    static int resolveDialogTheme(Context context, int themeResId) {
        if (themeResId >= 0x01000000) {   // start of real resource IDs.
            return themeResId;
        } else {
            final TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.alertDialogTheme, outValue, true);
            return outValue.resourceId;
        }
    }

    public static class Builder {

        public final Context context;
        public final LayoutInflater inflater;


        private CharSequence title;
        private CharSequence message;
        private CharSequence positiveButtonText;
        private CharSequence negativeButtonText;
        private CharSequence neutralButtonText;
        public DialogInterface.OnClickListener positiveButtonListener;
        public DialogInterface.OnClickListener negativeButtonListener;
        public DialogInterface.OnClickListener neutralButtonListener;

        public Builder(Context context) {
            this(context, resolveDialogTheme(context, 0));
        }


        public Builder(Context context, int themeResId) {

            // TODO apply themeResId

            this.context = context;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public Context getContext() {
            return this.context;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final OnClickListener listener) {
            this.positiveButtonText = text;
            this.positiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final OnClickListener listener) {
            this.negativeButtonText = text;
            this.negativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, final OnClickListener listener) {
            this.neutralButtonText = text;
            this.neutralButtonListener = listener;
            return this;
        }


        public MongolAlertDialog create() {

            final MongolAlertDialog dialog = new MongolAlertDialog(this.context, 0);

            if (title != null) {
                dialog.setTitle(title);
            }

            if (message != null) {
                dialog.setMessage(message);
            }

            if (positiveButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveButtonText,
                        positiveButtonListener, null);
            }

            if (negativeButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, negativeButtonText,
                        negativeButtonListener, null);
            }

            if (neutralButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, neutralButtonText,
                        neutralButtonListener, null);
            }

            return dialog;
        }

        public MongolAlertDialog show() {
            final MongolAlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }


}
