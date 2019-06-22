package net.studymongolian.testingapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import net.studymongolian.mongollibrary.MongolTextView;

public class MongolTextViewSpacingActivity extends AppCompatActivity {

    private static final String TEXT = "ᠨᠢᠭᠡ ᠬᠣᠶᠠᠷ ᠭᠣᠷᠪᠠ ᠳᠥᠷᠪᠡ ᠲᠠᠪᠤ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠳᠣᠯᠣᠭ᠎ᠠ ᠨᠠ\u200dᠢᠮᠠ ᠶᠢᠰᠦ ᠠᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠨᠢᠭᠡ ᠠᠷᠪᠠᠨ ᠬᠣᠶᠠᠷ ᠠᠷᠪᠠᠨ ᠭᠣᠷᠪᠠ ᠠᠷᠪᠠᠨ ᠳᠥᠷᠪᠡ ᠠᠷᠪᠠᠨ ᠲᠠᠪᠤ ᠠᠷᠪᠠᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠠᠷᠪᠠᠨ ᠨᠠ\u200dᠢᠮᠠ ᠠᠷᠪᠠ ᠶᠢᠰᠦ ᠬᠣᠷᠢ \uD83D\uDE42 ᠬᠣᠷᠢᠨ ᠨᠢᠭᠡ ᠬᠣᠷᠢᠨ ᠬᠣᠶᠠᠷ ᠬᠣᠷᠢᠨ ᠭᠣᠷᠪᠠ ᠬᠣᠷᠢᠨ ᠳᠥᠷᠪᠡ ᠬᠣᠷᠢᠨ ᠲᠠᠪᠤ ᠬᠣᠷᠢᠨ ᠵᠢᠷᠭᠤᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠳᠣᠯᠣᠭ᠎ᠠ ᠬᠣᠷᠢᠨ ᠨᠠ\u200dᠢᠮᠠ ᠬᠣᠷᠢ ᠶᠢᠰᠦ  ᠭᠣᠴᠢ one two three four five six seven eight nine ten 一二三四五六七八九十\uD83D\uDE03\uD83D\uDE0A\uD83D\uDE1C\uD83D\uDE01\uD83D\uDE2C\uD83D\uDE2E\uD83D\uDC34\uD83D\uDC02\uD83D\uDC2B\uD83D\uDC11\uD83D\uDC10ᆾ①②③㉑㊿〖汉字〗한국어モンゴル語English?︽ᠮᠣᠩᠭᠣᠯ︖︾";

    MongolTextView tvSampleText;
    TextView tvLineHeight;
    SeekBar seekBar;
    RadioButton rbAdd;
    RadioButton rbMult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtv_spacing);

        seekBar = findViewById(R.id.seekBar);
        tvSampleText = findViewById(R.id.tv_sample_text);
        tvSampleText.setText(TEXT);
        tvLineHeight = findViewById(R.id.tv_line_height);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        rbAdd = findViewById(R.id.radioButtonAdd);
        rbMult = findViewById(R.id.radioButtonMultiply);

    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (rbAdd.isChecked()) {
                tvSampleText.setLineSpacing(progress, 1);
                setLabels(progress, 1);
            } else {
                float multiplier = progress / 50f;
                tvSampleText.setLineSpacing(0, multiplier);
                setLabels(0, multiplier);
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void setLabels(float add, float mult) {
        tvLineHeight.setText("Line height = " + tvSampleText.getLineWidth());
        rbAdd.setText("Add " + add);
        rbMult.setText("Multiply " + mult);
    }

    public void onAddClick(View view) {
        seekBar.setProgress(0);
    }

    public void onMultiplyClick(View view) {
        seekBar.setProgress(50);
    }
}