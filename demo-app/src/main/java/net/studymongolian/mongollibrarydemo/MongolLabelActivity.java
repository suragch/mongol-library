package net.studymongolian.mongollibrarydemo;


import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.studymongolian.mongollibrary.MongolLabel;

public class MongolLabelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    MongolLabel mMongolLabelMatchParent;
    MongolLabel mMongolLabelWrapContent;


    private static final String[] fontColors = {"BLACK", "BLUE", "RED", "YELLOW"};
    private static final String[] fontSizesSP = {"10", "20", "30", "50", "150"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_label);

        mMongolLabelMatchParent = findViewById(R.id.mongol_label_matchparent);
        mMongolLabelWrapContent = findViewById(R.id.mongol_label_wrapcontent);

        // Color choice spinner
        Spinner colorSpinner = findViewById(R.id.fontcolor_spinner);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fontColors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapterColor);
        colorSpinner.setOnItemSelectedListener(this);

        // Font size spinner
        Spinner sizeSpinner = findViewById(R.id.fontsize_spinner);
        ArrayAdapter<String> adapterFontSizes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fontSizesSP);
        adapterFontSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapterFontSizes);
        sizeSpinner.setSelection(2); // 30sp
        sizeSpinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        int viewId = parent.getId();
        if (viewId == R.id.fontcolor_spinner) {
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
        } else if (viewId == R.id.fontsize_spinner) {
            int size = Integer.parseInt(parent.getSelectedItem().toString());
            mMongolLabelMatchParent.setTextSize(size);
            mMongolLabelWrapContent.setTextSize(size);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
