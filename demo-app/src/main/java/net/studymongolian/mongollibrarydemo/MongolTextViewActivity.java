package net.studymongolian.mongollibrarydemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
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
    private static final String TEXT_3 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ ᠳᠥᠷᠪᠡ ᠲᠠᠪᠤ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠳᠣᠯᠣᠭ᠎ᠠ ᠨᠠ\u200dᠢᠮᠠ ᠶᠢᠰᠦ ᠠᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠨᠢᠭᠡ ᠠᠷᠪᠠᠨ ᠬᠣᠶᠠᠷ ᠠᠷᠪᠠᠨ ᠭᠣᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠳᠥᠷᠪᠡ ᠠᠷᠪᠠᠨ ᠲᠠᠪᠤ ᠠᠷᠪᠠᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠨᠠ\u200dᠢᠮᠠ ᠠᠷᠪᠠ ᠶᠢᠰᠦ ᠬᠣᠷᠢ ᠬᠣᠷᠢᠨ ᠨᠢᠭᠡ ᠬᠣᠷᠢᠨ ᠬᠣᠶᠠᠷ ᠬᠣᠷᠢᠨ ᠭᠣᠷᠪᠠ ᠬᠣᠷᠢᠨ ᠳᠥᠷᠪᠡ ᠬᠣᠷᠢᠨ ᠲᠠᠪᠤ ᠬᠣᠷᠢᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠨᠠ\u200dᠢᠮᠠ ᠬᠣᠷᠢ ᠶᠢᠰᠦ  ᠭᠣᠴᠢ one two three four five six seven eight nine ten 一二三四五六七八九十\uD83D\uDE03\uD83D\uDE0A\uD83D\uDE1C\uD83D\uDE01\uD83D\uDE2C\uD83D\uDE2E\uD83D\uDC34\uD83D\uDC02\uD83D\uDC2B\uD83D\uDC11\uD83D\uDC10";

    MongolTextView mtvExample;

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

        builder.setItems(textLengths, new DialogInterface.OnClickListener() {
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

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public void onSizeClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Font Size");
        final String[] fontSizesSP = {"10sp", "20sp", "30sp", "40sp", "50sp"};

        builder.setItems(fontSizesSP, new DialogInterface.OnClickListener() {
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

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onFontClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Font");
        final String[] fonts = {"QAGAN", "SCNIN", "HAWANG", "AMGLANG", "JCLGQ"};

        builder.setItems(fonts, new DialogInterface.OnClickListener() {
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

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onColorClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Color");
        final String[] fontColors = {"BLACK", "BLUE", "RED"};

        builder.setItems(fontColors, new DialogInterface.OnClickListener() {
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

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onSpanClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Span");
        final String[] spanTypes = {"HIGHLIGHT", "TEXT COLOR", "SIZE", "FONT"};

        builder.setItems(spanTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SpannableString spannableString = new SpannableString(mtvExample.getText());

                // select the middle third
                int start = mtvExample.getText().length()/3;
                int end = mtvExample.getText().length()*2/3;

                switch (which) {

                    // highlight
                    case 0:
                        BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.YELLOW);
                        spannableString.setSpan(backgroundSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;

                    // text color
                    case 1:
                        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
                        spannableString.setSpan(foregroundSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;

                    // size
                    case 2: // SIZE
                        // TODO
                        Toast.makeText(MongolTextViewActivity.this, "TODO size", Toast.LENGTH_SHORT).show();
                        break;

                    // font
                    case 3: // FONT
                        // TODO
                        Toast.makeText(MongolTextViewActivity.this, "TODO font", Toast.LENGTH_SHORT).show();
                        break;
                }
                mtvExample.setText(spannableString);

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onAlignmentClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alignment");
        final String[] alignments = {"TOP", "CENTER", "BOTTOM"};

        builder.setItems(alignments, new DialogInterface.OnClickListener() {
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

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onPaddingClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Padding");
        final String[] alignments = {"0dp", "10dp", "10dp, 0dp, 20dp, 30dp"};

        builder.setItems(alignments, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                float desity = getResources().getDisplayMetrics().density;
                switch (which) {
                    case 0:
                        mtvExample.setPadding(0, 0, 0, 0);
                        break;
                    case 1:
                        int px = (int) (10 * desity);
                        mtvExample.setPadding(px, px, px, px);
                        break;
                    case 2:
                        int left = (int) (10 * desity);
                        int top = (int) (0 * desity);
                        int right = (int) (20 * desity);
                        int bottom = (int) (30 * desity);
                        mtvExample.setPadding(left, top, right, bottom);
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
