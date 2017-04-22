package net.studymongolian.mongollibrarydemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolTextView;


public class MongolTextViewActivity extends AppCompatActivity {

    private static final String TEXT_1 = "ᠮᠣᠩᠭᠣᠯ";
    private static final String TEXT_2 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ one two three 壹贰叁 \uD83D\uDE42\uD83D\uDE42\uD83D\uDE42";
    //private static final String TEXT_2 = "\n";
    private static final String TEXT_3 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ ᠳᠥᠷᠪᠡ ᠲᠠᠪᠤ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠳᠣᠯᠣᠭ᠎ᠠ ᠨᠠ\u200dᠢᠮᠠ ᠶᠢᠰᠦ ᠠᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠨᠢᠭᠡ ᠠᠷᠪᠠᠨ ᠬᠣᠶᠠᠷ ᠠᠷᠪᠠᠨ ᠭᠣᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠳᠥᠷᠪᠡ ᠠᠷᠪᠠᠨ ᠲᠠᠪᠤ ᠠᠷᠪᠠᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠨᠠ\u200dᠢᠮᠠ ᠠᠷᠪᠠ ᠶᠢᠰᠦ ᠬᠣᠷᠢ \uD83D\uDE42 ᠬᠣᠷᠢᠨ ᠨᠢᠭᠡ ᠬᠣᠷᠢᠨ ᠬᠣᠶᠠᠷ ᠬᠣᠷᠢᠨ ᠭᠣᠷᠪᠠ ᠬᠣᠷᠢᠨ ᠳᠥᠷᠪᠡ ᠬᠣᠷᠢᠨ ᠲᠠᠪᠤ ᠬᠣᠷᠢᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠨᠠ\u200dᠢᠮᠠ ᠬᠣᠷᠢ ᠶᠢᠰᠦ  ᠭᠣᠴᠢ one two three four five six seven eight nine ten 一二三四五六七八九十\uD83D\uDE03\uD83D\uDE0A\uD83D\uDE1C\uD83D\uDE01\uD83D\uDE2C\uD83D\uDE2E\uD83D\uDC34\uD83D\uDC02\uD83D\uDC2B\uD83D\uDC11\uD83D\uDC10";

    MongolTextView mtvExample;
    private int mCheckedTextItem = 0;
    private int mCheckedSizeItem = 0;
    private int mCheckedFontItem = 0;
    private int mCheckedColorItem = 0;
    private int mCheckedAlignmentItem = 0;
    private int mCheckedPaddingItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_textview);

        mtvExample = (MongolTextView) findViewById(R.id.mongol_textview);
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
                        tf = MongolFont.get(MongolFont.SCNIN, getApplicationContext());
                        break;
                    case 2:
                        tf = MongolFont.get(MongolFont.HAWANG, getApplicationContext());
                        break;
                    case 3:
                        tf = MongolFont.get(MongolFont.AMGLANG, getApplicationContext());
                        break;
                    case 4:
                        tf = MongolFont.get(MongolFont.JCLGQ, getApplicationContext());
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

        builder.setTitle("Span");
        final String[] spanTypes = {"HIGHLIGHT", "TEXT COLOR", "SIZE", "FONT"};
        final SpannableString spannableString = new SpannableString(mtvExample.getText().toString());

        // check boxes
        builder.setMultiChoiceItems(spanTypes, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                // user checked or unchecked a box

                // select the middle third
                int start = mtvExample.getText().length()/3;
                int end = mtvExample.getText().length()*2/3;

                switch (which) {

                    case 0: // highlight

                        BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.YELLOW);
                        if (isChecked) {
                            spannableString.setSpan(backgroundSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            spannableString.removeSpan(backgroundSpan);
                        }
                        break;

                    case 1: // text color
                        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.GREEN);
                        if (isChecked) {
                            spannableString.setSpan(foregroundSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            spannableString.removeSpan(foregroundSpan);
                        }
                        break;

                    case 2: // SIZE
                        // TODO
                        //Toast.makeText(MongolTextViewActivity.this, "TODO size", Toast.LENGTH_SHORT).show();
                        RelativeSizeSpan fontSizeSpan = new RelativeSizeSpan(2f);
                        if (isChecked) {
                            spannableString.setSpan(fontSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        } else {
                            spannableString.removeSpan(fontSizeSpan);
                        }
                        break;

                    case 3: // FONT
                        // TODO
                        Toast.makeText(MongolTextViewActivity.this, "TODO font", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // dialog OK and Cancel buttons
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
}
