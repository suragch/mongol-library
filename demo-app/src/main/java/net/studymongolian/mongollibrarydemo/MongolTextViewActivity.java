package net.studymongolian.mongollibrarydemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private static final String[] textLengths = {"TEXT 1", "TEXT 2", "TEXT 3"};
    private static final String[] fonts = {"QAGAN", "SCNIN", "HAWANG", "AMGLANG", "JCLGQ"};
    private static final String[] spanTypes = {"HIGHLIGHT", "TEXT COLOR", "SIZE", "FONT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_textview);

        mtvExample = (MongolTextView) findViewById(R.id.mongol_textview);
        //mTvWrapContent = (MongolTextView) findViewById(R.id.mongol_textview_wrapcontent);

        // Color choice spinner
        Spinner colorSpinner = (Spinner) findViewById(R.id.fontcolor_spinner);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fontColors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapterColor);
        colorSpinner.setOnItemSelectedListener(this);

        // Font size spinner
        Spinner sizeSpinner = (Spinner) findViewById(R.id.fontsize_spinner);
        ArrayAdapter<String> adapterFontSizes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fontSizesSP);
        adapterFontSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapterFontSizes);
        sizeSpinner.setSelection(2); // 30sp
        sizeSpinner.setOnItemSelectedListener(this);

        // Alignment choice spinner
        Spinner alignmentSpinner = (Spinner) findViewById(R.id.alignment_spinner);
        ArrayAdapter<String> adapterAlignment = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, alignments);
        adapterAlignment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignmentSpinner.setAdapter(adapterAlignment);
        alignmentSpinner.setOnItemSelectedListener(this);

        // Text length choice spinner
        Spinner textLengthSpinner = (Spinner) findViewById(R.id.textlength_spinner);
        ArrayAdapter<String> adapterTextLength = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, textLengths);
        adapterTextLength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textLengthSpinner.setAdapter(adapterTextLength);
        textLengthSpinner.setOnItemSelectedListener(this);

        // Font choice spinner
        Spinner fontSpinner = (Spinner) findViewById(R.id.font_spinner);
        ArrayAdapter<String> adapterFont = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fonts);
        adapterFont.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(adapterFont);
        fontSpinner.setOnItemSelectedListener(this);

        // Span type spinner
        Spinner spanSpinner = (Spinner) findViewById(R.id.spantype_spinner);
        ArrayAdapter<String> adapterSpans = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spanTypes);
        adapterSpans.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spanSpinner.setAdapter(adapterSpans);
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
            //mTvWrapContent.setTextColor(color);
        } else if (viewid == R.id.fontsize_spinner) {
            int size = Integer.parseInt(parent.getSelectedItem().toString());
            mtvExample.setTextSize(size);
            //mTvWrapContent.setTextSize(size);
        } else if (viewid == R.id.alignment_spinner) {
            int gravity = Gravity.TOP;
            String item = String.valueOf(parent.getItemAtPosition(pos));
            switch (item) {
                case "CENTER":
                    gravity = Gravity.CENTER;
                    break;
                case "BOTTOM":
                    gravity = Gravity.BOTTOM;
                    break;
            }
            mtvExample.setGravity(gravity);
            //mTvWrapContent.setGravity(gravity);
        } else if (viewid == R.id.textlength_spinner) {
            String newString = "";
            switch (pos) {
                case 0: // TEXT 1
                    newString = getString(R.string.short_string);
                    break;
                case 1: // TEXT 2
                    newString = getString(R.string.medium_string);
                    break;
                case 2: // TEXT 3
                    newString = getString(R.string.long_string);
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

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
