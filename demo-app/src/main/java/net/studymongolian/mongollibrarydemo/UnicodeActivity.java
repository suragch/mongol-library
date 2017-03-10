package net.studymongolian.mongollibrarydemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import net.studymongolian.mongollibrary.MongolLabel;
import net.studymongolian.mongollibrary.MongolTextView;
import net.studymongolian.mongollibrary.MongolUnicodeRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UnicodeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView label;
    TextView tvResults;

    private static final char FVS1 = MongolUnicodeRenderer.Uni.FVS1;
    private static final char FVS2 = MongolUnicodeRenderer.Uni.FVS2;
    private static final char FVS3 = MongolUnicodeRenderer.Uni.FVS3;
    private static final char ZWJ = MongolUnicodeRenderer.Uni.ZWJ;
    private static final char MVS = MongolUnicodeRenderer.Uni.MVS;
    private static final char NNBS = MongolUnicodeRenderer.Uni.NNBS;
    private static final char A = MongolUnicodeRenderer.Uni.A;
    private static final char E = MongolUnicodeRenderer.Uni.E;
    private static final char I = MongolUnicodeRenderer.Uni.I;
    private static final char O = MongolUnicodeRenderer.Uni.O;
    private static final char U = MongolUnicodeRenderer.Uni.U;
    private static final char OE = MongolUnicodeRenderer.Uni.OE;
    private static final char UE = MongolUnicodeRenderer.Uni.UE;
    private static final char EE = MongolUnicodeRenderer.Uni.EE;
    private static final char NA = MongolUnicodeRenderer.Uni.NA;
    private static final char ANG = MongolUnicodeRenderer.Uni.ANG;
    private static final char BA = MongolUnicodeRenderer.Uni.BA;
    private static final char PA = MongolUnicodeRenderer.Uni.PA;
    private static final char QA = MongolUnicodeRenderer.Uni.QA;
    private static final char GA = MongolUnicodeRenderer.Uni.GA;
    private static final char MA = MongolUnicodeRenderer.Uni.MA;
    private static final char LA = MongolUnicodeRenderer.Uni.LA;
    private static final char SA = MongolUnicodeRenderer.Uni.SA;
    private static final char SHA = MongolUnicodeRenderer.Uni.SHA;
    private static final char TA = MongolUnicodeRenderer.Uni.TA;
    private static final char DA = MongolUnicodeRenderer.Uni.DA;
    private static final char CHA = MongolUnicodeRenderer.Uni.CHA;
    private static final char JA = MongolUnicodeRenderer.Uni.JA;
    private static final char YA = MongolUnicodeRenderer.Uni.YA;
    private static final char RA = MongolUnicodeRenderer.Uni.RA;
    private static final char WA = MongolUnicodeRenderer.Uni.WA;
    private static final char FA = MongolUnicodeRenderer.Uni.FA;
    private static final char KA = MongolUnicodeRenderer.Uni.KA;
    private static final char KHA = MongolUnicodeRenderer.Uni.KHA;
    private static final char TSA = MongolUnicodeRenderer.Uni.TSA;
    private static final char ZA = MongolUnicodeRenderer.Uni.ZA;
    private static final char HAA = MongolUnicodeRenderer.Uni.HAA;
    private static final char ZRA = MongolUnicodeRenderer.Uni.ZRA;
    private static final char LHA = MongolUnicodeRenderer.Uni.LHA;
    private static final char ZHI = MongolUnicodeRenderer.Uni.ZHI;
    private static final char CHI = MongolUnicodeRenderer.Uni.CHI;

    private static final String[] names = {"A", "E", "I", "O", "U", "OE", "UE", "EE",
            "N", "NG", "B", "P", "Q", "G", "M", "L", "S", "SH", "T", "D", "CH", "J", "Y",
            "R", "W", "F", "K", "KH", "TS", "Z", "H", "ZR", "LH", "ZHI", "CHI"};
    private static final char[] characters = {A, E, I, O, U, OE, UE, EE,
            NA, ANG, BA, PA, QA, GA, MA, LA, SA, SHA, TA, DA, CHA, JA, YA,
            RA, WA, FA, KA, KHA, TSA, ZA, HAA, ZRA, LHA, ZHI, CHI};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unicode);

        label = (TextView) findViewById(R.id.tvTitle);
        tvResults = (TextView) findViewById(R.id.tvUnicodeResults);

        // spinner
        List<String> spinnerChoices = new ArrayList<>(Arrays.asList(names));
        spinnerChoices.add("MVS");
        spinnerChoices.add("NNBS");
        Spinner chagaanTolgaiSpinner = (Spinner) findViewById(R.id.spinnerChagaanTolgai);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerChoices);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chagaanTolgaiSpinner.setAdapter(adapterColor);
        chagaanTolgaiSpinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        //char unicode = MongolUnicodeRenderer.Uni.A;
        String item = String.valueOf(parent.getItemAtPosition(pos));


        int index = Arrays.asList(names).indexOf(item);
        if (index >= 0) {
            //unicode = characters[index];
            updateTextViews(characters[index], item);
            return;
        }

        if (item.equals("MVS")) {
            updateForMvs();
        } else if (item.equals("NNBS")) {
            updateForNnbs();
        }
    }

    private void updateForNnbs() {
        // update label
        label.setText("NNBS");

        // build string
        String space = "     ";
        StringBuilder displayString = new StringBuilder();
        displayString.append("Vocative Case").append("\n");
        displayString.append(NNBS).append(A).append(space).append("A").append("\n");
        displayString.append(NNBS).append(E).append(space).append("E").append("\n");
        displayString.append("\n");
        displayString.append("Genetive Case").append("\n");
        displayString.append(NNBS).append(YA).append(I).append(NA).append(space).append("YIN").append("\n");
        displayString.append(NNBS).append(U).append(NA).append(space).append("UN").append("\n");
        displayString.append(NNBS).append(UE).append(NA).append(space).append("UEN").append("\n");
        displayString.append(NNBS).append(U).append(space).append("U").append("\n");
        displayString.append(NNBS).append(UE).append(space).append("UE").append("\n");
        displayString.append("\n");
        displayString.append("Accusative Case").append("\n");
        displayString.append(NNBS).append(I).append(space).append("I").append("\n");
        displayString.append(NNBS).append(YA).append(I).append(space).append("YI").append("\n");
        displayString.append("\n");
        displayString.append("Dative-Locative Case").append("\n");
        displayString.append(NNBS).append(DA).append(U).append(space).append("DU").append("\n");
        displayString.append(NNBS).append(DA).append(UE).append(space).append("DUE").append("\n");
        displayString.append(NNBS).append(TA).append(U).append(space).append("TU").append("\n");
        displayString.append(NNBS).append(TA).append(UE).append(space).append("TUE").append("\n");
        displayString.append(NNBS).append(DA).append(U).append(RA).append(space).append("DUR").append("\n");
        displayString.append(NNBS).append(DA).append(UE).append(RA).append(space).append("DUER").append("\n");
        displayString.append(NNBS).append(TA).append(U).append(RA).append(space).append("TUR").append("\n");
        displayString.append(NNBS).append(TA).append(UE).append(RA).append(space).append("TUER").append("\n");
        displayString.append(NNBS).append(DA).append(A).append(QA).append(I).append(space).append("DAQI").append("\n");
        displayString.append(NNBS).append(DA).append(E).append(QA).append(I).append(space).append("DEQI").append("\n");
        displayString.append("\n");
        displayString.append("Ablative Case").append("\n");
        displayString.append(NNBS).append(A).append(CHA).append(A).append(space).append("ACHA").append("\n");
        displayString.append(NNBS).append(E).append(CHA).append(E).append(space).append("ECHE").append("\n");
        displayString.append("\n");
        displayString.append("Instrumental Case").append("\n");
        displayString.append(NNBS).append(BA).append(A).append(RA).append(space).append("BAR").append("\n");
        displayString.append(NNBS).append(BA).append(E).append(RA).append(space).append("BER").append("\n");
        displayString.append(NNBS).append(I).append(YA).append(A).append(RA).append(space).append("IYAR").append("\n");
        displayString.append(NNBS).append(I).append(YA).append(E).append(RA).append(space).append("IYER").append("\n");
        displayString.append("\n");
        displayString.append("Comitative Case").append("\n");
        displayString.append(NNBS).append(TA).append(A).append(I).append(space).append("TAI").append("\n");
        displayString.append(NNBS).append(TA).append(A).append(YA).append(I).append(space).append("TAYI").append("\n");
        displayString.append(NNBS).append(TA).append(E).append(I).append(space).append("TEI").append("\n");
        displayString.append(NNBS).append(TA).append(E).append(YA).append(I).append(space).append("TEYI").append("\n");
        displayString.append(NNBS).append(LA).append(U).append(GA).append(MVS).append(A).append(space).append("LUG-A").append("\n");
        displayString.append(NNBS).append(LA).append(UE).append(GA).append(E).append(space).append("LUEGE").append("\n");
        displayString.append("\n");
        displayString.append("Reflexive Case").append("\n");
        displayString.append(NNBS).append(BA).append(A).append(NA).append(space).append("BAN").append("\n");
        displayString.append(NNBS).append(BA).append(E).append(NA).append(space).append("BEN").append("\n");
        displayString.append(NNBS).append(I).append(YA).append(A).append(NA).append(space).append("IYAN").append("\n");
        displayString.append(NNBS).append(I).append(YA).append(E).append(NA).append(space).append("IYEN").append("\n");
        displayString.append("\n");
        displayString.append("Reflexive+Accusative Case").append("\n");
        displayString.append(NNBS).append(YA).append(U).append(GA).append(A).append(NA).append(space).append("YUGAN").append("\n");
        displayString.append(NNBS).append(YA).append(UE).append(GA).append(E).append(NA).append(space).append("YUEGEN").append("\n");
        displayString.append("\n");
        displayString.append("Reflexive+Dative-Locative Case").append("\n");
        displayString.append(NNBS).append(DA).append(A).append(GA).append(A).append(NA).append(space).append("DAGAN").append("\n");
        displayString.append(NNBS).append(DA).append(E).append(GA).append(E).append(NA).append(space).append("DEGEN").append("\n");
        displayString.append(NNBS).append(TA).append(A).append(GA).append(A).append(NA).append(space).append("TAGAN").append("\n");
        displayString.append(NNBS).append(TA).append(E).append(GA).append(E).append(NA).append(space).append("TEGEN").append("\n");
        displayString.append("\n");
        displayString.append("Reflexive+Ablative Case").append("\n");
        displayString.append(NNBS).append(A).append(CHA).append(A).append(GA).append(A).append(NA).append(space).append("ACHAGAN").append("\n");
        displayString.append(NNBS).append(E).append(CHA).append(E).append(GA).append(E).append(NA).append(space).append("ECHEGEN").append("\n");
        displayString.append("\n");
        displayString.append("Reflexive+Comitative Case").append("\n");
        displayString.append(NNBS).append(TA).append(A).append(I).append(GA).append(A).append(NA).append(space).append("TAIGAN").append("\n");
        displayString.append(NNBS).append(TA).append(A).append(YA).append(I).append(GA).append(A).append(NA).append(space).append("TAYIGAN").append("\n");
        displayString.append(NNBS).append(TA).append(E).append(I).append(GA).append(E).append(NA).append(space).append("TEIGEN").append("\n");
        displayString.append(NNBS).append(TA).append(E).append(YA).append(I).append(GA).append(E).append(NA).append(space).append("TEYIGEN").append("\n");
        displayString.append("\n");
        displayString.append("Plural").append("\n");
        displayString.append(NNBS).append(U).append(DA).append(space).append("UD").append("\n");
        displayString.append(NNBS).append(UE).append(DA).append(space).append("UED").append("\n");
        displayString.append(NNBS).append(NA).append(U).append(GA).append(U).append(DA).append(space).append("NUGUD").append("\n");
        displayString.append(NNBS).append(NA).append(UE).append(GA).append(UE).append(DA).append(space).append("NUEGUED").append("\n");
        displayString.append(NNBS).append(NA).append(A).append(RA).append(space).append("NAR").append("\n");
        displayString.append(NNBS).append(NA).append(E).append(RA).append(space).append("NER").append("\n");
        displayString.append("\n");
        displayString.append("Particles").append("\n");
        displayString.append(NNBS).append(U).append(U).append(space).append("UU").append("\n");
        displayString.append(NNBS).append(UE).append(UE).append(space).append("UEUE").append("\n");
        displayString.append(NNBS).append(DA).append(A).append(space).append("DA").append("\n");
        displayString.append(NNBS).append(DA).append(E).append(space).append("DE").append("\n");

        // set string
        tvResults.setText(displayString);
    }

    private void updateForMvs() {
        // update label
        label.setText("MVS");

        // build string
        String space = "     ";
        StringBuilder displayString = new StringBuilder();
        for (int i = 0; i < characters.length; i++) {
            displayString.append(ZWJ);
            displayString.append(characters[i]);
            displayString.append(MVS);
            displayString.append(A);
            displayString.append(space);
            displayString.append(names[i]);
            displayString.append("-A");
            displayString.append("\n");
        }
        displayString.append("\n");
        for (int i = 0; i < characters.length; i++) {
            displayString.append(ZWJ);
            displayString.append(characters[i]);
            displayString.append(MVS);
            displayString.append(E);
            displayString.append(space);
            displayString.append(names[i]);
            displayString.append("-E");
            displayString.append("\n");
        }
        displayString.append("\n");
        for (int i = 0; i < characters.length; i++) {
            displayString.append(ZWJ);
            displayString.append(characters[i]);
            displayString.append(MVS);
            displayString.append(A);
            displayString.append(NA);
            displayString.append(space);
            displayString.append(names[i]);
            displayString.append("-AN");
            displayString.append("\n");
        }

        // set string
        tvResults.setText(displayString);
    }

    private void updateTextViews(char unicode, String name) {
        // update label
        label.setText(name);

        // build string
        String space = "     ";
        StringBuilder displayString = new StringBuilder();
        displayString.append("Isolate").append("\n");
        displayString.append(unicode).append("\n");
        displayString.append(unicode).append(FVS1).append(space).append("FVS1").append("\n");
        displayString.append(unicode).append(FVS2).append(space).append("FVS2").append("\n");
        displayString.append(unicode).append(FVS3).append(space).append("FVS3").append("\n\n");
        displayString.append("Initial").append("\n");
        displayString.append(unicode).append(ZWJ).append("\n");
        displayString.append(unicode).append(ZWJ).append(FVS1).append(space).append("FVS1").append("\n");
        displayString.append(unicode).append(ZWJ).append(FVS2).append(space).append("FVS2").append("\n");
        displayString.append(unicode).append(ZWJ).append(FVS3).append(space).append("FVS3").append("\n\n");
        displayString.append("Medial").append("\n");
        displayString.append(ZWJ).append(unicode).append(ZWJ).append("\n");
        displayString.append(ZWJ).append(unicode).append(ZWJ).append(FVS1).append(space).append("FVS1").append("\n");
        displayString.append(ZWJ).append(unicode).append(ZWJ).append(FVS2).append(space).append("FVS2").append("\n");
        displayString.append(ZWJ).append(unicode).append(ZWJ).append(FVS3).append(space).append("FVS3").append("\n\n");
        displayString.append("Final").append("\n");
        displayString.append(ZWJ).append(unicode).append("\n");
        displayString.append(ZWJ).append(unicode).append(FVS1).append(space).append("FVS1").append("\n");
        displayString.append(ZWJ).append(unicode).append(FVS2).append(space).append("FVS2").append("\n");
        displayString.append(ZWJ).append(unicode).append(FVS3).append(space).append("FVS3").append("\n\n");
        displayString.append("Initial").append("\n");
        for (int i = 0; i < characters.length; i++) {
            displayString.append(unicode);
            displayString.append(characters[i]);
            displayString.append(space);
            displayString.append(name);
            displayString.append(names[i]);
            displayString.append("\n");
        }
        displayString.append("\n");
        displayString.append("Final").append("\n");
        for (int i = 0; i < characters.length; i++) {
            displayString.append(characters[i]);
            displayString.append(unicode);
            displayString.append(space);
            displayString.append(names[i]);
            displayString.append(name);
            displayString.append("\n");
        }
        displayString.append("\n");
        displayString.append("Medial").append("\n");
        for (int i = 0; i < characters.length; i++) {
            displayString.append(characters[i]);
            displayString.append(unicode);
            displayString.append(characters[i]);
            displayString.append(space);
            displayString.append(names[i]);
            displayString.append(name);
            displayString.append(names[i]);
            displayString.append("\n");
        }

        // set string
        tvResults.setText(displayString);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

