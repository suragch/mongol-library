package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolLabel;


public class MongolFontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_font);

        MongolLabel label1 = (MongolLabel) findViewById(R.id.mongolLabel1);
        MongolLabel label2 = (MongolLabel) findViewById(R.id.mongolLabel2);
        MongolLabel label3 = (MongolLabel) findViewById(R.id.mongolLabel3);
        MongolLabel label4 = (MongolLabel) findViewById(R.id.mongolLabel4);
        MongolLabel label5 = (MongolLabel) findViewById(R.id.mongolLabel5);
        MongolLabel label6 = (MongolLabel) findViewById(R.id.mongolLabel6);
        MongolLabel label7 = (MongolLabel) findViewById(R.id.mongolLabel7);
        MongolLabel label8 = (MongolLabel) findViewById(R.id.mongolLabel8);
        MongolLabel label9 = (MongolLabel) findViewById(R.id.mongolLabel9);
        MongolLabel label10 = (MongolLabel) findViewById(R.id.mongolLabel10);
        MongolLabel label11 = (MongolLabel) findViewById(R.id.mongolLabel11);
        MongolLabel label12 = (MongolLabel) findViewById(R.id.mongolLabel12);
        MongolLabel label13 = (MongolLabel) findViewById(R.id.mongolLabel13);
        MongolLabel label14 = (MongolLabel) findViewById(R.id.mongolLabel14);
        MongolLabel label15 = (MongolLabel) findViewById(R.id.mongolLabel15);

        label1.setTypeface(MongolFont.get(MongolFont.QAGAN, this));
        label2.setTypeface(MongolFont.get(MongolFont.GARQAG, this));
        label3.setTypeface(MongolFont.get(MongolFont.HARA, this));
        label4.setTypeface(MongolFont.get(MongolFont.SCNIN, this));
        label5.setTypeface(MongolFont.get(MongolFont.HAWANG, this));
        label6.setTypeface(MongolFont.get(MongolFont.QIMED, this));
        label7.setTypeface(MongolFont.get(MongolFont.NARIN, this));
        label8.setTypeface(MongolFont.get(MongolFont.MCDVNBAR, this));
        label9.setTypeface(MongolFont.get(MongolFont.AMGLANG, this));
        label10.setTypeface(MongolFont.get(MongolFont.SIDAM, this));
        label11.setTypeface(MongolFont.get(MongolFont.QINGMING, this));
        label12.setTypeface(MongolFont.get(MongolFont.ONQA_HARA, this));
        label13.setTypeface(MongolFont.get(MongolFont.SVGVNAG, this));
        label14.setTypeface(MongolFont.get(MongolFont.SVLBIYA, this));
        label15.setTypeface(MongolFont.get(MongolFont.JCLGQ, this));

    }
}
