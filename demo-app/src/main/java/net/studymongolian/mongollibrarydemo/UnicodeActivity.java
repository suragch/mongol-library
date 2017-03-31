package net.studymongolian.mongollibrarydemo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolFont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UnicodeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvLabel;
    TextView tvResults;
    MongolCode renderer;

    private static final char FVS1 = MongolCode.Uni.FVS1;
    private static final char FVS2 = MongolCode.Uni.FVS2;
    private static final char FVS3 = MongolCode.Uni.FVS3;
    private static final char ZWJ = MongolCode.Uni.ZWJ;
    private static final char MVS = MongolCode.Uni.MVS;
    private static final char NNBS = MongolCode.Uni.NNBS;
    private static final char A = MongolCode.Uni.A;
    private static final char E = MongolCode.Uni.E;
    private static final char I = MongolCode.Uni.I;
    private static final char O = MongolCode.Uni.O;
    private static final char U = MongolCode.Uni.U;
    private static final char OE = MongolCode.Uni.OE;
    private static final char UE = MongolCode.Uni.UE;
    private static final char EE = MongolCode.Uni.EE;
    private static final char NA = MongolCode.Uni.NA;
    private static final char ANG = MongolCode.Uni.ANG;
    private static final char BA = MongolCode.Uni.BA;
    private static final char PA = MongolCode.Uni.PA;
    private static final char QA = MongolCode.Uni.QA;
    private static final char GA = MongolCode.Uni.GA;
    private static final char MA = MongolCode.Uni.MA;
    private static final char LA = MongolCode.Uni.LA;
    private static final char SA = MongolCode.Uni.SA;
    private static final char SHA = MongolCode.Uni.SHA;
    private static final char TA = MongolCode.Uni.TA;
    private static final char DA = MongolCode.Uni.DA;
    private static final char CHA = MongolCode.Uni.CHA;
    private static final char JA = MongolCode.Uni.JA;
    private static final char YA = MongolCode.Uni.YA;
    private static final char RA = MongolCode.Uni.RA;
    private static final char WA = MongolCode.Uni.WA;
    private static final char FA = MongolCode.Uni.FA;
    private static final char KA = MongolCode.Uni.KA;
    private static final char KHA = MongolCode.Uni.KHA;
    private static final char TSA = MongolCode.Uni.TSA;
    private static final char ZA = MongolCode.Uni.ZA;
    private static final char HAA = MongolCode.Uni.HAA;
    private static final char ZRA = MongolCode.Uni.ZRA;
    private static final char LHA = MongolCode.Uni.LHA;
    private static final char ZHI = MongolCode.Uni.ZHI;
    private static final char CHI = MongolCode.Uni.CHI;

    private static final String[] names = {"A", "E", "I", "O", "U", "OE", "UE", "EE",
            "N", "NG", "B", "P", "Q", "G", "M", "L", "S", "SH", "T", "D", "CH", "J", "Y",
            "R", "W", "F", "K", "KH", "TS", "Z", "H", "ZR", "LH", "ZHI", "CHI"};
    private static final char[] characters = {A, E, I, O, U, OE, UE, EE,
            NA, ANG, BA, PA, QA, GA, MA, LA, SA, SHA, TA, DA, CHA, JA, YA,
            RA, WA, FA, KA, KHA, TSA, ZA, HAA, ZRA, LHA, ZHI, CHI};
    private static final String[] fonts = {"QAGAN", "GARQAG", "HARA", "SCNIN", "HAWANG",
            "QIMED", "NARIN", "MCDVNBAR", "AMGLANG", "SIDAM", "QINGMING", "ONQA_HARA",
            "SVGVNAG", "SVLBIYA", "JCLGQ",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unicode);

        // initialize renderer
        renderer = MongolCode.INSTANCE;

        // text views
        tvLabel = (TextView) findViewById(R.id.tvTitle);
        tvResults = (TextView) findViewById(R.id.tvUnicodeResults);

        // set Mongol font
        Typeface tf = MongolFont.get(MongolFont.QAGAN, this);
        //Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/MQG8F02.ttf");
        //Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/MenksoftHawang.ttf");
        tvResults.setTypeface(tf);

        // Chagaan tologai spinner
        List<String> viewChoices = new ArrayList<>(Arrays.asList(names));
        viewChoices.add("MVS");
        viewChoices.add("NNBS");
        viewChoices.add("Other");
        Spinner chagaanTologaiSpinner = (Spinner) findViewById(R.id.spinnerChagaanTolgai);
        ArrayAdapter<String> adapterChagaanTologai = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, viewChoices);
        adapterChagaanTologai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chagaanTologaiSpinner.setAdapter(adapterChagaanTologai);
        chagaanTologaiSpinner.setOnItemSelectedListener(this);

        // Font spinner
        List<String> fontChoices = new ArrayList<>(Arrays.asList(fonts));
        Spinner fontSpinner = (Spinner) findViewById(R.id.spinnerFont);
        ArrayAdapter<String> adapterFont = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fontChoices);
        adapterFont.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(adapterFont);
        fontSpinner.setOnItemSelectedListener(this);
    }

    private void setStrings(String label, String results) {
        String renderedResults = renderer.unicodeToMenksoft(results);
        tvLabel.setText(label);
        tvResults.setText(renderedResults);
        //tvResults.setText(results);
        //tvResults.setText("IJ");  // FIXME why does this give a Mongolian B with the HAWANG font?
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String item = String.valueOf(parent.getItemAtPosition(pos));

        int viewid = parent.getId();
        if (viewid == R.id.spinnerChagaanTolgai) {
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
            } else if (item.equals("Other")) {
                updateForOther();
            }
        } else if (viewid == R.id.spinnerFont) {
            String fontChoice;
            switch (item) {
                case "QAGAN":
                    fontChoice = MongolFont.QAGAN;
                    break;
                case "GARQAG":
                    fontChoice = MongolFont.GARQAG;
                    break;
                case "HARA":
                    fontChoice = MongolFont.HARA;
                    break;
                case "SCNIN":
                    fontChoice = MongolFont.SCNIN;
                    break;
                case "HAWANG":
                    fontChoice = MongolFont.HAWANG;
                    break;
                case "QIMED":
                    fontChoice = MongolFont.QIMED;
                    break;
                case "NARIN":
                    fontChoice = MongolFont.NARIN;
                    break;
                case "MCDVNBAR":
                    fontChoice = MongolFont.MCDVNBAR;
                    break;
                case "AMGLANG":
                    fontChoice = MongolFont.AMGLANG;
                    break;
                case "SIDAM":
                    fontChoice = MongolFont.SIDAM;
                    break;
                case "QINGMING":
                    fontChoice = MongolFont.QINGMING;
                    break;
                case "ONQA_HARA":
                    fontChoice = MongolFont.ONQA_HARA;
                    break;
                case "SVGVNAG":
                    fontChoice = MongolFont.SVGVNAG;
                    break;
                case "SVLBIYA":
                    fontChoice = MongolFont.SVLBIYA;
                    break;
                case "JCLGQ":
                    fontChoice = MongolFont.JCLGQ;
                    break;
                default:
                    Log.e("TAG", "onItemSelected: Font doesn't exist. Defaulting to QAGAN.");
                    fontChoice = MongolFont.QAGAN;
                    break;
            }
            Typeface tf = MongolFont.get(fontChoice, this);
            tvResults.setTypeface(tf);
        }

    }

    private void updateForNnbs() {

        // build string
        String space = "  ";
        StringBuilder displayString = new StringBuilder();
        displayString.append("Vocative Case").append("\n");
        //displayString.append(NNBS).append(A).append(space).append("A").append("\n");  // not needed?
        //displayString.append(NNBS).append(E).append(space).append("E").append("\n");  // not needed?
        //displayString.append("\n");
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

        // set strings
        setStrings("NNBS", displayString.toString());
        //tvResults.setText(displayString);
    }

    private void updateForMvs() {
        // update label
        //tvLabel.setText("MVS");

        // build string
        String space = "  ";
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

        // set strings
        setStrings("MVS", displayString.toString());
        //tvResults.setText(displayString);
    }

    private void updateForOther() {

        StringBuilder displayString = new StringBuilder();
        // Words from "A Study of Traditional Mongolian Script Encodings and Rendering"
        // http://www.colips.org/journals/volume21/21.1.3-Biligsaikhan.pdf
        displayString
                .append("\u182A\u1822\u1834\u1822\u182D").append("\n")
                .append("BICHIG").append("\n\n")

                .append("\u1821\u182D\u1821\u1830\u1822\u182D\u202F\u1822\u1828\u1826").append("\n")
                .append("EGESIG (NNBSP) INU").append("\n\n")

                .append("\u182A\u1826\u1835\u1822\u182D\u202F\u1822\u202F\u182A\u1821\u1828\u202F\u1836\u1826\u182D\u1821\u1828").append("\n")
                .append("BUJIG (NNBSP) I (NNBSP) BEN (NNBSP) YUGEN").append("\n\n")

                .append("\u1834\u1822\u1837\u1822\u182D\u202F\u182E\u1820\u1828\u1822").append("\n")
                .append("CHIRIG (NNBSP) MANI").append("\n\n")

                .append("\u1821\u182D\u1834\u1821").append("\n")
                .append("EGCHI").append("\n\n")

                .append("\u182C\u1826\u182D\u1835\u1822\u182E\u202F\u1833\u1826\u1837\u202F\u1822\u1836\u1821\u1828\u202F\u1833\u1821\u182D\u1821\u1828").append("\n")
                .append("QUGJIM (NNBSP) DUR (NNBSP) IYEN (NNBSP) DEGEN").append("\n\n")

                .append("\u182A\u1826\u1837\u1822\u1833\u182D\u1821\u182F\u202F\u1822\u1836\u1821\u1828").append("\n")
                .append("BURIDGEL (NNBSP) IYEN").append("\n\n")

                .append("\u1830\u1821\u1833\u182C\u1822\u182F\u202F\u182E\u1822\u1828\u1822").append("\n")
                .append("SEDQIL (NNBSP) MINI").append("\n\n")

                .append("\u1826\u1822\u182F\u1821\u1833\u182A\u1826\u1837\u1822\u202F\u1833\u1826").append("\n")
                .append("UILEDBURI (NNBSP) DU").append("\n\n")

                .append("\u1835\u1821\u182F\u1822\u182D\u1826\u1833\u182C\u1821\u1828\u202F\u1826").append("\n")
                .append("JELIGUDQEN (NNBSP) U").append("\n\n")

                .append("\u182E\u1820\u1829\u182D\u1820\u182F\u202F\u1833\u1824\u1837\u202F\u1822\u1836\u1820\u1828\u202F\u1833\u1820\u182D\u1820\u1828").append("\n")
                .append("MANGGAL (NNBSP) DUR (NNBSP) IYAN (NNBSP) DAGAN").append("\n\n")

                .append("\u1833\u180B\u1826\u1829\u202F\u1822").append("\n")
                .append("D(FVS1)UNG (NNBSP) I").append("\n\n")

                .append("\u1830\u1823\u1833\u1828\u1820\u182E\u202F\u1820\u1834\u1820\u202F\u182A\u1820\u1828\u202F\u1820\u1834\u1820\u182D\u1820\u1828").append("\n")
                .append("SODNAM (NNBSP) ACHA (NNBSP) BAN (NNBSP) ACHAGAN").append("\n\n")

                .append("\u1840\u1820\u182D\u182A\u1820\u202F\u182F\u1824\u182D\u180E\u1820").append("\n")
                .append("LHAGBA (NNBSP) LUG(MVS)A").append("\n\n")

                .append("\u1834\u1821\u182A\u1821\u182D\u182E\u1821\u1833\u202F\u182F\u1826\u182D\u1821").append("\n")
                .append("CHEBEGMED (NNBSP) LUGE").append("\n\n")

                .append("\u183C\u1827\u182E\u1827\u1828\u1832\u202F\u1832\u1820\u1836\u1822\u182D\u1820\u1828").append("\n")
                .append("TSEMENT (NNBSP) TAYIGAN").append("\n\n")

                .append("\u1826\u1828\u1822\u1836\u180E\u1821\u202F\u1832\u1821\u1836\u1822\u182D\u1821\u1828").append("\n")
                .append("UNIY(MVS)E (NNBSP) TEYIGEN").append("\n\n")

                .append("\u182C\u1823\u1836\u1822\u1828\u180E\u1820").append("\n")
                .append("QOYIN(MVS)A").append("\n\n")

                .append("\u1820\u1829\u1828\u180E\u1820").append("\n")
                .append("ANGN(MVS)A").append("\n\n");


        // Menksoft test words
        displayString
                .append("\u1834\u1822\u1829\u182D\u180E\u1820").append("\n")
                .append("CHINGG(MVS)A").append("\n\n")

                .append("\u1834\u1822\u1829\u182D\u1820\u182F\u1820\u182C\u1824").append("\n")
                .append("CHINGGALAQU").append("\n\n")

                .append("\u1833\u1820\u182F\u1835\u1822\u1836\u182D\u1830\u1820\u1828").append("\n")
                .append("DALJIYGSAN").append("\n\n")

                .append("\u1822\u182F\u182A\u1822\u182D\u1834\u1822").append("\n")
                .append("ILBIGCHI").append("\n\n")

                .append("\u182A\u1822\u1834\u1822\u182D\u1834\u1822").append("\n")
                .append("BICHIGCHI").append("\n\n")

                .append("\u1830\u1822\u182D\u1830\u1822\u182D\u182F\u1821\u182C\u1826").append("\n")
                .append("SIGSIGLEQU").append("\n\n")

                .append("\u1833\u1822\u182D\u182F\u1822\u182E\u1830\u1822\u182D\u1830\u1821\u1828").append("\n")
                .append("DIGLIMSIGSEN").append("\n\n")

                .append("\u1834\u1822\u182D\u1822\u182D\u182F\u1822\u182D").append("\n")
                .append("CHIGIGLIG").append("\n\n")

                .append("\u182E\u1825\u1829\u182D\u1821").append("\n")
                .append("MONGGE").append("\n\n")

                .append("\u183A\u1822\u1828\u1823\u180B").append("\n")
                .append("KINO(FVS1)").append("\n\n")

                .append("\u182A\u1820\u1836\u1822\u182D\u1824\u182F\u182C\u1824").append("\n")
                .append("BAYIGULQU").append("\n\n")

                .append("\u182D\u1837\u1827\u182D").append("\n")
                .append("GREG").append("\n\n")

                .append("\u182E\u1823\u1832\u180B\u1823\u1837").append("\n")
                .append("MOT(FVS1)OR").append("\n\n")

                .append("\u1828\u1821\u1836\u1822\u182D\u1821\u182E\u182F\u1822\u182D").append("\n")
                .append("NEYIGEMLIG").append("\n\n")

                .append("\u182E\u1823\u1829\u182D\u1823\u182F").append("\n")
                .append("MONGGOL").append("\n\n")

                .append("\u1836\u1820\u1832\u1824\u182D\u180E\u1820").append("\n")
                .append("YATUG(MVS)A").append("\n\n")

                .append("\u1824\u182F\u1820\u182D\u1820\u1828\u180E\u1820").append("\n")
                .append("OLAGAN(MVS)A").append("\n\n")

                .append("\u182A\u1822\u1834\u1822\u182D\u182F\u1821\u182C\u1826").append("\n")
                .append("BICHIGLEQU").append("\n\n")

                .append("\u182B\u1837\u1823\u182D\u180D\u1837\u1820\u182E").append("\n")
                .append("PROG(FVS3)RAM").append("\n\n")

                .append("\u183A\u1820\u1837\u1832").append("\n")
                .append("KART").append("\n\n")

                .append("\u1833\u180B\u1826\u1829\u1828\u1821\u182F\u1832\u1821").append("\n")
                .append("D(FVS1)UNGNELTE").append("\n\n");

        // Other test words
        displayString
                // G + consonant
                .append("\u182D\u1837\u1820\u182E").append("\n")
                .append("GRAM").append("\n\n")

                // NG + G
                .append("\u182E\u1822\u1829\u182D\u180E\u1820").append("\n")
                .append("MINGG(MVS)A").append("\n\n")

                // MVS in middle of word
                .append("\u182E\u1822\u1829\u182D\u180E\u1820\u1828").append("\n")
                .append("MINGG(MVS)AN").append("\n\n")

                // break context with ZWJ for single tooth I
                .append("\u1828\u1820\u200d\u1822\u182E\u1820").append("\n")
                .append("NA(ZWJ)IMA").append("\n\n")

                // double touth AI diphthong
                .append("\u1828\u1820\u1822\u1835\u1820").append("\n")
                .append("NAIJA").append("\n\n")

                // double touth AYI diphthong
                .append("\u1828\u1820\u1836\u1822\u1835\u1820").append("\n")
                .append("NAYIJA").append("\n\n")

                // double touth AI diphthong
                .append("\u182A\u1820\u1822\u1828\u180E\u1820").append("\n")
                .append("BAIN(MVS)A").append("\n\n")

                // double touth AYI diphthong
                .append("\u182A\u1820\u1836\u1822\u1828\u180E\u1820").append("\n")
                .append("BAYIN(MVS)A").append("\n\n")

                // sayihan (beautiful)
                .append("\u1830\u1820\u1836\u1822\u182C\u1820\u1828").append("\n")
                .append("SAYIHAN").append("\n\n")

                // sayihan (recently), Using ZWJ to break context rule
                .append("\u1830\u1820\u1836\u200d\u1822\u182C\u1820\u1828").append("\n")
                .append("SAY(ZWJ)IHAN").append("\n\n")

                // sayi (recently), Using ZWJ to break context rule
                .append("\u1830\u1820\u1836\u200d\u1822").append("\n")
                .append("SAY(ZWJ)I").append("\n\n")

                // final AI diphthong
                .append("\u1832\u1820\u1822").append("\n")
                .append("TAI").append("\n\n")

                // final AYI diphthong
                .append("\u1832\u1820\u1836\u1822").append("\n")
                .append("TAYI").append("\n\n")

                // final AYI diphthong with straight Y
                .append("\u1828\u1820\u182E\u1820\u1836\u180B\u1822").append("\n")
                .append("NAMAY(FVS1)I").append("\n\n")

                // EI diphthong
                .append("\u1821\u1836\u1822\u182E\u1826").append("\n")
                .append("EYIMU").append("\n\n")

                // normal medial Y
                .append("\u1830\u1823\u1836\u1823\u182F").append("\n")
                .append("SOYOL").append("\n\n")

                // buu exception
                .append("\u182A\u1826\u1826").append("\n")
                .append("BUU").append("\n\n")

                // normal way to display uu
                .append("\u182C\u1826\u1826").append("\n")
                .append("QUU").append("\n\n")

                // non long tooth UE in first syllable
                .append("\u182C\u1821\u1826\u182C\u1821\u1833").append("\n")
                .append("QEUQEN").append("\n\n")

                // ui not in initial syllable
                .append("\u182C\u1821\u1833\u1826\u1836\u1822\u1828").append("\n")
                .append("QEDUYIN").append("\n\n")

                // ui not in initial syllable
                .append("\u182C\u1821\u1833\u1826\u1822\u1828").append("\n")
                .append("QEDUIN").append("\n\n")

                // G between consonant in masculine word with L ligature
                .append("\u1820\u1829\u182D\u182F\u1822").append("\n")
                .append("ANGGLI").append("\n\n")

                // IG in masculine word + L ligature
                .append("\u1820\u1830\u1822\u182D\u182F\u1820\u1835\u1824").append("\n")
                .append("ASIGLAJU").append("\n\n")

                // compound name (NaranGerel) with mix of feminine and masculine plus G
                .append("\u1828\u1820\u1837\u1820\u1828\u182D\u1821\u1837\u1821\u182F").append("\n")
                .append("NARANGEREL").append("\n\n")

                // compound name (CholmonOdo) with ZWJ to break context rule
                .append("\u1834\u1823\u182F\u182E\u1823\u1828\u200d\u1823\u180B\u1833\u1823").append("\n")
                .append("CHOLMON(ZWJ)O(FVS1)DO").append("\n\n")

                // compound name (CholmonOdo) without ZWJ
                .append("\u1834\u1823\u182F\u182E\u1823\u1828\u1823\u180B\u1833\u1823").append("\n")
                .append("CHOLMONO(FVS1)DO").append("\n\n")

                // compound name (BayanUndur) with ZWJ to break context rule
                .append("\u182A\u1820\u1836\u1820\u1828\u200d\u1826\u180C\u1828\u1833\u1826\u1837").append("\n")
                .append("BAYAN(ZWJ)U(FVS1)NDUR").append("\n\n")

                // compound name (BayanUndur) without ZWJ
                .append("\u182A\u1820\u1836\u1820\u1828\u200d\u1826\u180C\u1828\u1833\u1826\u1837").append("\n")
                .append("BAYANU(FVS1)NDUR").append("\n\n")

                // compound name (SodoBilig) where final G is unspecified
                .append("\u1830\u1823\u1833\u1823\u182A\u1822\u182F\u1822\u182D").append("\n")
                .append("SODOBILIG").append("\n\n")

                // compound name (SodoBilig) where final G is unspecified
                .append("\u1830\u1823\u1833\u1823\u182A\u1822\u182F\u1822\u182D\u180C").append("\n")
                .append("SODOBILIG(FVS2)").append("\n\n")

                // compound name (AnggilomChecheg) where final G is unspecified
                .append("\u1820\u1829\u182D\u1822\u182F\u1824\u182E\u1834\u1821\u1834\u1821\u182D").append("\n")
                .append("ANGGILUMCHECHEG").append("\n\n")

                // final G in neuter word
                .append("\u1830\u1822\u182D").append("\n")
                .append("SIG").append("\n\n")

                // final masculine G in neuter word
                .append("\u1830\u1822\u182D\u180B").append("\n")
                .append("SIG(FVS1)").append("\n\n");

        // set strings
        setStrings("Other", displayString.toString());

        // A file could be specified as an alternate source of test words
        //
        //try {
        //    // See this link for some test words to compare
        //    // http://www.colips.org/journals/volume21/21.1.3-Biligsaikhan.pdf
        //    String displayString = readFromAssets(this, "UnicodeWordTestList.txt");
        //    //tvLabel.setText("Other");
        //    //tvResults.setText(displayString);
        //    setStrings("Other", displayString);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line).append("\n");
            line = reader.readLine();
        }
        reader.close();
        return stringBuilder.toString();
    }

    private void updateTextViews(char unicode, String name) {

        // build string
        String space = "  ";
        StringBuilder displayString = new StringBuilder();
        displayString.append("Isolate").append("\n");
        displayString.append(unicode).append("\n");
        displayString.append(unicode).append(FVS1).append(space).append("FVS1").append("\n");
        displayString.append(unicode).append(FVS2).append(space).append("FVS2").append("\n");
        displayString.append(unicode).append(FVS3).append(space).append("FVS3").append("\n\n");
        displayString.append("Initial").append("\n");
        displayString.append(unicode).append(ZWJ).append("\n");
        displayString.append(unicode).append(FVS1).append(ZWJ).append(space).append("FVS1").append("\n");
        displayString.append(unicode).append(FVS2).append(ZWJ).append(space).append("FVS2").append("\n");
        displayString.append(unicode).append(FVS3).append(ZWJ).append(space).append("FVS3").append("\n\n");
        displayString.append("Medial").append("\n");
        displayString.append(ZWJ).append(unicode).append(ZWJ).append("\n");
        displayString.append(ZWJ).append(unicode).append(FVS1).append(ZWJ).append(space).append("FVS1").append("\n");
        displayString.append(ZWJ).append(unicode).append(FVS2).append(ZWJ).append(space).append("FVS2").append("\n");
        displayString.append(ZWJ).append(unicode).append(FVS3).append(ZWJ).append(space).append("FVS3").append("\n\n");
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

        // set strings
        setStrings(name, displayString.toString());
        //tvResults.setText(displayString);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

