package net.studymongolian.mongollibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;



public class MongolAlertDialog extends Dialog implements DialogInterface {

    //protected final Window mWindow;

    private CharSequence mTitle;
    private MongolTextView mTitleView;

    protected CharSequence mMessage;
    protected MongolTextView mMessageView;
    protected HorizontalScrollView mScrollView;

    private MongolButton mButtonPositive;
    private CharSequence mButtonPositiveText;
    private MongolButton mButtonNegative;
    private CharSequence mButtonNegativeText;
    private MongolButton mButtonNeutral;
    private CharSequence mButtonNeutralText;


    public MongolAlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    public MongolAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MongolAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        this(context, 0);

        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //installContent();

        //mWindow = getWindow();

        int contentView = R.layout.mongol_alert_dialog_layout;
        getWindow().setContentView(contentView);
        setupView();
    }

//    public void installContent() {
//        int contentView = mAlertDialogLayout;
//        mWindow.setContentView(contentView);
//        setupView();
//    }

    private void setupView() {

        final Window window = getWindow();

        final ViewGroup titlePanel = (ViewGroup) window.findViewById(R.id.mongol_dialog_title_panel);
        final ViewGroup contentPanel = (ViewGroup) window.findViewById(R.id.mongol_dialog_content_panel);
        final ViewGroup buttonPanel = (ViewGroup) window.findViewById(R.id.mongol_dialog_button_panel);

        final MongolTextView title = (MongolTextView) titlePanel.findViewById(R.id.mongol_dialog_title);
        mTitle = title.getText();

        final MongolTextView content = (MongolTextView) window.findViewById(R.id.mongol_dialog_content);


        //final View parentPanel = mWindow.findViewById(R.id.parentPanel);
        //final View defaultTopPanel = parentPanel.findViewById(R.id.topPanel);
        //final View defaultContentPanel = parentPanel.findViewById(R.id.contentPanel);
        //final View defaultButtonPanel = parentPanel.findViewById(R.id.buttonPanel);

        // Install custom content before setting up the title or buttons so
        // that we can handle panel overrides.
        //final ViewGroup customPanel = (ViewGroup) parentPanel.findViewById(R.id.customPanel);
        //setupCustomContent(customPanel);

        //final View customTopPanel = customPanel.findViewById(R.id.topPanel);
        //final View customContentPanel = customPanel.findViewById(R.id.contentPanel);
        //final View customButtonPanel = customPanel.findViewById(R.id.buttonPanel);

        // Resolve the correct panels and remove the defaults, if needed.
        //final ViewGroup topPanel = resolvePanel(customTopPanel, defaultTopPanel);
        //final ViewGroup contentPanel = resolvePanel(customContentPanel, defaultContentPanel);
        //final ViewGroup buttonPanel = resolvePanel(customButtonPanel, defaultButtonPanel);

        setupTitle(titlePanel);
        setupContent(contentPanel);
        setupButtons(buttonPanel);

        final boolean hasContentPanel = contentPanel != null
                && contentPanel.getVisibility() != View.GONE;
        final boolean hasTitlePanel = titlePanel != null
                && titlePanel.getVisibility() != View.GONE;
        final boolean hasButtonPanel = buttonPanel != null
                && buttonPanel.getVisibility() != View.GONE;

        // Only display the text spacer if we don't have buttons.
        if (!hasButtonPanel) {
            if (contentPanel != null) {
                final View spacer = contentPanel.findViewById(R.id.textSpacerNoButtons);
                if (spacer != null) {
                    spacer.setVisibility(View.VISIBLE);
                }
            }

            // FIXME don't have access to this method
            //mWindow.setCloseOnTouchOutsideIfNotSet(true);
        }


    }

    protected void setupTitle(ViewGroup titlePanel) {

        final boolean hasTextTitle = !TextUtils.isEmpty(mTitle);
        if (hasTextTitle) {
            // Display the title if a title is supplied, else hide it.
            mTitleView = (MongolTextView) getWindow().findViewById(R.id.mongol_dialog_title);
            mTitleView.setText(mTitle);
        } else {
            titlePanel.setVisibility(View.GONE);
        }

    }

    protected void setupContent(ViewGroup contentPanel) {
        mScrollView = (HorizontalScrollView) contentPanel.findViewById(R.id.mongol_dialog_content_scrollview);
        mScrollView.setFocusable(false);
        mMessageView = (MongolTextView) getWindow().findViewById(R.id.mongol_dialog_title);
        mMessage = mMessageView.getText();
        if (mMessage != null) {
            mMessageView.setText(mMessage);
        } else {
            mMessageView.setVisibility(View.GONE);
            mScrollView.removeView(mMessageView);
        }
    }


//    public MongolButton getButton(int whichButton) {
//        switch (whichButton) {
//            case DialogInterface.BUTTON_POSITIVE:
//                return mButtonPositive;
//            case DialogInterface.BUTTON_NEGATIVE:
//                return mButtonNegative;
//            case DialogInterface.BUTTON_NEUTRAL:
//                return mButtonNeutral;
//            default:
//                return null;
//        }
//    }

    protected void setupButtons(ViewGroup buttonPanel) {
        int BIT_BUTTON_POSITIVE = 1;
        int BIT_BUTTON_NEGATIVE = 2;
        int BIT_BUTTON_NEUTRAL = 4;
        int whichButtons = 0;
        mButtonPositive = (MongolButton) buttonPanel.findViewById(R.id.mongol_dialog_button_positive);
        mButtonPositive.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonPositiveText)) {
            mButtonPositive.setVisibility(View.GONE);
        } else {
            mButtonPositive.setText(mButtonPositiveText);
            mButtonPositive.setVisibility(View.VISIBLE);
            whichButtons = whichButtons | BIT_BUTTON_POSITIVE;
        }

        mButtonNegative = (MongolButton) buttonPanel.findViewById(R.id.mongol_dialog_button_negative);
        mButtonNegative.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonNegativeText)) {
            mButtonNegative.setVisibility(View.GONE);
        } else {
            mButtonNegative.setText(mButtonNegativeText);
            mButtonNegative.setVisibility(View.VISIBLE);

            whichButtons = whichButtons | BIT_BUTTON_NEGATIVE;
        }

        mButtonNeutral = (MongolButton) buttonPanel.findViewById(R.id.mongol_dialog_button_neutral);
        mButtonNeutral.setOnClickListener(mButtonHandler);

        if (TextUtils.isEmpty(mButtonNeutralText)) {
            mButtonNeutral.setVisibility(View.GONE);
        } else {
            mButtonNeutral.setText(mButtonNeutralText);
            mButtonNeutral.setVisibility(View.VISIBLE);

            whichButtons = whichButtons | BIT_BUTTON_NEUTRAL;
        }

            /*
             * If we only have 1 button it should be centered on the layout and
             * expand to fill 50% of the available space.
             */
        if (whichButtons == BIT_BUTTON_POSITIVE) {
            centerButton(mButtonPositive);
        } else if (whichButtons == BIT_BUTTON_NEGATIVE) {
            centerButton(mButtonNegative);
        } else if (whichButtons == BIT_BUTTON_NEUTRAL) {
            centerButton(mButtonNeutral);
        }

        final boolean hasButtons = whichButtons != 0;
        if (!hasButtons) {
            buttonPanel.setVisibility(View.GONE);
        }
    }

    private void centerButton(MongolButton button) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        params.weight = 0.5f;
        button.setLayoutParams(params);
//        View leftSpacer = mWindow.findViewById(R.id.leftSpacer); // TODO topSpacer
//        if (leftSpacer != null) {
//            leftSpacer.setVisibility(View.VISIBLE);
//        }
//        View rightSpacer = mWindow.findViewById(R.id.rightSpacer);  // TODO bottom spacer
//        if (rightSpacer != null) {
//            rightSpacer.setVisibility(View.VISIBLE);
//        }
    }

    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mButtonPositive) {
                Log.i("TAG", "MongolAlertDialog onClick: positive button clicked");
            } else if (v == mButtonNegative) {
                Log.i("TAG", "MongolAlertDialog onClick: negative button clicked");
            } else if (v == mButtonNeutral) {
                Log.i("TAG", "MongolAlertDialog onClick: neutral button clicked");
            } else {

            }
        }
    };

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        mTitle = title;
        if (mTitleView != null) {
            mTitleView.setText(title);
        }

    }

    public void setMessage(CharSequence message) {
        mMessage = message;
        if (mMessageView != null) {
            mMessageView.setText(message);
        }
    }

    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {

        switch (whichButton) {

            case DialogInterface.BUTTON_POSITIVE:
                mButtonPositiveText = text;
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                mButtonNegativeText = text;
                break;

            case DialogInterface.BUTTON_NEUTRAL:
                mButtonNeutralText = text;
                break;

            default:
                throw new IllegalArgumentException("Button does not exist");
        }
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
        //private final AlertParams P;
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
            //P = new AlertParams(new ContextThemeWrapper(
            //
            //        context, resolveDialogTheme(context, themeResId)));

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
            // Context has already been wrapped with the appropriate theme.
            //final MongolAlertDialog dialog = new MongolAlertDialog(P.mContext, 0, false);
            final MongolAlertDialog dialog = new MongolAlertDialog(this.context, 0);

            // FIXME where do I inflate this?

            if (title != null) {
                dialog.setTitle(title);
            }

            if (message != null) {
                dialog.setMessage(message);
            }
            if (positiveButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveButtonText,
                        positiveButtonListener);
            }
            if (negativeButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, negativeButtonText,
                        negativeButtonListener);
            }
            if (neutralButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, neutralButtonText,
                        neutralButtonListener);
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
