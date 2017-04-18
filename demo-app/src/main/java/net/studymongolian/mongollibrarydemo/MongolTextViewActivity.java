package net.studymongolian.mongollibrarydemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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

import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolTextView;


public class MongolTextViewActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    MongolTextView mtvExample;
    //MongolTextView mTvWrapContent;

    // spinner choices
    private static final String[] fontColors = {"BLACK", "BLUE", "RED", "YELLOW"};
    private static final String[] fontSizesSP = {"10", "20", "30", "40", "50"};
    private static final String[] alignments = {"TOP", "CENTER", "BOTTOM"};
    private static final String[] paddings = {"0 PADDING", "10 PADDING"};
    private static final String[] textLengths = {"TEXT 1", "TEXT 2", "TEXT 3"};
    private static final String[] fonts = {"QAGAN", "SCNIN", "HAWANG", "AMGLANG", "JCLGQ"};
    private static final String[] spanTypes = {"HIGHLIGHT", "TEXT COLOR", "SIZE", "FONT"};

    private static final String TEXT_1 = "ᠮᠣᠩᠭᠣᠯ";
    private static final String TEXT_2 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ one two three 壹贰叁 \uD83D\uDE42\uD83D\uDE42\uD83D\uDE42";
    private static final String TEXT_3 = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ ᠳᠥᠷᠪᠡ ᠲᠠᠪᠤ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠳᠣᠯᠣᠭ᠎ᠠ ᠨᠠᠢᠮᠠ ᠶᠢᠰᠦ ᠠᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠨᠢᠭᠡ ᠠᠷᠪᠠᠨ ᠬᠣᠶᠠᠷ ᠠᠷᠪᠠᠨ ᠭᠣᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠳᠥᠷᠪᠡ ᠠᠷᠪᠠᠨ ᠲᠠᠪᠤ ᠠᠷᠪᠠᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠨᠠᠢᠮᠠ ᠠᠷᠪᠠ ᠶᠢᠰᠦ ᠬᠣᠷᠢ ᠬᠣᠷᠢᠨ ᠨᠢᠭᠡ ᠬᠣᠷᠢᠨ ᠬᠣᠶᠠᠷ ᠬᠣᠷᠢᠨ ᠭᠣᠷᠪᠠ ᠬᠣᠷᠢᠨ ᠳᠥᠷᠪᠡ ᠬᠣᠷᠢᠨ ᠲᠠᠪᠤ ᠬᠣᠷᠢᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠨᠠᠢᠮᠠ ᠬᠣᠷᠢ ᠶᠢᠰᠦ  ᠭᠣᠴᠢ one two three four five six seven eight nine ten 一二三四五六七八九十\uD83D\uDE03\uD83D\uDE0A\uD83D\uDE1C\uD83D\uDE01\uD83D\uDE2C\uD83D\uDE2E\uD83D\uDC34\uD83D\uDC02\uD83D\uDC2B\uD83D\uDC11\uD83D\uDC10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_textview);

        mtvExample = (MongolTextView) findViewById(R.id.mongol_textview);

        // Color choice spinner
        Spinner colorSpinner = (Spinner) findViewById(R.id.fontcolor_spinner);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fontColors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapterColor);
        //colorSpinner.setSelection(0, false);
        colorSpinner.setOnItemSelectedListener(this);

        // Font size spinner
        Spinner sizeSpinner = (Spinner) findViewById(R.id.fontsize_spinner);
        ArrayAdapter<String> adapterFontSizes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fontSizesSP);
        adapterFontSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapterFontSizes);
        sizeSpinner.setSelection(2); // 30sp
        //sizeSpinner.setSelection(0, false);
        sizeSpinner.setOnItemSelectedListener(this);

        // Alignment choice spinner
        Spinner alignmentSpinner = (Spinner) findViewById(R.id.alignment_spinner);
        ArrayAdapter<String> adapterAlignment = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, alignments);
        adapterAlignment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignmentSpinner.setAdapter(adapterAlignment);
        alignmentSpinner.setSelection(0, false);
        alignmentSpinner.setOnItemSelectedListener(this);

        // Padding size spinner
        Spinner paddingSpinner = (Spinner) findViewById(R.id.padding_spinner);
        ArrayAdapter<String> adapterPadding = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, paddings);
        adapterPadding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paddingSpinner.setAdapter(adapterPadding);
        paddingSpinner.setSelection(0, false);
        paddingSpinner.setOnItemSelectedListener(this);

        // Text length choice spinner
        Spinner textLengthSpinner = (Spinner) findViewById(R.id.textlength_spinner);
        ArrayAdapter<String> adapterTextLength = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, textLengths);
        adapterTextLength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textLengthSpinner.setAdapter(adapterTextLength);
        //textLengthSpinner.setSelection(0, false);
        textLengthSpinner.setOnItemSelectedListener(this);

        // Font choice spinner
        Spinner fontSpinner = (Spinner) findViewById(R.id.font_spinner);
        ArrayAdapter<String> adapterFont = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fonts);
        adapterFont.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(adapterFont);
        fontSpinner.setSelection(0, false);
        fontSpinner.setOnItemSelectedListener(this);

        // Span type spinner
        Spinner spanSpinner = (Spinner) findViewById(R.id.spantype_spinner);
        ArrayAdapter<String> adapterSpans = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spanTypes);
        adapterSpans.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spanSpinner.setAdapter(adapterSpans);
        spanSpinner.setSelection(0, false);
        spanSpinner.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        int viewid = parent.getId();
        if (viewid == R.id.fontcolor_spinner) {
            int color = Color.BLACK;
            String item = String.valueOf(parent.getItemAtPosition(pos));
            switch (item) {
                case "BLUE":
                    color = Color.BLUE;
                    break;
                case "RED":
                    color = Color.RED;
                    break;
                case "YELLOW":
                    color = Color.YELLOW;
                    break;
            }
            mtvExample.setTextColor(color);

        } else if (viewid == R.id.fontsize_spinner) {

            int size = Integer.parseInt(parent.getSelectedItem().toString());
            mtvExample.setTextSize(size);

        } else if (viewid == R.id.alignment_spinner) {

            int gravity;
            String item = String.valueOf(parent.getItemAtPosition(pos));
            switch (item) {
                case "CENTER":
                    gravity = Gravity.CENTER;
                    break;
                case "BOTTOM":
                    gravity = Gravity.BOTTOM;
                    break;
                default:
                    gravity = Gravity.TOP;
            }
            mtvExample.setGravity(gravity);

        } else if (viewid == R.id.padding_spinner) {

            int padding = 0;
            switch (pos) {
                case 0: // 0 padding
                    padding = 0;
                    break;
                case 1: // 10 padding
                    padding = 10;
                    break;
            }
            // convert pixels to dp
            int px = (int) (padding * getResources().getDisplayMetrics().density);
            mtvExample.setPadding(px, px, px, px);

        } else if (viewid == R.id.textlength_spinner) {

            String newString = "";
            switch (pos) {
                case 0: // TEXT 1
                    newString = TEXT_1;
                    break;
                case 1: // TEXT 2
                    newString = TEXT_2;
                    break;
                case 2: // TEXT 3
                    newString = TEXT_3;
                    break;
            }
            mtvExample.setText(newString);

        } else if (viewid == R.id.font_spinner) {

            Typeface tf;
            String item = String.valueOf(parent.getItemAtPosition(pos));
            switch (item) {
                case "SCNIN":
                    tf = MongolFont.get(MongolFont.SCNIN, this);
                    break;
                case "HAWANG":
                    tf = MongolFont.get(MongolFont.HAWANG, this);
                    break;
                case "AMGLANG":
                    tf = MongolFont.get(MongolFont.AMGLANG, this);
                    break;
                case "JCLGQ":
                    tf = MongolFont.get(MongolFont.JCLGQ, this);
                    break;
                default:
                    tf = MongolFont.get(MongolFont.QAGAN, this);
                    break;
            }
            mtvExample.setTypeface(tf);
        } else if (viewid == R.id.spantype_spinner) {

            SpannableString spannableString = new SpannableString(mtvExample.getText());

            // select the middle third
            int start = mtvExample.getText().length()/3;
            int end = mtvExample.getText().length()*2/3;

            switch (pos) {
                case 0: // HIGHLIGHT
                    BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.YELLOW);
                    spannableString.setSpan(backgroundSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                case 1: // TEXT COLOR
                    ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
                    spannableString.setSpan(foregroundSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                case 2: // SIZE
                    break;
                case 3: // FONT
                    break;
            }

            mtvExample.setText(spannableString);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
