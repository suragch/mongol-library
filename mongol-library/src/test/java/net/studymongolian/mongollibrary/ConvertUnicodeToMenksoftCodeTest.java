package net.studymongolian.mongollibrary;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConvertUnicodeToMenksoftCodeTest {

    private MongolCode converter = MongolCode.INSTANCE;

    // extracting the method name so that it can change without breaking all of the tests
    private String convert(String unicode) {
        return converter.unicodeToMenksoft(unicode);
    }


    ////////////////// non-Mongol strings ////////////////////


    @Test
    public void emptyString() {
        String unicode = "";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void nullString() {
        String unicode = null;
        String result = convert(unicode);
        String expected = null;
        assertEquals(expected, result);
    }

    @Test
    public void englishKeyboardChars() {
        String unicode = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=_+`~;:'\" ,./<>?";
        String result = convert(unicode);
        String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=_+`~;:'\" ,./<>?";
        assertEquals(expected, result);
    }

    @Test
    public void chineseChars() {
        String unicode = "中文";
        String result = convert(unicode);
        String expected = "中文";
        assertEquals(expected, result);
    }

    @Test
    public void emojiChars() {
        String unicode = "\uD83D\uDE04\uD83D\uDD90\uD83C\uDFFC\uD83C\uDDF3\uD83C\uDDF4";
        String result = convert(unicode);
        String expected = "\uD83D\uDE04\uD83D\uDD90\uD83C\uDFFC\uD83C\uDDF3\uD83C\uDDF4";
        assertEquals(expected, result);
    }

    @Test
    public void menksoftString() {
        String unicode = "\uE2F2\uE291\uE2BC\uE2EC\uE291\uE2F9";
        String result = convert(unicode);
        String expected = "\uE2F2\uE291\uE2BC\uE2EC\uE291\uE2F9";
        assertEquals(expected, result);
    }

    // nirugu

    @Test
    public void niruguChar() {
        String unicode = "\u180A";
        String result = convert(unicode);
        String expected = "\uE23E";
        assertEquals(expected, result);
    }

    // tests for all combinations of Unicode+FVS (even undefined ones)

    @Test
    public void aIsol() {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInit() {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void aMedi() {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void aFina() {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void aIsolFvs1() {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInitFvs1() {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void aMediFvs1() {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void aFinaFvs1() {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void aIsolFvs2() {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInitFvs2() {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void aMediFvs2() {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void aFinaFvs2() {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void aIsolFvs3() {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInitFvs3() {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void aMediFvs3() {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void aFinaFvs3() {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsol() {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInit() {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eMedi() {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eFina() {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsolFvs1() {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInitFvs1() {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void eMediFvs1() {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eFinaFvs1() {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsolFvs2() {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInitFvs2() {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void eMediFvs2() {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eFinaFvs2() {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsolFvs3() {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInitFvs3() {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void eMediFvs3() {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eFinaFvs3() {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsol() {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInit() {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void iMedi() {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void iFina() {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsolFvs1() {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInitFvs1() {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void iMediFvs1() {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void iFinaFvs1() {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsolFvs2() {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInitFvs2() {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void iMediFvs2() {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void iFinaFvs2() {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsolFvs3() {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInitFvs3() {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void iMediFvs3() {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void iFinaFvs3() {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsol() {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInit() {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oMedi() {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oFina() {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsolFvs1() {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInitFvs1() {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void oMediFvs1() {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oFinaFvs1() {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsolFvs2() {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInitFvs2() {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void oMediFvs2() {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oFinaFvs2() {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsolFvs3() {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInitFvs3() {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void oMediFvs3() {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oFinaFvs3() {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsol() {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInit() {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void uMedi() {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void uFina() {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsolFvs1() {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInitFvs1() {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void uMediFvs1() {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void uFinaFvs1() {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsolFvs2() {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInitFvs2() {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void uMediFvs2() {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void uFinaFvs2() {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsolFvs3() {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInitFvs3() {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void uMediFvs3() {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void uFinaFvs3() {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsol() {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInit() {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oeMedi() {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oeFina() {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsolFvs1() {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInitFvs1() {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void oeMediFvs1() {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oeFinaFvs1() {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsolFvs2() {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInitFvs2() {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void oeMediFvs2() {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oeFinaFvs2() {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsolFvs3() {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInitFvs3() {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void oeMediFvs3() {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void oeFinaFvs3() {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsol() {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInit() {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ueMedi() {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ueFina() {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsolFvs1() {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInitFvs1() {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void ueMediFvs1() {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ueFinaFvs1() {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsolFvs2() {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInitFvs2() {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void ueMediFvs2() {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ueFinaFvs2() {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsolFvs3() {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInitFvs3() {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void ueMediFvs3() {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ueFinaFvs3() {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsol() {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInit() {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eeMedi() {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eeFina() {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsolFvs1() {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInitFvs1() {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void eeMediFvs1() {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eeFinaFvs1() {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsolFvs2() {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInitFvs2() {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void eeMediFvs2() {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eeFinaFvs2() {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsolFvs3() {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInitFvs3() {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void eeMediFvs3() {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void eeFinaFvs3() {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsol() {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInit() {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void nMedi() {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void nFina() {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsolFvs1() {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInitFvs1() {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void nMediFvs1() {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void nFinaFvs1() {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsolFvs2() {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInitFvs2() {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void nMediFvs2() {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void nFinaFvs2() {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsolFvs3() {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInitFvs3() {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void nMediFvs3() {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void nFinaFvs3() {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsol() {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInit() {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ngMedi() {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ngFina() {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsolFvs1() {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInitFvs1() {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void ngMediFvs1() {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ngFinaFvs1() {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsolFvs2() {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInitFvs2() {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void ngMediFvs2() {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ngFinaFvs2() {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsolFvs3() {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInitFvs3() {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void ngMediFvs3() {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void ngFinaFvs3() {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsol() {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInit() {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void bMedi() {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void bFina() {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsolFvs1() {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInitFvs1() {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void bMediFvs1() {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void bFinaFvs1() {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsolFvs2() {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInitFvs2() {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void bMediFvs2() {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void bFinaFvs2() {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsolFvs3() {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInitFvs3() {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void bMediFvs3() {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void bFinaFvs3() {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsol() {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInit() {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void pMedi() {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void pFina() {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsolFvs1() {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInitFvs1() {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void pMediFvs1() {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void pFinaFvs1() {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsolFvs2() {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInitFvs2() {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void pMediFvs2() {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void pFinaFvs2() {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsolFvs3() {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInitFvs3() {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void pMediFvs3() {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void pFinaFvs3() {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsol() {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInit() {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void qMedi() {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void qFina() {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsolFvs1() {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInitFvs1() {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void qMediFvs1() {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void qFinaFvs1() {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsolFvs2() {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInitFvs2() {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void qMediFvs2() {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void qFinaFvs2() {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsolFvs3() {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInitFvs3() {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void qMediFvs3() {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void qFinaFvs3() {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsol() {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInit() {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void gMedi() {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void gFina() {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsolFvs1() {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInitFvs1() {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void gMediFvs1() {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void gFinaFvs1() {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsolFvs2() {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInitFvs2() {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void gMediFvs2() {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void gFinaFvs2() {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsolFvs3() {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInitFvs3() {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void gMediFvs3() {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void gFinaFvs3() {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsol() {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInit() {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void mMedi() {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void mFina() {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsolFvs1() {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInitFvs1() {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void mMediFvs1() {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void mFinaFvs1() {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsolFvs2() {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInitFvs2() {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void mMediFvs2() {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void mFinaFvs2() {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsolFvs3() {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInitFvs3() {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void mMediFvs3() {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void mFinaFvs3() {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsol() {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInit() {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lMedi() {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lFina() {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsolFvs1() {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInitFvs1() {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void lMediFvs1() {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lFinaFvs1() {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsolFvs2() {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInitFvs2() {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void lMediFvs2() {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lFinaFvs2() {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsolFvs3() {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInitFvs3() {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void lMediFvs3() {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lFinaFvs3() {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsol() {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInit() {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void sMedi() {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void sFina() {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsolFvs1() {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInitFvs1() {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void sMediFvs1() {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void sFinaFvs1() {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsolFvs2() {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInitFvs2() {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void sMediFvs2() {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void sFinaFvs2() {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsolFvs3() {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInitFvs3() {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void sMediFvs3() {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void sFinaFvs3() {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsol() {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInit() {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void shMedi() {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void shFina() {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsolFvs1() {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInitFvs1() {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void shMediFvs1() {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void shFinaFvs1() {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsolFvs2() {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInitFvs2() {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void shMediFvs2() {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void shFinaFvs2() {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsolFvs3() {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInitFvs3() {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void shMediFvs3() {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void shFinaFvs3() {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsol() {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInit() {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tMedi() {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tFina() {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsolFvs1() {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInitFvs1() {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void tMediFvs1() {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tFinaFvs1() {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsolFvs2() {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInitFvs2() {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void tMediFvs2() {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tFinaFvs2() {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsolFvs3() {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInitFvs3() {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void tMediFvs3() {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tFinaFvs3() {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsol() {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInit() {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void dMedi() {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void dFina() {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsolFvs1() {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInitFvs1() {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void dMediFvs1() {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void dFinaFvs1() {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsolFvs2() {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInitFvs2() {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void dMediFvs2() {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void dFinaFvs2() {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsolFvs3() {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInitFvs3() {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void dMediFvs3() {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void dFinaFvs3() {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsol() {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInit() {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chMedi() {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chFina() {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsolFvs1() {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInitFvs1() {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void chMediFvs1() {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chFinaFvs1() {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsolFvs2() {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInitFvs2() {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void chMediFvs2() {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chFinaFvs2() {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsolFvs3() {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInitFvs3() {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void chMediFvs3() {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chFinaFvs3() {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsol() {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInit() {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void jMedi() {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void jFina() {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsolFvs1() {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInitFvs1() {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void jMediFvs1() {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void jFinaFvs1() {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsolFvs2() {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInitFvs2() {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void jMediFvs2() {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void jFinaFvs2() {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsolFvs3() {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInitFvs3() {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void jMediFvs3() {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void jFinaFvs3() {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsol() {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInit() {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void yMedi() {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void yFina() {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsolFvs1() {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInitFvs1() {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void yMediFvs1() {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void yFinaFvs1() {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsolFvs2() {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInitFvs2() {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void yMediFvs2() {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void yFinaFvs2() {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsolFvs3() {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInitFvs3() {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void yMediFvs3() {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void yFinaFvs3() {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsol() {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInit() {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void rMedi() {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void rFina() {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsolFvs1() {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInitFvs1() {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void rMediFvs1() {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void rFinaFvs1() {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsolFvs2() {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInitFvs2() {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void rMediFvs2() {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void rFinaFvs2() {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsolFvs3() {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInitFvs3() {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void rMediFvs3() {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void rFinaFvs3() {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsol() {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInit() {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void wMedi() {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void wFina() {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsolFvs1() {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInitFvs1() {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void wMediFvs1() {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void wFinaFvs1() {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsolFvs2() {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInitFvs2() {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void wMediFvs2() {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void wFinaFvs2() {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsolFvs3() {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInitFvs3() {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void wMediFvs3() {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void wFinaFvs3() {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsol() {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInit() {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void fMedi() {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void fFina() {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsolFvs1() {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInitFvs1() {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void fMediFvs1() {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void fFinaFvs1() {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsolFvs2() {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInitFvs2() {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void fMediFvs2() {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void fFinaFvs2() {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsolFvs3() {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInitFvs3() {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void fMediFvs3() {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void fFinaFvs3() {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsol() {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInit() {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void kMedi() {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void kFina() {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsolFvs1() {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInitFvs1() {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void kMediFvs1() {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void kFinaFvs1() {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsolFvs2() {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInitFvs2() {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void kMediFvs2() {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void kFinaFvs2() {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsolFvs3() {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInitFvs3() {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void kMediFvs3() {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void kFinaFvs3() {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsol() {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInit() {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void khMedi() {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void khFina() {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsolFvs1() {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInitFvs1() {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void khMediFvs1() {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void khFinaFvs1() {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsolFvs2() {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInitFvs2() {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void khMediFvs2() {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void khFinaFvs2() {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsolFvs3() {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInitFvs3() {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void khMediFvs3() {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void khFinaFvs3() {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsol() {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInit() {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tsMedi() {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tsFina() {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsolFvs1() {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInitFvs1() {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void tsMediFvs1() {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tsFinaFvs1() {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsolFvs2() {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInitFvs2() {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void tsMediFvs2() {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tsFinaFvs2() {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsolFvs3() {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInitFvs3() {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void tsMediFvs3() {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void tsFinaFvs3() {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsol() {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInit() {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zMedi() {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zFina() {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsolFvs1() {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInitFvs1() {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zMediFvs1() {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zFinaFvs1() {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsolFvs2() {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInitFvs2() {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zMediFvs2() {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zFinaFvs2() {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsolFvs3() {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInitFvs3() {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zMediFvs3() {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zFinaFvs3() {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsol() {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInit() {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void haaMedi() {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void haaFina() {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsolFvs1() {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInitFvs1() {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void haaMediFvs1() {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void haaFinaFvs1() {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsolFvs2() {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInitFvs2() {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void haaMediFvs2() {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void haaFinaFvs2() {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsolFvs3() {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInitFvs3() {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void haaMediFvs3() {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void haaFinaFvs3() {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsol() {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInit() {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zrMedi() {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zrFina() {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsolFvs1() {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInitFvs1() {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zrMediFvs1() {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zrFinaFvs1() {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsolFvs2() {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInitFvs2() {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zrMediFvs2() {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zrFinaFvs2() {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsolFvs3() {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInitFvs3() {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zrMediFvs3() {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zrFinaFvs3() {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsol() {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInit() {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lhMedi() {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lhFina() {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsolFvs1() {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInitFvs1() {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void lhMediFvs1() {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lhFinaFvs1() {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsolFvs2() {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInitFvs2() {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void lhMediFvs2() {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lhFinaFvs2() {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsolFvs3() {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInitFvs3() {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void lhMediFvs3() {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void lhFinaFvs3() {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsol() {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInit() {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zhiMedi() {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFina() {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsolFvs1() {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInitFvs1() {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zhiMediFvs1() {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFinaFvs1() {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsolFvs2() {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInitFvs2() {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zhiMediFvs2() {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFinaFvs2() {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsolFvs3() {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInitFvs3() {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void zhiMediFvs3() {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFinaFvs3() {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsol() {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInit() {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chiMedi() {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chiFina() {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsolFvs1() {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInitFvs1() {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void chiMediFvs1() {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chiFinaFvs1() {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsolFvs2() {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInitFvs2() {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void chiMediFvs2() {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chiFinaFvs2() {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsolFvs3() {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInitFvs3() {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "" + "\u200D"; 
        assertEquals(expected, result);
    }

    @Test
    public void chiMediFvs3() {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "\u200D" + "" + "\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void chiFinaFvs3() {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "\u200D" + "";
        assertEquals(expected, result);
    }







    ///////////////////////// Words ////////////////////////////





    @Test
    public void bichigWord() {
        String unicode = "ᠪᠢᠴᠢᠭ"; // bichig
        String result = convert(unicode);
        String expected = "\uE2C1\uE27F\uE317\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void egeshigWord() {
        String unicode = "ᠡᠭᠡᠰᠢᠭ ᠢᠨᠦ"; // EGESIG (NNBSP) INU
        String result = convert(unicode);
        String expected = "\uE271\uE2EB\uE277\uE301\uE27E\uE2E8 \uE27A\uE2B9\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void bujigWord() {
        String unicode = "ᠪᠦᠵᠢᠭ ᠢ ᠪᠡᠨ ᠶᠦᠭᠡᠨ"; // BUJIG (NNBSP) I (NNBSP) BEN (NNBSP) YUGEN
        String result = convert(unicode);
        String expected = "\uE2C2\uE2AA\uE31D\uE27E\uE2E8 \uE282 \uE2C1\uE277\uE2B5 \uE31E\uE2AB\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void chirigWord() {
        String unicode = "ᠴᠢᠷᠢᠭ ᠮᠠᠨᠢ"; // CHIRIG (NNBSP) MANI
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE327\uE27E\uE2E8 \uE2F1\uE26C\uE2B7\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void egchiWord() {
        String unicode = "ᠡᠭᠴᠡ"; // EGCHI
        String result = convert(unicode);
        String expected = "\uE271\uE2F0\uE317\uE273";
        assertEquals(expected, result);
    }

    @Test
    public void qugjimWord() {
        String unicode = "ᠬᠦᠭᠵᠢᠮ ᠳᠦᠷ ᠢᠶᠡᠨ ᠳᠡᠭᠡᠨ"; // QUGJIM (NNBSP) DUR (NNBSP) IYEN (NNBSP) DEGEN
        String result = convert(unicode);
        String expected = "\uE2D4\uE2AA\uE2F0\uE31D\uE27E\uE2F3 \uE310\uE2AB\uE325 \uE280\uE321\uE276\uE2B5 \uE310\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void buridgelWord() {
        String unicode = "ᠪᠦᠷᠢᠳᠭᠡᠯ ᠢᠶᠡᠨ"; // BURIDGEL (NNBSP) IYEN
        String result = convert(unicode);
        String expected = "\uE2C2\uE2AA\uE327\uE27E\uE314\uE2EB\uE277\uE2F9 \uE280\uE321\uE276\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void sedqilWord() {
        String unicode = "ᠰᠡᠳᠬᠢᠯ ᠮᠢᠨᠢ"; // SEDQIL (NNBSP) MINI
        String result = convert(unicode);
        String expected = "\uE2FD\uE276\uE314\uE2DA\uE27F\uE2F9 \uE2F1\uE27E\uE2B7\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void uiledburiWord() {
        String unicode = "ᠦᠢᠯᠡᠳᠪᠦᠷᠢ ᠳᠦ"; // UILEDBURI (NNBSP) DU
        String result = convert(unicode);
        String expected = "\uE2A2\uE27E\uE2FA\uE276\uE314\uE2C6\uE2AC\uE327\uE27B \uE310\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void jeligudqenWord() {
        String unicode = "ᠵᠡᠯᠢᠭᠦᠳᠬᠡᠨ ᠦ"; // JELIGUDQEN (NNBSP) U
        String result = convert(unicode);
        String expected = "\uE31A\uE276\uE2FA\uE27E\uE2ED\uE2AC\uE314\uE2DA\uE277\uE2B5 \uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void manggalWord() {
        String unicode = "ᠮᠠᠩᠭᠠᠯ ᠳᠤᠷ ᠢᠶᠠᠨ ᠳᠠᠭᠠᠨ"; // MANGGAL (NNBSP) DUR (NNBSP) IYAN (NNBSP) DAGAN
        String result = convert(unicode);
        String expected = "\uE2F1\uE26C\uE2BC\uE2EA\uE26C\uE2F9 \uE310\uE291\uE325 \uE280\uE321\uE26C\uE2B5 \uE310\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void dengWord() {
        String unicode = "ᠳ᠋ᠦᠩ ᠢ"; // D(FVS1)UNG (NNBSP) I
        String result = convert(unicode);
        String expected = "\uE310\uE2A9\uE2BB \uE282";
        assertEquals(expected, result);
    }

    @Test
    public void sodnamWord() {
        String unicode = "ᠰᠣᠳᠨᠠᠮ ᠠᠴᠠ ᠪᠠᠨ ᠠᠴᠠᠭᠠᠨ"; // SODNAM (NNBSP) ACHA (NNBSP) BAN (NNBSP) ACHAGAN
        String result = convert(unicode);
        String expected = "\uE2FE\uE289\uE314\uE2B7\uE26C\uE2F3 \uE267\uE317\uE268 \uE2C1\uE26D\uE2B5 \uE267\uE317\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void lhagbaWord() {
        String unicode = "ᡀᠠᠭᠪᠠ ᠯᠤᠭ᠎ᠠ"; // LHAGBA (NNBSP) LUG(MVS)A
        String result = convert(unicode);
        String expected = "\uE34B\uE26C\uE2EE\uE2C5\uE26B \uE2F8\uE291\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void chebegmedWord() {
        String unicode = "ᠴᠡᠪᠡᠭᠮᠡᠳ ᠯᠦᠭᠡ"; // CHEBEGMED (NNBSP) LUGE
        String result = convert(unicode);
        String expected = "\uE315\uE276\uE2C5\uE277\uE2EB\uE2F6\uE276\uE311 \uE2F8\uE2AB\uE2EB\uE275";
        assertEquals(expected, result);
    }

    @Test
    public void tsementWord() {
        String unicode = "ᠼᠧᠮᠧᠨᠲ ᠲᠠᠶᠢᠭᠠᠨ"; // TSEMENT (NNBSP) TAYIGAN
        String result = convert(unicode);
        String expected = "\uE33F\uE2B0\uE2F4\uE2B0\uE2BA\uE30A \uE308\uE26C\uE321\uE27E\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void uniyeWord() {
        String unicode = "ᠦᠨᠢᠶ᠎ᠡ ᠲᠡᠶᠢᠭᠡᠨ"; // UNIY(MVS)E (NNBSP) TEYIGEN
        String result = convert(unicode);
        String expected = "\uE2A2\uE2B7\uE27E\uE31F\uE274 \uE308\uE276\uE321\uE27E\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void qoyinaWord() {
        String unicode = "ᠬᠣᠶᠢᠨ᠎ᠠ"; // QOYIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2D2\uE289\uE321\uE27E\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void angnaWord() {
        String unicode = "ᠠᠩᠨ᠎ᠠ"; // ANGN(MVS)A
        String result = convert(unicode);
        String expected = "\uE266\uE2BE\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void chinggaWord() {
        String unicode = "ᠴᠢᠩᠭ᠎ᠠ"; // CHINGG(MVS)A
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE2BC\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void chingalaquWord() {
        String unicode = "ᠴᠢᠩᠭᠠᠯᠠᠬᠤ"; // CHINGGALAQU
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE2BC\uE2EA\uE26C\uE2FA\uE26C\uE2DC\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void daljiygsanWord() {
        String unicode = "ᠳᠠᠯᠵᠢᠶᠭᠰᠠᠨ"; // DALJIYGSAN
        String result = convert(unicode);
        String expected = "\uE30E\uE26C\uE2FB\uE31D\uE27E\uE321\uE2EE\uE301\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void ilbigchiWord() {
        String unicode = "ᠢᠯᠪᠢᠭᠴᠢ"; // ILBIGCHI
        String result = convert(unicode);
        String expected = "\uE27A\uE2FB\uE2C5\uE27F\uE2F0\uE317\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void bichigchiWord() {
        String unicode = "ᠪᠢᠴᠢᠭᠴᠢ"; // BICHIGCHI
        String result = convert(unicode);
        String expected = "\uE2C1\uE27F\uE317\uE27E\uE2F0\uE317\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void sigsiglequWord() {
        String unicode = "ᠰᠢᠭᠰᠢᠭᠯᠡᠬᠦ"; // SIGSIGLEQU
        String result = convert(unicode);
        String expected = "\uE2FD\uE27E\uE2EB\uE301\uE27E\uE2EB\uE2FC\uE276\uE2DD\uE2A7";
        assertEquals(expected, result);
    }

    @Test
    public void diglimsigsenWord() {
        String unicode = "ᠳᠢᠭᠯᠢᠮᠰᠢᠭᠰᠡᠨ"; // DIGLIMSIGSEN
        String result = convert(unicode);
        String expected = "\uE30E\uE27E\uE2EB\uE2FC\uE27E\uE2F4\uE301\uE27E\uE2EB\uE301\uE276\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void chigigligWord() {
        String unicode = "ᠴᠢᠭᠢᠭᠯᠢᠭ"; // CHIGIGLIG
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE2EB\uE27F\uE2EB\uE2FC\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void monggeWord() {
        String unicode = "ᠮᠥᠩᠭᠡ"; // MONGGE
        String result = convert(unicode);
        String expected = "\uE2F2\uE29C\uE2BD\uE2EB\uE275";
        assertEquals(expected, result);
    }

    @Test
    public void kinoWord() {
        String unicode = "ᠺᠢᠨᠣ᠋"; // KINO(FVS1)
        String result = convert(unicode);
        String expected = "\uE333\uE27F\uE2B9\uE286";
        assertEquals(expected, result);
    }

    @Test
    public void bayigulquWord() {
        String unicode = "ᠪᠠᠶᠢᠭᠤᠯᠬᠤ"; // BAYIGULQU
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE321\uE27E\uE2EC\uE291\uE2FA\uE2DC\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void gregWord() {
        String unicode = "ᠭᠷᠧᠭ"; // GREG
        String result = convert(unicode);
        String expected = "\uE2E3\uE327\uE2B0\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void motorWord() {
        String unicode = "ᠮᠣᠲ᠋ᠣᠷ"; // MOT(FVS1)OR
        String result = convert(unicode);
        String expected = "\uE2F2\uE289\uE30D\uE289\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void neigemligWord() {
        String unicode = "ᠨᠡᠶᠢᠭᠡᠮᠯᠢᠭ"; // NEYIGEMLIG
        String result = convert(unicode);
        String expected = "\uE2B1\uE276\uE321\uE27E\uE2EB\uE277\uE2F5\uE2FA\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void mongolWord() {
        String unicode = "ᠮᠣᠩᠭᠣᠯ"; // MONGGOL
        String result = convert(unicode);
        String expected = "\uE2F2\uE289\uE2BC\uE2EC\uE289\uE2F9";
        assertEquals(expected, result);
    }

    @Test
    public void yatugaWord() {
        String unicode = "ᠶᠠᠲᠤᠭ᠎ᠠ"; // YATUG(MVS)A
        String result = convert(unicode);
        String expected = "\uE31E\uE26C\uE30B\uE291\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void olaganaWord() {
        String unicode = "ᠤᠯᠠᠭᠠᠨ᠎ᠠ"; // OLAGAN(MVS)A
        String result = convert(unicode);
        String expected = "\uE28C\uE2FA\uE26C\uE2EA\uE26C\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void bichiglequWord() {
        String unicode = "ᠪᠢᠴᠢᠭᠯᠡᠬᠦ"; // BICHIGLEQU
        String result = convert(unicode);
        String expected = "\uE2C1\uE27F\uE317\uE27E\uE2EB\uE2FC\uE276\uE2DD\uE2A7";
        assertEquals(expected, result);
    }

    @Test
    public void programWord() {
        String unicode = "ᠫᠷᠣᠭ᠍ᠷᠠᠮ"; // PROG(FVS3)RAM
        String result = convert(unicode);
        String expected = "\uE2C8\uE326\uE289\uE2EF\uE327\uE26C\uE2F3";
        assertEquals(expected, result);
    }

    @Test
    public void kartWord() {
        String unicode = "ᠺᠠᠷᠲ"; // KART
        String result = convert(unicode);
        String expected = "\uE333\uE26D\uE326\uE30A";
        assertEquals(expected, result);
    }

    @Test
    public void dungnelteWord() {
        String unicode = "ᠳ᠋ᠦᠩᠨᠡᠯᠲᠡ"; // D(FVS1)UNGNELTE
        String result = convert(unicode);
        String expected = "\uE310\uE2A9\uE2BC\uE2B7\uE276\uE2FA\uE30B\uE273";
        assertEquals(expected, result);
    }

    @Test
    public void gramWord() {
        String unicode = "ᠭᠷᠠᠮ"; // GRAM
        String result = convert(unicode);
        String expected = "\uE2E3\uE327\uE26C\uE2F3";
        assertEquals(expected, result);
    }

    @Test
    public void minggaWord() {
        String unicode = "ᠮᠢᠩᠭ᠎ᠠ"; // MINGG(MVS)A
        String result = convert(unicode);
        String expected = "\uE2F1\uE27E\uE2BC\uE2E9\uE26A";
        assertEquals(expected, result);
    }

//    @Test
//    public void mingganWord() {
//        String unicode = "ᠮᠢᠩᠭ᠎ᠠᠨ"; // MINGG(MVS)AN
//        String result = convert(unicode);
//        String expected = "\uE2F1\uE27E\uE2BC\uE2EA\uE26C\uE2B5";
//        assertEquals(expected, result);
//    }

    @Test
    public void naimaWord() {
        String unicode = "ᠨᠠ\u200Dᠢᠮᠠ"; // NA(ZWJ)IMA
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\u200D\uE27E\uE2F5\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void naimaWordMenksoftVersion() {
        String unicode = "ᠨᠠᠢ\u180Cᠮᠠ"; // NAI(FVS2)MA (not defined in Unicode 10.0)
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE27E\uE2F5\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void naijaWord() {
        String unicode = "ᠨᠠᠢᠵᠠ"; // NAIJA
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE281\uE31D\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void nayijaWord() {
        String unicode = "ᠨᠠᠶᠢᠵᠠ"; // NAYIJA
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE321\uE27E\uE31D\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void bainaWord() {
        String unicode = "ᠪᠠᠢᠨ᠎ᠠ"; // BAIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE281\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void bayinaWord() {
        String unicode = "ᠪᠠᠶᠢᠨ᠎ᠠ"; // BAYIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE321\uE27E\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void baiinaWord() {
        String unicode = "ᠪᠠᠢᠢᠨ᠎ᠠ"; // BAIIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE27E\uE27E\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void saihanWord() {
        String unicode = "ᠰᠠᠶᠢᠬᠠᠨ"; // SAYIHAN
        String result = convert(unicode);
        String expected = "\uE2FD\uE26C\uE321\uE27E\uE2D8\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void sayihanWord() {
        String unicode = "ᠰᠠᠶ\u180Bᠢᠬᠠᠨ"; // SAY(FVS1)IHAN
        String result = convert(unicode);
        String expected = "\uE2FD\uE26C\uE320\uE27E\uE2D8\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void sayiWord() {
        String unicode = "ᠰᠠᠶ\u180Bᠢ"; // SAY(FVS1)I
        String result = convert(unicode);
        String expected = "\uE2FD\uE26C\uE320\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void taiWord() {
        String unicode = "ᠲᠠᠢ"; // TAI
        String result = convert(unicode);
        String expected = "\uE308\uE26C\uE27B";
        assertEquals(expected, result);
    }

    // no longer supporting
    //@Test
    //public void tayiWord() {
    //    String unicode = "ᠲᠠᠶᠢ"; // TAYI
    //    String result = convert(unicode);
    //    String expected = "\uE308\uE26C\uE27B";
    //    assertEquals(expected, result);
    //}

    @Test
    public void namayiWord() {
        String unicode = "ᠨᠠᠮᠠᠶᠢ"; // NAMAYI
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE2F4\uE26C\uE321\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void eyimuWord() {
        String unicode = "ᠡᠶᠢᠮᠦ"; // EYIMU
        String result = convert(unicode);
        String expected = "\uE271\uE321\uE27E\uE2F5\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void soyolWord() {
        String unicode = "ᠰᠣᠶᠣᠯ"; // SOYOL
        String result = convert(unicode);
        String expected = "\uE2FE\uE289\uE320\uE289\uE2F9";
        assertEquals(expected, result);
    }

    @Test
    public void buuWord() {
        String unicode = "ᠪᠦᠦ"; // BUU
        String result = convert(unicode);
        String expected = "\uE2C2\uE2AC\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void quuWord() {
        String unicode = "ᠬᠦᠦ"; // QUU
        String result = convert(unicode);
        String expected = "\uE2D4\uE2AA\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void qeuqenWord() {
        String unicode = "ᠬᠡᠦᠬᠡᠳ"; // QEUQEN
        String result = convert(unicode);
        String expected = "\uE2D0\uE277\uE2AB\uE2DA\uE277\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void qeduyinWord() {
        String unicode = "ᠬᠡᠳᠦᠶᠢᠨ"; // QEDUYIN
        String result = convert(unicode);
        String expected = "\uE2D0\uE277\uE313\uE2AB\uE321\uE27E\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void qeduinWord() {
        String unicode = "ᠬᠡᠳᠦᠢᠨ"; // QEDUIN
        String result = convert(unicode);
        String expected = "\uE2D0\uE277\uE313\uE2AB\uE281\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void anggliWord() {
        String unicode = "ᠠᠩᠭᠯᠢ"; // ANGGLI
        String result = convert(unicode);
        String expected = "\uE266\uE2BD\uE2EB\uE2FC\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void asiglajuWord() {
        String unicode = "ᠠᠰᠢᠭᠯᠠᠵᠤ"; // ASIGLAJU
        String result = convert(unicode);
        String expected = "\uE266\uE301\uE27E\uE2EE\uE2FA\uE26C\uE31D\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void narangerelWord() {
        String unicode = "ᠨᠠᠷᠠᠨᠭᠡᠷᠡᠯ"; // NARANGEREL
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE327\uE26C\uE2B8\uE2EB\uE277\uE327\uE276\uE2F9";
        assertEquals(expected, result);
    }

    @Test
    public void cholmonodoWord() {
        String unicode = "ᠴᠣᠯᠮᠣᠨ\u200Dᠣ᠋ᠳᠣ"; // CHOLMON(ZWJ)O(FVS1)DO
        String result = convert(unicode);
        String expected = "\uE315\uE289\uE2FB\uE2F5\uE289\uE2BA\u200D\uE288\uE313\uE285";
        assertEquals(expected, result);
    }

    @Test
    public void cholmonodo2Word() {
        String unicode = "ᠴᠣᠯᠮᠣᠨᠣ᠋ᠳᠣ"; // CHOLMONO(FVS1)DO
        String result = convert(unicode);
        String expected = "\uE315\uE289\uE2FB\uE2F5\uE289\uE2BA\uE288\uE313\uE285";
        assertEquals(expected, result);
    }

    @Test
    public void bayanunderWord() {
        String unicode = "ᠪᠠᠶᠠᠨ\u200Dᠦ᠌ᠨᠳᠦᠷ"; // BAYAN(ZWJ)U(FVS1)NDUR
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE320\uE26C\uE2BA\u200D\uE2A8\uE2B8\uE313\uE2AB\uE325";
        assertEquals(expected, result);
    }

//    @Test
//    public void bayanunder2Word() {
//        String unicode = "ᠪᠠᠶᠠᠨᠦ᠌ᠨᠳᠦᠷ"; // BAYANU(FVS1)NDUR
//        String result = convert(unicode);
//        String expected = "\uE2C1\uE26D\uE320\uE26C\uE2BA\uE2A8\uE2B8\uE313\uE2AB\uE325";
//        assertEquals(expected, result);
//    }

    @Test
    public void sodobiligWord() {
        String unicode = "ᠰᠣᠳᠣᠪᠢᠯᠢᠭ᠌"; // SODOBILIG(FVS2)
        String result = convert(unicode);
        String expected = "\uE2FE\uE289\uE313\uE289\uE2C5\uE27F\uE2FA\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void anggilumchechegWord() {
        String unicode = "ᠠᠩᠭᠢᠯᠤᠮᠴᠡᠴᠡᠭ"; // ANGGILUMCHECHEG
        String result = convert(unicode);
        String expected = "\uE266\uE2BD\uE2EB\uE27F\uE2FB\uE291\uE2F5\uE317\uE276\uE317\uE276\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void chechegmaWord() {
        String unicode = "ᠴᠡᠴᠡᠭᠮ᠎ᠡ"; // CHECHEGM(MVS)A
        String result = convert(unicode);
        String expected = "\uE315\uE276\uE317\uE276\uE2F0\uE2F3\uE274";
        assertEquals(expected, result);
    }


    @Test
    public void sigDefaultWord() {
        String unicode = "ᠰᠢᠭ"; // SIG
        String result = convert(unicode);
        String expected = "\uE2FD\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void sigSpecifiedWord() {
        String unicode = "ᠰᠢᠭ᠋"; // SIG(FVS1)
        String result = convert(unicode);
        String expected = "\uE2FD\uE27E\uE2E7";
        assertEquals(expected, result);
    }


    ///////////////////////// Suffixes ////////////////////////


    @Test
    public void yinSuffix() {
        String unicode = " ᠶᠢᠨ"; //
        String result = convert(unicode);
        String expected = " \uE321\uE27E\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void unSuffix() {
        String unicode = " ᠤᠨ"; //
        String result = convert(unicode);
        String expected = " \uE292\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void uenSuffix() {
        String unicode = " ᠦᠨ"; //
        String result = convert(unicode);
        String expected = " \uE2AC\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void uSuffix() {
        String unicode = " ᠤ"; //
        String result = convert(unicode);
        String expected = " \uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void ueSuffix() {
        String unicode = " ᠦ"; //
        String result = convert(unicode);
        String expected = " \uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void iSuffix() {
        String unicode = " ᠢ"; //
        String result = convert(unicode);
        String expected = " \uE282";
        assertEquals(expected, result);
    }

    @Test
    public void yiSuffix() {
        String unicode = " ᠶᠢ"; //
        String result = convert(unicode);
        String expected = " \uE321\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void duSuffix() {
        String unicode = " ᠳᠤ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void dueSuffix() {
        String unicode = " ᠳᠦ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void tuSuffix() {
        String unicode = " ᠲᠤ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void tueSuffix() {
        String unicode = " ᠲᠦ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void durSuffix() {
        String unicode = " ᠳᠤᠷ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE291\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void duerSuffix() {
        String unicode = " ᠳᠦᠷ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE2AB\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void turSuffix() {
        String unicode = " ᠲᠤᠷ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE291\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void tuerSuffix() {
        String unicode = " ᠲᠦᠷ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE2AB\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void daqiSuffix() {
        String unicode = " ᠳᠠᠬᠢ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE26C\uE2DA\uE27C";
        assertEquals(expected, result);
    }

    @Test
    public void deqiSuffix() {
        String unicode = " ᠳᠡᠬᠢ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE276\uE2DA\uE27C";
        assertEquals(expected, result);
    }

    @Test
    public void achaSuffix() {
        String unicode = " ᠠᠴᠠ"; //
        String result = convert(unicode);
        String expected = " \uE267\uE317\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void echeSuffix() {
        String unicode = " ᠡᠴᠡ"; //
        String result = convert(unicode);
        String expected = " \uE271\uE317\uE273";
        assertEquals(expected, result);
    }

    @Test
    public void barSuffix() {
        String unicode = " ᠪᠠᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE26D\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void berSuffix() {
        String unicode = " ᠪᠡᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE277\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void iyarSuffix() {
        String unicode = " ᠢᠶᠠᠷ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE26C\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void iyerSuffix() {
        String unicode = " ᠢᠶᠡᠷ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE276\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void taiSuffix() {
        String unicode = " ᠲᠠᠢ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE27B";
        assertEquals(expected, result);
    }

    // no longer supporting this
    //@Test
    //public void tayiSuffix() {
    //    String unicode = " ᠲᠠᠶᠢ"; //
    //    String result = convert(unicode);
    //    String expected = " \uE308\uE26C\uE27B";
    //    assertEquals(expected, result);
    //}

    @Test
    public void teiSuffix() {
        String unicode = " ᠲᠡᠢ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE27B";
        assertEquals(expected, result);
    }

    // no longer supporting this
    //@Test
    //public void teyiSuffix() {
    //    String unicode = " ᠲᠡᠶᠢ"; //
    //    String result = convert(unicode);
    //    String expected = " \uE308\uE276\uE27B";
    //    assertEquals(expected, result);
    //}

    @Test
    public void lugaSuffix() {
        String unicode = " ᠯᠤᠭ᠎ᠠ"; //
        String result = convert(unicode);
        String expected = " \uE2F8\uE291\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void luegeSuffix() {
        String unicode = " ᠯᠦᠭᠡ"; //
        String result = convert(unicode);
        String expected = " \uE2F8\uE2AB\uE2EB\uE275";
        assertEquals(expected, result);
    }

    @Test
    public void banSuffix() {
        String unicode = " ᠪᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE26D\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void benSuffix() {
        String unicode = " ᠪᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void iyanSuffix() {
        String unicode = " ᠢᠶᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void iyenSuffix() {
        String unicode = " ᠢᠶᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE276\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void yuganSuffix() {
        String unicode = " ᠶᠤᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE31E\uE291\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void yuegenSuffix() {
        String unicode = " ᠶᠦᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE31E\uE2AB\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void daganSuffix() {
        String unicode = " ᠳᠠᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void degenSuffix() {
        String unicode = " ᠳᠡᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void taganSuffix() {
        String unicode = " ᠲᠠᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void tegenSuffix() {
        String unicode = " ᠲᠡᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void achaganSuffix() {
        String unicode = " ᠠᠴᠠᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE267\uE317\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void echegenSuffix() {
        String unicode = " ᠡᠴᠡᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE271\uE317\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void taiganSuffix() {
        String unicode = " ᠲᠠᠢᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE281\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void tayiganSuffix() {
        String unicode = " ᠲᠠᠶᠢᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE321\uE27E\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void teigenSuffix() {
        String unicode = " ᠲᠡᠢᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE281\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void teyigenSuffix() {
        String unicode = " ᠲᠡᠶᠢᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE321\uE27E\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void udSuffix() {
        String unicode = " ᠤᠳ"; //
        String result = convert(unicode);
        String expected = " \uE292\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void uedSuffix() {
        String unicode = " ᠦᠳ"; //
        String result = convert(unicode);
        String expected = " \uE2AC\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void nugudSuffix() {
        String unicode = " ᠨᠤᠭᠤᠳ"; //
        String result = convert(unicode);
        String expected = " \uE2B3\uE291\uE2EC\uE291\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void nueguedSuffix() {
        String unicode = " ᠨᠦᠭᠦᠳ"; //
        String result = convert(unicode);
        String expected = " \uE2B3\uE2AB\uE2ED\uE2AC\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void narSuffix() {
        String unicode = " ᠨᠠᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2B1\uE26C\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void nerSuffix() {
        String unicode = " ᠨᠡᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2B1\uE276\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void uuSuffix() {
        String unicode = " ᠤᠤ"; //
        String result = convert(unicode);
        String expected = " \uE292\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void ueueSuffix() {
        String unicode = " ᠦᠦ"; //
        String result = convert(unicode);
        String expected = " \uE2AC\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void daSuffix() {
        String unicode = " ᠳᠠ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void deSuffix() {
        String unicode = " ᠳᠡ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE273";
        assertEquals(expected, result);
    }

    // TodoScript tests

    @Test
    public void todoAllTodoCode() {
        String unicode = "ᡐᡆᡑᡆ"; //
        String result = convert(unicode);
        String expected = "ᡐᡆᡑᡆ";
        assertEquals(expected, result);
    }

    @Test
    public void todoMixedTodoMongolCode() {
        String unicode = "ᠰᡇᠷᡋᡇᠯᡓᡅᠯᠠᡋᠠ"; //
        String result = convert(unicode);
        String expected = "ᠰᡇᠷᡋᡇᠯᡓᡅᠯᠠᡋᠠ";
        assertEquals(expected, result);
    }

    @Test
    public void todoZWJ() {
        String unicode = "\u200Dᡐ\u200D"; //
        String result = convert(unicode);
        String expected = "\u200Dᡐ\u200D";
        assertEquals(expected, result);
    }

    @Test
    public void todoFVS() {
        String unicode = "\u1847\u180B";
        String result = convert(unicode);
        String expected = "\u1847\u180B";
        assertEquals(expected, result);
    }

    @Test
    public void todoZwjFvsMix() {
        String unicode = "\u200D\u1847\u180B\u200D";
        String result = convert(unicode);
        String expected = "\u200D\u1847\u180B\u200D";
        assertEquals(expected, result);
    }

}
