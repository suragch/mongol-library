package net.studymongolian.mongollibrarydemo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.studymongolian.mongollibrary.MongolLabel;

public class MongolLabelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    MongolLabel mMongolLabelMatchParent;
    MongolLabel mMongolLabelWrapContent;


    private static final String[] fontColors = {"BLACK", "BLUE", "RED", "YELLOW"};
    private static final String[] fontSizesSP = {"10", "20", "30", "40", "50"};
    private static final String[] alignments = {"TOP", "CENTER", "BOTTOM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_label);

        mMongolLabelMatchParent = (MongolLabel) findViewById(R.id.mongol_label_matchparent);
        mMongolLabelWrapContent = (MongolLabel) findViewById(R.id.mongol_label_wrapcontent);

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
            mMongolLabelMatchParent.setTextColor(color);
            mMongolLabelWrapContent.setTextColor(color);
        } else if (viewid == R.id.fontsize_spinner) {
            int size = Integer.parseInt(parent.getSelectedItem().toString());
            mMongolLabelMatchParent.setTextSize(size);
            mMongolLabelWrapContent.setTextSize(size);
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
            mMongolLabelMatchParent.setGravity(gravity);
            mMongolLabelWrapContent.setGravity(gravity);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
