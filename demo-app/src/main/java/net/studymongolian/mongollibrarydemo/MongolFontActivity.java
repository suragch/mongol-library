package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolLabel;


public class MongolFontActivity extends AppCompatActivity {

    public static final String GARQAG = "fonts/MenksoftGarqag.ttf";         // Title
    public static final String HARA = "fonts/MenksoftHara.ttf";             // Bold
    public static final String SCNIN = "fonts/MenksoftScnin.ttf";           // News
    public static final String HAWANG = "fonts/MenksoftHawang.ttf";         // Handwriting
    public static final String QIMED = "fonts/MenksoftQimed.ttf";           // Handwriting pen
    public static final String NARIN = "fonts/MenksoftNarin.ttf";           // Thin
    public static final String MCDVNBAR = "fonts/MenksoftMcdvnbar.ttf";     // Wood carving
    public static final String AMGLANG = "fonts/MAM8102.ttf";               // Brush
    public static final String SIDAM = "fonts/MBN8102.ttf";                 // Fat round
    public static final String QINGMING = "fonts/MQI8102.ttf";              // Qing Ming style
    public static final String ONQA_HARA = "fonts/MTH8102.ttf";             // Thick stem
    public static final String SVGVNAG = "fonts/MSO8102.ttf";               // Thick stem thin lines
    public static final String SVLBIYA = "fonts/MBJ8102.ttf";               // Double stem
    public static final String JCLGQ = "fonts/MenksoftJclgq.ttf";           // Computer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_font);

        MongolLabel label1 = findViewById(R.id.mongolLabel1);
        MongolLabel label2 = findViewById(R.id.mongolLabel2);
        MongolLabel label3 = findViewById(R.id.mongolLabel3);
        MongolLabel label4 = findViewById(R.id.mongolLabel4);
        MongolLabel label5 = findViewById(R.id.mongolLabel5);
        MongolLabel label6 = findViewById(R.id.mongolLabel6);
        MongolLabel label7 = findViewById(R.id.mongolLabel7);
        MongolLabel label8 = findViewById(R.id.mongolLabel8);
        MongolLabel label9 = findViewById(R.id.mongolLabel9);
        MongolLabel label10 = findViewById(R.id.mongolLabel10);
        MongolLabel label11 = findViewById(R.id.mongolLabel11);
        MongolLabel label12 = findViewById(R.id.mongolLabel12);
        MongolLabel label13 = findViewById(R.id.mongolLabel13);
        MongolLabel label14 = findViewById(R.id.mongolLabel14);
        MongolLabel label15 = findViewById(R.id.mongolLabel15);

        // library font (this is the default so specifying the font is not actually necessary)
        label1.setTypeface(MongolFont.get(MongolFont.QAGAN, this));

        // additional fonts just added in this app
        label2.setTypeface(MongolFont.get(GARQAG, this));
        label3.setTypeface(MongolFont.get(HARA, this));
        label4.setTypeface(MongolFont.get(SCNIN, this));
        label5.setTypeface(MongolFont.get(HAWANG, this));
        label6.setTypeface(MongolFont.get(QIMED, this));
        label7.setTypeface(MongolFont.get(NARIN, this));
        label8.setTypeface(MongolFont.get(MCDVNBAR, this));
        label9.setTypeface(MongolFont.get(AMGLANG, this));
        label10.setTypeface(MongolFont.get(SIDAM, this));
        label11.setTypeface(MongolFont.get(QINGMING, this));
        label12.setTypeface(MongolFont.get(ONQA_HARA, this));
        label13.setTypeface(MongolFont.get(SVGVNAG, this));
        label14.setTypeface(MongolFont.get(SVLBIYA, this));
        label15.setTypeface(MongolFont.get(JCLGQ, this));

    }
}
