package net.studymongolian.mongollibrarydemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolTextView;
import net.studymongolian.mongollibrary.MongolTypefaceSpan;


public class MongolTextViewActivity extends AppCompatActivity {

    private static final String TEXT_1 = "ᠮᠣᠩᠭᠣᠯ";
    private static final String TEXT_2 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ one two three 壹贰叁 \uD83D\uDE42\uD83D\uDE42\uD83D\uDE42";
    private static final String TEXT_3 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ ᠳᠥᠷᠪᠡ ᠲᠠᠪᠤ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠳᠣᠯᠣᠭ᠎ᠠ ᠨᠠ\u200dᠢᠮᠠ ᠶᠢᠰᠦ ᠠᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠨᠢᠭᠡ ᠠᠷᠪᠠᠨ ᠬᠣᠶᠠᠷ ᠠᠷᠪᠠᠨ ᠭᠣᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠳᠥᠷᠪᠡ ᠠᠷᠪᠠᠨ ᠲᠠᠪᠤ ᠠᠷᠪᠠᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠨᠠ\u200dᠢᠮᠠ ᠠᠷᠪᠠ ᠶᠢᠰᠦ ᠬᠣᠷᠢ \uD83D\uDE42 ᠬᠣᠷᠢᠨ ᠨᠢᠭᠡ ᠬᠣᠷᠢᠨ ᠬᠣᠶᠠᠷ ᠬᠣᠷᠢᠨ ᠭᠣᠷᠪᠠ ᠬᠣᠷᠢᠨ ᠳᠥᠷᠪᠡ ᠬᠣᠷᠢᠨ ᠲᠠᠪᠤ ᠬᠣᠷᠢᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠨᠠ\u200dᠢᠮᠠ ᠬᠣᠷᠢ ᠶᠢᠰᠦ  ᠭᠣᠴᠢ one two three four five six seven eight nine ten 一二三四五六七八九十\uD83D\uDE03\uD83D\uDE0A\uD83D\uDE1C\uD83D\uDE01\uD83D\uDE2C\uD83D\uDE2E\uD83D\uDC34\uD83D\uDC02\uD83D\uDC2B\uD83D\uDC11\uD83D\uDC10ᆾ①②③㉑㊿〖汉字〗한국어モンゴル語English?︽ᠮᠣᠩᠭᠣᠯ︖︾";

    MongolTextView mtvExample;
    private int mCheckedTextItem = 2;
    private int mCheckedSizeItem = 2;
    private int mCheckedFontItem = 0;
    private int mCheckedColorItem = 0;
    private int mCheckedAlignmentItem = 0;
    private int mCheckedPaddingItem = 0;
    private int mCheckedStrokeItem = 0;
    private int mCheckedShadowItem = 0;
    private int mCheckedSpacingItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_textview);

        mtvExample = findViewById(R.id.mongol_textview);
        mtvExample.setText(TEXT_3);

        // When using shadow on the text it may be necessary to use software rendering
        // especially if the blur radius is greater than 25 or when combining with a stroke.
        //
        // mtvExample.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    // Button click methods

    public void onTextClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Text");
        final String[] textLengths = {"TEXT 1", "TEXT 2", "TEXT 3"};

        builder.setSingleChoiceItems(textLengths, mCheckedTextItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = TEXT_1;
                switch (which) {
                    case 0:
                        text = TEXT_1;
                        break;
                    case 1:
                        text = TEXT_2;
                        break;
                    case 2:
                        text = TEXT_3;
                        break;
                }
                mtvExample.setText(text);
                mCheckedTextItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void onSizeClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Font Size");
        final String[] fontSizesSP = {"10sp", "20sp", "30sp", "40sp", "50sp"};

        builder.setSingleChoiceItems(fontSizesSP, mCheckedSizeItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = 30;
                switch (which) {
                    case 0:
                        size = 10;
                        break;
                    case 1:
                        size = 20;
                        break;
                    case 2:
                        size = 30;
                        break;
                    case 3:
                        size = 40;
                        break;
                    case 4:
                        size = 50;
                        break;
                }
                mtvExample.setTextSize(size);
                mCheckedSizeItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onFontClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Font");
        final String[] fonts = {"QAGAN", "SCNIN", "HAWANG", "AMGLANG", "JCLGQ"};

        builder.setSingleChoiceItems(fonts, mCheckedFontItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Typeface tf = MongolFont.get(MongolFont.QAGAN, getApplicationContext());
                switch (which) {
                    case 0:
                        tf = MongolFont.get(MongolFont.QAGAN, getApplicationContext());
                        break;
                    case 1:
                        tf = MongolFont.get(MongolFontActivity.SCNIN, getApplicationContext());
                        break;
                    case 2:
                        tf = MongolFont.get(MongolFontActivity.HAWANG, getApplicationContext());
                        break;
                    case 3:
                        tf = MongolFont.get(MongolFontActivity.AMGLANG, getApplicationContext());
                        break;
                    case 4:
                        tf = MongolFont.get(MongolFontActivity.JCLGQ, getApplicationContext());
                        break;
                }
                mtvExample.setTypeface(tf);
                mCheckedFontItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onColorClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Color");
        final String[] fontColors = {"BLACK", "BLUE", "RED"};

        builder.setSingleChoiceItems(fontColors, mCheckedColorItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int color = Color.BLACK;
                switch (which) {
                    case 0:
                        color = Color.BLACK;
                        break;
                    case 1:
                        color = Color.BLUE;
                        break;
                    case 2:
                        color = Color.RED;
                        break;
                }
                mtvExample.setTextColor(color);
                mCheckedColorItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onSpanClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add span");
        final String[] spanTypes = {"BackgroundColorSpan", "ForegroundColorSpan", "RelativeSizeSpan", "TypefaceSpan",
                "ScaleXSpan", "StyleSpan", "SubscriptSpan", "SuperscriptSpan", "UnderlineSpan", "ClickableSpan"};
        final SpannableString spannableString = new SpannableString(mtvExample.getText().toString());

        // check boxes
        builder.setMultiChoiceItems(spanTypes, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                // user checked or unchecked a box

                // select the middle third
                int start = mtvExample.getText().length() / 3;
                int end = mtvExample.getText().length() * 2 / 3;

                switch (which) {

                    case 0: // highlight

                        if (isChecked) {
                            BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            BackgroundColorSpan[] spans = spannableString.getSpans(0, spannableString.length(), BackgroundColorSpan.class);
                            for (BackgroundColorSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 1: // text color
                        if (isChecked) {
                            ForegroundColorSpan span = new ForegroundColorSpan(Color.GREEN);
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            ForegroundColorSpan[] spans = spannableString.getSpans(0, spannableString.length(), ForegroundColorSpan.class);
                            for (ForegroundColorSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 2: // SIZE
                        if (isChecked) {
                            RelativeSizeSpan span = new RelativeSizeSpan(2f);
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            RelativeSizeSpan[] spans = spannableString.getSpans(0, spannableString.length(), RelativeSizeSpan.class);
                            for (RelativeSizeSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 3: // FONT
                        // setting a custom font as a span requires the custom MongolTypefaceSpan
                        if (isChecked) {
                            Typeface font = MongolFont.get(MongolFontActivity.JCLGQ, getApplicationContext());
                            MongolTypefaceSpan span = new MongolTypefaceSpan(font);
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            MongolTypefaceSpan[] spans = spannableString.getSpans(0, spannableString.length(), MongolTypefaceSpan.class);
                            for (MongolTypefaceSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 4: // scale X

                        // TODO rotated characters are scaled correctly

                        if (isChecked) {
                            ScaleXSpan span = new ScaleXSpan(2f);
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            ScaleXSpan[] spans = spannableString.getSpans(0, spannableString.length(), ScaleXSpan.class);
                            for (ScaleXSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 5: // bold style

                        if (isChecked) {
                            // TODO is there a correct way to apply ITALIC to Mongolian text (can adjust paint.setTextSkewX)
                            // StyleSpan span = new StyleSpan(Typeface.ITALIC);
                            StyleSpan span = new StyleSpan(Typeface.BOLD);
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            StyleSpan[] spans = spannableString.getSpans(0, spannableString.length(), StyleSpan.class);
                            for (StyleSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 6: // Subscript
                        if (isChecked) {
                            SubscriptSpan span = new SubscriptSpan();
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            SubscriptSpan[] spans = spannableString.getSpans(0, spannableString.length(), SubscriptSpan.class);
                            for (SubscriptSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 7: // Superscript
                        if (isChecked) {
                            SuperscriptSpan span = new SuperscriptSpan();
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            SuperscriptSpan[] spans = spannableString.getSpans(0, spannableString.length(), SuperscriptSpan.class);
                            for (SuperscriptSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 8: // Underline

                        if (isChecked) {
                            UnderlineSpan span = new UnderlineSpan();
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            UnderlineSpan[] spans = spannableString.getSpans(0, spannableString.length(), UnderlineSpan.class);
                            for (UnderlineSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                        break;

                    case 9: // Clickable

                        if (isChecked) {
                            ClickableSpan span = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                    Toast.makeText(MongolTextViewActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                                }
                            };
                            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            mtvExample.setMovementMethod(LinkMovementMethod.getInstance());
                        } else {
                            ClickableSpan[] spans = spannableString.getSpans(0, spannableString.length(), ClickableSpan.class);
                            for (ClickableSpan span : spans) {
                                spannableString.removeSpan(span);
                            }
                        }
                }
            }
        });

        // dialog buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mtvExample.setText(spannableString);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onAlignmentClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alignment");
        final String[] alignments = {"TOP", "CENTER", "BOTTOM"};

        builder.setSingleChoiceItems(alignments, mCheckedAlignmentItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int gravity = Gravity.TOP;
                switch (which) {
                    case 0:
                        gravity = Gravity.TOP;
                        break;
                    case 1:
                        gravity = Gravity.CENTER;
                        break;
                    case 2:
                        gravity = Gravity.BOTTOM;
                        break;
                }
                mtvExample.setGravity(gravity);
                mCheckedAlignmentItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onPaddingClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Padding");
        final String[] paddings = {"0dp", "10dp", "10dp, 0dp, 20dp, 30dp"};

        builder.setSingleChoiceItems(paddings, mCheckedPaddingItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                float density = getResources().getDisplayMetrics().density;
                switch (which) {
                    case 0:
                        mtvExample.setPadding(0, 0, 0, 0);
                        break;
                    case 1:
                        int px = (int) (10 * density);
                        mtvExample.setPadding(px, px, px, px);
                        break;
                    case 2:
                        int left = (int) (10 * density);
                        int top = (int) (0 * density);
                        int right = (int) (20 * density);
                        int bottom = (int) (30 * density);
                        mtvExample.setPadding(left, top, right, bottom);
                        break;
                }
                mCheckedPaddingItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onStrokeClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Stroke");
        final String[] choices = {"none", "thin green", "thick yellow", "transparent text"};

        builder.setSingleChoiceItems(choices, mCheckedStrokeItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mtvExample.setStrokeWidth(0);
                        mtvExample.setStrokeColor(Color.TRANSPARENT);
                        break;
                    case 1:
                        mtvExample.setStrokeWidth(1);
                        mtvExample.setStrokeColor(Color.GREEN);
                        break;
                    case 2:
                        mtvExample.setStrokeWidth(5);
                        mtvExample.setStrokeColor(Color.YELLOW);
                        break;
                    case 3:
                        mtvExample.setTextColor(Color.TRANSPARENT);
                        mtvExample.setStrokeWidth(1);
                        mtvExample.setStrokeColor(Color.BLACK);
                        break;
                }
                mCheckedStrokeItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onShadowClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Shadow");
        final String[] choices = {
                "none",
                "radius=2, dx=14, dy=14",
                "radius=10, dx=4, dy=4",
                "radius=5, dx=-2, dy=-2, color=RED",
        };

        builder.setSingleChoiceItems(choices, mCheckedShadowItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mtvExample.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
                        break;
                    case 1:
                        mtvExample.setShadowLayer(2, 14, 14, Color.BLACK);
                        break;
                    case 2:
                        mtvExample.setShadowLayer(10, 4, 4, Color.BLACK);
                        break;
                    case 3:
                        mtvExample.setShadowLayer(5, -2, -2, Color.RED);
                        break;
                }
                mCheckedShadowItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onSpacingClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Line Spacing");
        final String[] choices = {
                "none (add=0 mult=1)",
                "extra add = 20px",
                "extra add = -20px",
                "multiplier = 2",
                "multiplier = 0.5",
                "mult=2 add=20",
        };

        builder.setSingleChoiceItems(choices, mCheckedSpacingItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mtvExample.setLineSpacing(0, 1);
                        break;
                    case 1:
                        mtvExample.setLineSpacing(20, 1);
                        break;
                    case 2:
                        mtvExample.setLineSpacing(-20, 1);
                        break;
                    case 3:
                        mtvExample.setLineSpacing(0, 2);
                        break;
                    case 4:
                        mtvExample.setLineSpacing(0, 0.5f);
                        break;
                    case 5:
                        mtvExample.setLineSpacing(20, 2);
                        break;
                }
                mCheckedSpacingItem = which;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
