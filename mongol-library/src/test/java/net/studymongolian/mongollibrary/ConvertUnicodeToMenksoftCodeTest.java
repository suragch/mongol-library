package net.studymongolian.mongollibrary;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConvertUnicodeToMenksoftCodeTest {

    MongolCode converter = MongolCode.INSTANCE;

    // extracting the method name so that it can change without breaking all of the tests
    private String convert(String unicode) {
        return converter.unicodeToMenksoft(unicode);
    }


    ////////////////// non-Mongol strings ////////////////////


    @Test
    public void emptyString() throws Exception {
        String unicode = "";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nullString() throws Exception {
        String unicode = null;
        String result = convert(unicode);
        String expected = null;
        assertEquals(expected, result);
    }

    @Test
    public void englishKeyboardChars() throws Exception {
        String unicode = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=_+`~;:'\" ,./<>?";
        String result = convert(unicode);
        String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=_+`~;:'\" ,./<>?";
        assertEquals(expected, result);
    }

    @Test
    public void chineseChars() throws Exception {
        String unicode = "中文";
        String result = convert(unicode);
        String expected = "中文";
        assertEquals(expected, result);
    }

    @Test
    public void emojiChars() throws Exception {
        String unicode = "\uD83D\uDE04\uD83D\uDD90\uD83C\uDFFC\uD83C\uDDF3\uD83C\uDDF4";
        String result = convert(unicode);
        String expected = "\uD83D\uDE04\uD83D\uDD90\uD83C\uDFFC\uD83C\uDDF3\uD83C\uDDF4";
        assertEquals(expected, result);
    }

    @Test
    public void menksoftString() throws Exception {
        String unicode = "\uE2F2\uE291\uE2BC\uE2EC\uE291\uE2F9";
        String result = convert(unicode);
        String expected = "\uE2F2\uE291\uE2BC\uE2EC\uE291\uE2F9";
        assertEquals(expected, result);
    }

    // nirugu

    @Test
    public void niruguChar() throws Exception {
        String unicode = "\u180A";
        String result = convert(unicode);
        String expected = "\uE23E";
        assertEquals(expected, result);
    }

    // tests for all combinations of Unicode+FVS (even undefined ones)

    @Test
    public void aIsol() throws Exception {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInit() throws Exception {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aMedi() throws Exception {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aFina() throws Exception {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aIsolFvs1() throws Exception {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInitFvs1() throws Exception {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aMediFvs1() throws Exception {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aFinaFvs1() throws Exception {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aIsolFvs2() throws Exception {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInitFvs2() throws Exception {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aMediFvs2() throws Exception {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aFinaFvs2() throws Exception {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aIsolFvs3() throws Exception {
        String unicode = "ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aInitFvs3() throws Exception {
        String unicode = "ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aMediFvs3() throws Exception {
        String unicode = "‍ᠠ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void aFinaFvs3() throws Exception {
        String unicode = "‍ᠠ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsol() throws Exception {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInit() throws Exception {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eMedi() throws Exception {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eFina() throws Exception {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsolFvs1() throws Exception {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInitFvs1() throws Exception {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eMediFvs1() throws Exception {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eFinaFvs1() throws Exception {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsolFvs2() throws Exception {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInitFvs2() throws Exception {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eMediFvs2() throws Exception {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eFinaFvs2() throws Exception {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eIsolFvs3() throws Exception {
        String unicode = "ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eInitFvs3() throws Exception {
        String unicode = "ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eMediFvs3() throws Exception {
        String unicode = "‍ᠡ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eFinaFvs3() throws Exception {
        String unicode = "‍ᠡ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsol() throws Exception {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInit() throws Exception {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iMedi() throws Exception {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iFina() throws Exception {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsolFvs1() throws Exception {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInitFvs1() throws Exception {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iMediFvs1() throws Exception {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iFinaFvs1() throws Exception {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsolFvs2() throws Exception {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInitFvs2() throws Exception {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iMediFvs2() throws Exception {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iFinaFvs2() throws Exception {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iIsolFvs3() throws Exception {
        String unicode = "ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iInitFvs3() throws Exception {
        String unicode = "ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iMediFvs3() throws Exception {
        String unicode = "‍ᠢ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void iFinaFvs3() throws Exception {
        String unicode = "‍ᠢ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsol() throws Exception {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInit() throws Exception {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oMedi() throws Exception {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oFina() throws Exception {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsolFvs1() throws Exception {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInitFvs1() throws Exception {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oMediFvs1() throws Exception {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oFinaFvs1() throws Exception {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsolFvs2() throws Exception {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInitFvs2() throws Exception {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oMediFvs2() throws Exception {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oFinaFvs2() throws Exception {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oIsolFvs3() throws Exception {
        String unicode = "ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oInitFvs3() throws Exception {
        String unicode = "ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oMediFvs3() throws Exception {
        String unicode = "‍ᠣ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oFinaFvs3() throws Exception {
        String unicode = "‍ᠣ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsol() throws Exception {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInit() throws Exception {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uMedi() throws Exception {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uFina() throws Exception {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsolFvs1() throws Exception {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInitFvs1() throws Exception {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uMediFvs1() throws Exception {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uFinaFvs1() throws Exception {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsolFvs2() throws Exception {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInitFvs2() throws Exception {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uMediFvs2() throws Exception {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uFinaFvs2() throws Exception {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uIsolFvs3() throws Exception {
        String unicode = "ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uInitFvs3() throws Exception {
        String unicode = "ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uMediFvs3() throws Exception {
        String unicode = "‍ᠤ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void uFinaFvs3() throws Exception {
        String unicode = "‍ᠤ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsol() throws Exception {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInit() throws Exception {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeMedi() throws Exception {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeFina() throws Exception {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsolFvs1() throws Exception {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInitFvs1() throws Exception {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeMediFvs1() throws Exception {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeFinaFvs1() throws Exception {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsolFvs2() throws Exception {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInitFvs2() throws Exception {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeMediFvs2() throws Exception {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeFinaFvs2() throws Exception {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeIsolFvs3() throws Exception {
        String unicode = "ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeInitFvs3() throws Exception {
        String unicode = "ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeMediFvs3() throws Exception {
        String unicode = "‍ᠥ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oeFinaFvs3() throws Exception {
        String unicode = "‍ᠥ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsol() throws Exception {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInit() throws Exception {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueMedi() throws Exception {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueFina() throws Exception {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsolFvs1() throws Exception {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInitFvs1() throws Exception {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueMediFvs1() throws Exception {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueFinaFvs1() throws Exception {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsolFvs2() throws Exception {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInitFvs2() throws Exception {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueMediFvs2() throws Exception {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueFinaFvs2() throws Exception {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueIsolFvs3() throws Exception {
        String unicode = "ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueInitFvs3() throws Exception {
        String unicode = "ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueMediFvs3() throws Exception {
        String unicode = "‍ᠦ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ueFinaFvs3() throws Exception {
        String unicode = "‍ᠦ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsol() throws Exception {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInit() throws Exception {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeMedi() throws Exception {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeFina() throws Exception {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsolFvs1() throws Exception {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInitFvs1() throws Exception {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeMediFvs1() throws Exception {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeFinaFvs1() throws Exception {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsolFvs2() throws Exception {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInitFvs2() throws Exception {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeMediFvs2() throws Exception {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeFinaFvs2() throws Exception {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeIsolFvs3() throws Exception {
        String unicode = "ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeInitFvs3() throws Exception {
        String unicode = "ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeMediFvs3() throws Exception {
        String unicode = "‍ᠧ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void eeFinaFvs3() throws Exception {
        String unicode = "‍ᠧ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsol() throws Exception {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInit() throws Exception {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nMedi() throws Exception {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nFina() throws Exception {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsolFvs1() throws Exception {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInitFvs1() throws Exception {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nMediFvs1() throws Exception {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nFinaFvs1() throws Exception {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsolFvs2() throws Exception {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInitFvs2() throws Exception {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nMediFvs2() throws Exception {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nFinaFvs2() throws Exception {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nIsolFvs3() throws Exception {
        String unicode = "ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nInitFvs3() throws Exception {
        String unicode = "ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nMediFvs3() throws Exception {
        String unicode = "‍ᠨ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nFinaFvs3() throws Exception {
        String unicode = "‍ᠨ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsol() throws Exception {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInit() throws Exception {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngMedi() throws Exception {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngFina() throws Exception {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsolFvs1() throws Exception {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInitFvs1() throws Exception {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngMediFvs1() throws Exception {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngFinaFvs1() throws Exception {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsolFvs2() throws Exception {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInitFvs2() throws Exception {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngMediFvs2() throws Exception {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngFinaFvs2() throws Exception {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngIsolFvs3() throws Exception {
        String unicode = "ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngInitFvs3() throws Exception {
        String unicode = "ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngMediFvs3() throws Exception {
        String unicode = "‍ᠩ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void ngFinaFvs3() throws Exception {
        String unicode = "‍ᠩ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsol() throws Exception {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInit() throws Exception {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bMedi() throws Exception {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bFina() throws Exception {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsolFvs1() throws Exception {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInitFvs1() throws Exception {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bMediFvs1() throws Exception {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bFinaFvs1() throws Exception {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsolFvs2() throws Exception {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInitFvs2() throws Exception {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bMediFvs2() throws Exception {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bFinaFvs2() throws Exception {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bIsolFvs3() throws Exception {
        String unicode = "ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bInitFvs3() throws Exception {
        String unicode = "ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bMediFvs3() throws Exception {
        String unicode = "‍ᠪ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void bFinaFvs3() throws Exception {
        String unicode = "‍ᠪ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsol() throws Exception {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInit() throws Exception {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pMedi() throws Exception {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pFina() throws Exception {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsolFvs1() throws Exception {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInitFvs1() throws Exception {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pMediFvs1() throws Exception {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pFinaFvs1() throws Exception {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsolFvs2() throws Exception {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInitFvs2() throws Exception {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pMediFvs2() throws Exception {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pFinaFvs2() throws Exception {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pIsolFvs3() throws Exception {
        String unicode = "ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pInitFvs3() throws Exception {
        String unicode = "ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pMediFvs3() throws Exception {
        String unicode = "‍ᠫ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void pFinaFvs3() throws Exception {
        String unicode = "‍ᠫ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsol() throws Exception {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInit() throws Exception {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qMedi() throws Exception {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qFina() throws Exception {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsolFvs1() throws Exception {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInitFvs1() throws Exception {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qMediFvs1() throws Exception {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qFinaFvs1() throws Exception {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsolFvs2() throws Exception {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInitFvs2() throws Exception {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qMediFvs2() throws Exception {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qFinaFvs2() throws Exception {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qIsolFvs3() throws Exception {
        String unicode = "ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qInitFvs3() throws Exception {
        String unicode = "ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qMediFvs3() throws Exception {
        String unicode = "‍ᠬ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void qFinaFvs3() throws Exception {
        String unicode = "‍ᠬ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsol() throws Exception {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInit() throws Exception {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gMedi() throws Exception {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gFina() throws Exception {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsolFvs1() throws Exception {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInitFvs1() throws Exception {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gMediFvs1() throws Exception {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gFinaFvs1() throws Exception {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsolFvs2() throws Exception {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInitFvs2() throws Exception {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gMediFvs2() throws Exception {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gFinaFvs2() throws Exception {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gIsolFvs3() throws Exception {
        String unicode = "ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gInitFvs3() throws Exception {
        String unicode = "ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gMediFvs3() throws Exception {
        String unicode = "‍ᠭ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void gFinaFvs3() throws Exception {
        String unicode = "‍ᠭ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsol() throws Exception {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInit() throws Exception {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mMedi() throws Exception {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mFina() throws Exception {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsolFvs1() throws Exception {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInitFvs1() throws Exception {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mMediFvs1() throws Exception {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mFinaFvs1() throws Exception {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsolFvs2() throws Exception {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInitFvs2() throws Exception {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mMediFvs2() throws Exception {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mFinaFvs2() throws Exception {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mIsolFvs3() throws Exception {
        String unicode = "ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mInitFvs3() throws Exception {
        String unicode = "ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mMediFvs3() throws Exception {
        String unicode = "‍ᠮ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void mFinaFvs3() throws Exception {
        String unicode = "‍ᠮ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsol() throws Exception {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInit() throws Exception {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lMedi() throws Exception {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lFina() throws Exception {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsolFvs1() throws Exception {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInitFvs1() throws Exception {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lMediFvs1() throws Exception {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lFinaFvs1() throws Exception {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsolFvs2() throws Exception {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInitFvs2() throws Exception {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lMediFvs2() throws Exception {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lFinaFvs2() throws Exception {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lIsolFvs3() throws Exception {
        String unicode = "ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lInitFvs3() throws Exception {
        String unicode = "ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lMediFvs3() throws Exception {
        String unicode = "‍ᠯ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lFinaFvs3() throws Exception {
        String unicode = "‍ᠯ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsol() throws Exception {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInit() throws Exception {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sMedi() throws Exception {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sFina() throws Exception {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsolFvs1() throws Exception {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInitFvs1() throws Exception {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sMediFvs1() throws Exception {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sFinaFvs1() throws Exception {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsolFvs2() throws Exception {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInitFvs2() throws Exception {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sMediFvs2() throws Exception {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sFinaFvs2() throws Exception {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sIsolFvs3() throws Exception {
        String unicode = "ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sInitFvs3() throws Exception {
        String unicode = "ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sMediFvs3() throws Exception {
        String unicode = "‍ᠰ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void sFinaFvs3() throws Exception {
        String unicode = "‍ᠰ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsol() throws Exception {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInit() throws Exception {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shMedi() throws Exception {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shFina() throws Exception {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsolFvs1() throws Exception {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInitFvs1() throws Exception {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shMediFvs1() throws Exception {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shFinaFvs1() throws Exception {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsolFvs2() throws Exception {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInitFvs2() throws Exception {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shMediFvs2() throws Exception {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shFinaFvs2() throws Exception {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shIsolFvs3() throws Exception {
        String unicode = "ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shInitFvs3() throws Exception {
        String unicode = "ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shMediFvs3() throws Exception {
        String unicode = "‍ᠱ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void shFinaFvs3() throws Exception {
        String unicode = "‍ᠱ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsol() throws Exception {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInit() throws Exception {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tMedi() throws Exception {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tFina() throws Exception {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsolFvs1() throws Exception {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInitFvs1() throws Exception {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tMediFvs1() throws Exception {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tFinaFvs1() throws Exception {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsolFvs2() throws Exception {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInitFvs2() throws Exception {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tMediFvs2() throws Exception {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tFinaFvs2() throws Exception {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tIsolFvs3() throws Exception {
        String unicode = "ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tInitFvs3() throws Exception {
        String unicode = "ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tMediFvs3() throws Exception {
        String unicode = "‍ᠲ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tFinaFvs3() throws Exception {
        String unicode = "‍ᠲ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsol() throws Exception {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInit() throws Exception {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dMedi() throws Exception {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dFina() throws Exception {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsolFvs1() throws Exception {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInitFvs1() throws Exception {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dMediFvs1() throws Exception {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dFinaFvs1() throws Exception {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsolFvs2() throws Exception {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInitFvs2() throws Exception {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dMediFvs2() throws Exception {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dFinaFvs2() throws Exception {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dIsolFvs3() throws Exception {
        String unicode = "ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dInitFvs3() throws Exception {
        String unicode = "ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dMediFvs3() throws Exception {
        String unicode = "‍ᠳ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void dFinaFvs3() throws Exception {
        String unicode = "‍ᠳ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsol() throws Exception {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInit() throws Exception {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chMedi() throws Exception {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chFina() throws Exception {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsolFvs1() throws Exception {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInitFvs1() throws Exception {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chMediFvs1() throws Exception {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chFinaFvs1() throws Exception {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsolFvs2() throws Exception {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInitFvs2() throws Exception {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chMediFvs2() throws Exception {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chFinaFvs2() throws Exception {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chIsolFvs3() throws Exception {
        String unicode = "ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chInitFvs3() throws Exception {
        String unicode = "ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chMediFvs3() throws Exception {
        String unicode = "‍ᠴ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chFinaFvs3() throws Exception {
        String unicode = "‍ᠴ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsol() throws Exception {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInit() throws Exception {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jMedi() throws Exception {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jFina() throws Exception {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsolFvs1() throws Exception {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInitFvs1() throws Exception {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jMediFvs1() throws Exception {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jFinaFvs1() throws Exception {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsolFvs2() throws Exception {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInitFvs2() throws Exception {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jMediFvs2() throws Exception {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jFinaFvs2() throws Exception {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jIsolFvs3() throws Exception {
        String unicode = "ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jInitFvs3() throws Exception {
        String unicode = "ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jMediFvs3() throws Exception {
        String unicode = "‍ᠵ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void jFinaFvs3() throws Exception {
        String unicode = "‍ᠵ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsol() throws Exception {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInit() throws Exception {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yMedi() throws Exception {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yFina() throws Exception {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsolFvs1() throws Exception {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInitFvs1() throws Exception {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yMediFvs1() throws Exception {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yFinaFvs1() throws Exception {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsolFvs2() throws Exception {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInitFvs2() throws Exception {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yMediFvs2() throws Exception {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yFinaFvs2() throws Exception {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yIsolFvs3() throws Exception {
        String unicode = "ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yInitFvs3() throws Exception {
        String unicode = "ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yMediFvs3() throws Exception {
        String unicode = "‍ᠶ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void yFinaFvs3() throws Exception {
        String unicode = "‍ᠶ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsol() throws Exception {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInit() throws Exception {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rMedi() throws Exception {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rFina() throws Exception {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsolFvs1() throws Exception {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInitFvs1() throws Exception {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rMediFvs1() throws Exception {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rFinaFvs1() throws Exception {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsolFvs2() throws Exception {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInitFvs2() throws Exception {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rMediFvs2() throws Exception {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rFinaFvs2() throws Exception {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rIsolFvs3() throws Exception {
        String unicode = "ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rInitFvs3() throws Exception {
        String unicode = "ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rMediFvs3() throws Exception {
        String unicode = "‍ᠷ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void rFinaFvs3() throws Exception {
        String unicode = "‍ᠷ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsol() throws Exception {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInit() throws Exception {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wMedi() throws Exception {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wFina() throws Exception {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsolFvs1() throws Exception {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInitFvs1() throws Exception {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wMediFvs1() throws Exception {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wFinaFvs1() throws Exception {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsolFvs2() throws Exception {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInitFvs2() throws Exception {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wMediFvs2() throws Exception {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wFinaFvs2() throws Exception {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wIsolFvs3() throws Exception {
        String unicode = "ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wInitFvs3() throws Exception {
        String unicode = "ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wMediFvs3() throws Exception {
        String unicode = "‍ᠸ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void wFinaFvs3() throws Exception {
        String unicode = "‍ᠸ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsol() throws Exception {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInit() throws Exception {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fMedi() throws Exception {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fFina() throws Exception {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsolFvs1() throws Exception {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInitFvs1() throws Exception {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fMediFvs1() throws Exception {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fFinaFvs1() throws Exception {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsolFvs2() throws Exception {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInitFvs2() throws Exception {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fMediFvs2() throws Exception {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fFinaFvs2() throws Exception {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fIsolFvs3() throws Exception {
        String unicode = "ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fInitFvs3() throws Exception {
        String unicode = "ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fMediFvs3() throws Exception {
        String unicode = "‍ᠹ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void fFinaFvs3() throws Exception {
        String unicode = "‍ᠹ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsol() throws Exception {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInit() throws Exception {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kMedi() throws Exception {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kFina() throws Exception {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsolFvs1() throws Exception {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInitFvs1() throws Exception {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kMediFvs1() throws Exception {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kFinaFvs1() throws Exception {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsolFvs2() throws Exception {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInitFvs2() throws Exception {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kMediFvs2() throws Exception {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kFinaFvs2() throws Exception {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kIsolFvs3() throws Exception {
        String unicode = "ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kInitFvs3() throws Exception {
        String unicode = "ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kMediFvs3() throws Exception {
        String unicode = "‍ᠺ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void kFinaFvs3() throws Exception {
        String unicode = "‍ᠺ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsol() throws Exception {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInit() throws Exception {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khMedi() throws Exception {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khFina() throws Exception {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsolFvs1() throws Exception {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInitFvs1() throws Exception {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khMediFvs1() throws Exception {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khFinaFvs1() throws Exception {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsolFvs2() throws Exception {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInitFvs2() throws Exception {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khMediFvs2() throws Exception {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khFinaFvs2() throws Exception {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khIsolFvs3() throws Exception {
        String unicode = "ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khInitFvs3() throws Exception {
        String unicode = "ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khMediFvs3() throws Exception {
        String unicode = "‍ᠻ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void khFinaFvs3() throws Exception {
        String unicode = "‍ᠻ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsol() throws Exception {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInit() throws Exception {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsMedi() throws Exception {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsFina() throws Exception {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsolFvs1() throws Exception {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInitFvs1() throws Exception {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsMediFvs1() throws Exception {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsFinaFvs1() throws Exception {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsolFvs2() throws Exception {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInitFvs2() throws Exception {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsMediFvs2() throws Exception {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsFinaFvs2() throws Exception {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsIsolFvs3() throws Exception {
        String unicode = "ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsInitFvs3() throws Exception {
        String unicode = "ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsMediFvs3() throws Exception {
        String unicode = "‍ᠼ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void tsFinaFvs3() throws Exception {
        String unicode = "‍ᠼ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsol() throws Exception {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInit() throws Exception {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zMedi() throws Exception {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zFina() throws Exception {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsolFvs1() throws Exception {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInitFvs1() throws Exception {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zMediFvs1() throws Exception {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zFinaFvs1() throws Exception {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsolFvs2() throws Exception {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInitFvs2() throws Exception {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zMediFvs2() throws Exception {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zFinaFvs2() throws Exception {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zIsolFvs3() throws Exception {
        String unicode = "ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zInitFvs3() throws Exception {
        String unicode = "ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zMediFvs3() throws Exception {
        String unicode = "‍ᠽ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zFinaFvs3() throws Exception {
        String unicode = "‍ᠽ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsol() throws Exception {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInit() throws Exception {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaMedi() throws Exception {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaFina() throws Exception {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsolFvs1() throws Exception {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInitFvs1() throws Exception {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaMediFvs1() throws Exception {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaFinaFvs1() throws Exception {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsolFvs2() throws Exception {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInitFvs2() throws Exception {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaMediFvs2() throws Exception {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaFinaFvs2() throws Exception {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaIsolFvs3() throws Exception {
        String unicode = "ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaInitFvs3() throws Exception {
        String unicode = "ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaMediFvs3() throws Exception {
        String unicode = "‍ᠾ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void haaFinaFvs3() throws Exception {
        String unicode = "‍ᠾ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsol() throws Exception {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInit() throws Exception {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrMedi() throws Exception {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrFina() throws Exception {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsolFvs1() throws Exception {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInitFvs1() throws Exception {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrMediFvs1() throws Exception {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrFinaFvs1() throws Exception {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsolFvs2() throws Exception {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInitFvs2() throws Exception {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrMediFvs2() throws Exception {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrFinaFvs2() throws Exception {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrIsolFvs3() throws Exception {
        String unicode = "ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrInitFvs3() throws Exception {
        String unicode = "ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrMediFvs3() throws Exception {
        String unicode = "‍ᠿ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zrFinaFvs3() throws Exception {
        String unicode = "‍ᠿ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsol() throws Exception {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInit() throws Exception {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhMedi() throws Exception {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhFina() throws Exception {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsolFvs1() throws Exception {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInitFvs1() throws Exception {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhMediFvs1() throws Exception {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhFinaFvs1() throws Exception {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsolFvs2() throws Exception {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInitFvs2() throws Exception {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhMediFvs2() throws Exception {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhFinaFvs2() throws Exception {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhIsolFvs3() throws Exception {
        String unicode = "ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhInitFvs3() throws Exception {
        String unicode = "ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhMediFvs3() throws Exception {
        String unicode = "‍ᡀ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void lhFinaFvs3() throws Exception {
        String unicode = "‍ᡀ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsol() throws Exception {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInit() throws Exception {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiMedi() throws Exception {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFina() throws Exception {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsolFvs1() throws Exception {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInitFvs1() throws Exception {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiMediFvs1() throws Exception {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFinaFvs1() throws Exception {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsolFvs2() throws Exception {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInitFvs2() throws Exception {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiMediFvs2() throws Exception {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFinaFvs2() throws Exception {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiIsolFvs3() throws Exception {
        String unicode = "ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiInitFvs3() throws Exception {
        String unicode = "ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiMediFvs3() throws Exception {
        String unicode = "‍ᡁ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void zhiFinaFvs3() throws Exception {
        String unicode = "‍ᡁ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsol() throws Exception {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInit() throws Exception {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiMedi() throws Exception {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiFina() throws Exception {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsolFvs1() throws Exception {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInitFvs1() throws Exception {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiMediFvs1() throws Exception {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiFinaFvs1() throws Exception {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsolFvs2() throws Exception {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInitFvs2() throws Exception {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiMediFvs2() throws Exception {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiFinaFvs2() throws Exception {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiIsolFvs3() throws Exception {
        String unicode = "ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiInitFvs3() throws Exception {
        String unicode = "ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiMediFvs3() throws Exception {
        String unicode = "‍ᡂ‍";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void chiFinaFvs3() throws Exception {
        String unicode = "‍ᡂ";
        String result = convert(unicode);
        String expected = "";
        assertEquals(expected, result);
    }







    ///////////////////////// Words ////////////////////////////





    @Test
    public void bichigWord() throws Exception {
        String unicode = "ᠪᠢᠴᠢᠭ"; // bichig
        String result = convert(unicode);
        String expected = "\uE2C1\uE27F\uE317\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void egeshigWord() throws Exception {
        String unicode = "ᠡᠭᠡᠰᠢᠭ ᠢᠨᠦ"; // EGESIG (NNBSP) INU
        String result = convert(unicode);
        String expected = "\uE271\uE2EB\uE277\uE301\uE27E\uE2E8 \uE27A\uE2B9\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void bujigWord() throws Exception {
        String unicode = "ᠪᠦᠵᠢᠭ ᠢ ᠪᠡᠨ ᠶᠦᠭᠡᠨ"; // BUJIG (NNBSP) I (NNBSP) BEN (NNBSP) YUGEN
        String result = convert(unicode);
        String expected = "\uE2C2\uE2AA\uE31D\uE27E\uE2E8 \uE282 \uE2C1\uE277\uE2B5 \uE31E\uE2AB\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void chirigWord() throws Exception {
        String unicode = "ᠴᠢᠷᠢᠭ ᠮᠠᠨᠢ"; // CHIRIG (NNBSP) MANI
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE327\uE27E\uE2E8 \uE2F1\uE26C\uE2B7\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void egchiWord() throws Exception {
        String unicode = "ᠡᠭᠴᠡ"; // EGCHI
        String result = convert(unicode);
        String expected = "\uE271\uE2F0\uE317\uE273";
        assertEquals(expected, result);
    }

    @Test
    public void qugjimWord() throws Exception {
        String unicode = "ᠬᠦᠭᠵᠢᠮ ᠳᠦᠷ ᠢᠶᠡᠨ ᠳᠡᠭᠡᠨ"; // QUGJIM (NNBSP) DUR (NNBSP) IYEN (NNBSP) DEGEN
        String result = convert(unicode);
        String expected = "\uE2D4\uE2AA\uE2F0\uE31D\uE27E\uE2F3 \uE310\uE2AB\uE325 \uE280\uE321\uE276\uE2B5 \uE310\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void buridgelWord() throws Exception {
        String unicode = "ᠪᠦᠷᠢᠳᠭᠡᠯ ᠢᠶᠡᠨ"; // BURIDGEL (NNBSP) IYEN
        String result = convert(unicode);
        String expected = "\uE2C2\uE2AA\uE327\uE27E\uE314\uE2EB\uE277\uE2F9 \uE280\uE321\uE276\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void sedqilWord() throws Exception {
        String unicode = "ᠰᠡᠳᠬᠢᠯ ᠮᠢᠨᠢ"; // SEDQIL (NNBSP) MINI
        String result = convert(unicode);
        String expected = "\uE2FD\uE276\uE314\uE2DA\uE27F\uE2F9 \uE2F1\uE27E\uE2B7\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void uiledburiWord() throws Exception {
        String unicode = "ᠦᠢᠯᠡᠳᠪᠦᠷᠢ ᠳᠦ"; // UILEDBURI (NNBSP) DU
        String result = convert(unicode);
        String expected = "\uE2A2\uE27E\uE2FA\uE276\uE314\uE2C6\uE2AC\uE327\uE27B \uE310\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void jeligudqenWord() throws Exception {
        String unicode = "ᠵᠡᠯᠢᠭᠦᠳᠬᠡᠨ ᠦ"; // JELIGUDQEN (NNBSP) U
        String result = convert(unicode);
        String expected = "\uE31A\uE276\uE2FA\uE27E\uE2ED\uE2AC\uE314\uE2DA\uE277\uE2B5 \uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void manggalWord() throws Exception {
        String unicode = "ᠮᠠᠩᠭᠠᠯ ᠳᠤᠷ ᠢᠶᠠᠨ ᠳᠠᠭᠠᠨ"; // MANGGAL (NNBSP) DUR (NNBSP) IYAN (NNBSP) DAGAN
        String result = convert(unicode);
        String expected = "\uE2F1\uE26C\uE2BC\uE2EA\uE26C\uE2F9 \uE310\uE291\uE325 \uE280\uE321\uE26C\uE2B5 \uE310\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void dengWord() throws Exception {
        String unicode = "ᠳ᠋ᠦᠩ ᠢ"; // D(FVS1)UNG (NNBSP) I
        String result = convert(unicode);
        String expected = "\uE310\uE2A9\uE2BB \uE282";
        assertEquals(expected, result);
    }

    @Test
    public void sodnamWord() throws Exception {
        String unicode = "ᠰᠣᠳᠨᠠᠮ ᠠᠴᠠ ᠪᠠᠨ ᠠᠴᠠᠭᠠᠨ"; // SODNAM (NNBSP) ACHA (NNBSP) BAN (NNBSP) ACHAGAN
        String result = convert(unicode);
        String expected = "\uE2FE\uE289\uE314\uE2B7\uE26C\uE2F3 \uE267\uE317\uE268 \uE2C1\uE26D\uE2B5 \uE267\uE317\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void lhagbaWord() throws Exception {
        String unicode = "ᡀᠠᠭᠪᠠ ᠯᠤᠭ᠎ᠠ"; // LHAGBA (NNBSP) LUG(MVS)A
        String result = convert(unicode);
        String expected = "\uE34B\uE26C\uE2EE\uE2C5\uE26B \uE2F8\uE291\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void chebegmedWord() throws Exception {
        String unicode = "ᠴᠡᠪᠡᠭᠮᠡᠳ ᠯᠦᠭᠡ"; // CHEBEGMED (NNBSP) LUGE
        String result = convert(unicode);
        String expected = "\uE315\uE276\uE2C5\uE277\uE2EB\uE2F6\uE276\uE311 \uE2F8\uE2AB\uE2EB\uE275";
        assertEquals(expected, result);
    }

    @Test
    public void tsementWord() throws Exception {
        String unicode = "ᠼᠧᠮᠧᠨᠲ ᠲᠠᠶᠢᠭᠠᠨ"; // TSEMENT (NNBSP) TAYIGAN
        String result = convert(unicode);
        String expected = "\uE33F\uE2B0\uE2F4\uE2B0\uE2BA\uE30A \uE308\uE26C\uE321\uE27E\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void uniyeWord() throws Exception {
        String unicode = "ᠦᠨᠢᠶ᠎ᠡ ᠲᠡᠶᠢᠭᠡᠨ"; // UNIY(MVS)E (NNBSP) TEYIGEN
        String result = convert(unicode);
        String expected = "\uE2A2\uE2B7\uE27E\uE31F\uE274 \uE308\uE276\uE321\uE27E\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void qoyinaWord() throws Exception {
        String unicode = "ᠬᠣᠶᠢᠨ᠎ᠠ"; // QOYIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2D2\uE289\uE321\uE27E\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void angnaWord() throws Exception {
        String unicode = "ᠠᠩᠨ᠎ᠠ"; // ANGN(MVS)A
        String result = convert(unicode);
        String expected = "\uE266\uE2BE\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void chinggaWord() throws Exception {
        String unicode = "ᠴᠢᠩᠭ᠎ᠠ"; // CHINGG(MVS)A
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE2BC\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void chingalaquWord() throws Exception {
        String unicode = "ᠴᠢᠩᠭᠠᠯᠠᠬᠤ"; // CHINGGALAQU
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE2BC\uE2EA\uE26C\uE2FA\uE26C\uE2DC\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void daljiygsanWord() throws Exception {
        String unicode = "ᠳᠠᠯᠵᠢᠶᠭᠰᠠᠨ"; // DALJIYGSAN
        String result = convert(unicode);
        String expected = "\uE30E\uE26C\uE2FB\uE31D\uE27E\uE321\uE2EE\uE301\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void ilbigchiWord() throws Exception {
        String unicode = "ᠢᠯᠪᠢᠭᠴᠢ"; // ILBIGCHI
        String result = convert(unicode);
        String expected = "\uE27A\uE2FB\uE2C5\uE27F\uE2F0\uE317\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void bichigchiWord() throws Exception {
        String unicode = "ᠪᠢᠴᠢᠭᠴᠢ"; // BICHIGCHI
        String result = convert(unicode);
        String expected = "\uE2C1\uE27F\uE317\uE27E\uE2F0\uE317\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void sigsiglequWord() throws Exception {
        String unicode = "ᠰᠢᠭᠰᠢᠭᠯᠡᠬᠦ"; // SIGSIGLEQU
        String result = convert(unicode);
        String expected = "\uE2FD\uE27E\uE2EB\uE301\uE27E\uE2EB\uE2FC\uE276\uE2DD\uE2A7";
        assertEquals(expected, result);
    }

    @Test
    public void diglimsigsenWord() throws Exception {
        String unicode = "ᠳᠢᠭᠯᠢᠮᠰᠢᠭᠰᠡᠨ"; // DIGLIMSIGSEN
        String result = convert(unicode);
        String expected = "\uE30E\uE27E\uE2EB\uE2FC\uE27E\uE2F4\uE301\uE27E\uE2EB\uE301\uE276\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void chigigligWord() throws Exception {
        String unicode = "ᠴᠢᠭᠢᠭᠯᠢᠭ"; // CHIGIGLIG
        String result = convert(unicode);
        String expected = "\uE315\uE27E\uE2EB\uE27F\uE2EB\uE2FC\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void monggeWord() throws Exception {
        String unicode = "ᠮᠥᠩᠭᠡ"; // MONGGE
        String result = convert(unicode);
        String expected = "\uE2F2\uE29C\uE2BD\uE2EB\uE275";
        assertEquals(expected, result);
    }

    @Test
    public void kinoWord() throws Exception {
        String unicode = "ᠺᠢᠨᠣ᠋"; // KINO(FVS1)
        String result = convert(unicode);
        String expected = "\uE333\uE27F\uE2BA\uE286";
        assertEquals(expected, result);
    }

    @Test
    public void bayigulquWord() throws Exception {
        String unicode = "ᠪᠠᠶᠢᠭᠤᠯᠬᠤ"; // BAYIGULQU
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE321\uE27E\uE2EC\uE291\uE2FA\uE2DC\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void gregWord() throws Exception {
        String unicode = "ᠭᠷᠧᠭ"; // GREG
        String result = convert(unicode);
        String expected = "\uE2E3\uE327\uE2B0\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void motorWord() throws Exception {
        String unicode = "ᠮᠣᠲ᠋ᠣᠷ"; // MOT(FVS1)OR
        String result = convert(unicode);
        String expected = "\uE2F2\uE289\uE30D\uE289\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void neigemligWord() throws Exception {
        String unicode = "ᠨᠡᠶᠢᠭᠡᠮᠯᠢᠭ"; // NEYIGEMLIG
        String result = convert(unicode);
        String expected = "\uE2B1\uE276\uE321\uE27E\uE2EB\uE277\uE2F5\uE2FA\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void mongolWord() throws Exception {
        String unicode = "ᠮᠣᠩᠭᠣᠯ"; // MONGGOL
        String result = convert(unicode);
        String expected = "\uE2F2\uE289\uE2BC\uE2EC\uE289\uE2F9";
        assertEquals(expected, result);
    }

    @Test
    public void yatugaWord() throws Exception {
        String unicode = "ᠶᠠᠲᠤᠭ᠎ᠠ"; // YATUG(MVS)A
        String result = convert(unicode);
        String expected = "\uE31E\uE26C\uE30B\uE291\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void olaganaWord() throws Exception {
        String unicode = "ᠤᠯᠠᠭᠠᠨ᠎ᠠ"; // OLAGAN(MVS)A
        String result = convert(unicode);
        String expected = "\uE28C\uE2FA\uE26C\uE2EA\uE26C\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void bichiglequWord() throws Exception {
        String unicode = "ᠪᠢᠴᠢᠭᠯᠡᠬᠦ"; // BICHIGLEQU
        String result = convert(unicode);
        String expected = "\uE2C1\uE27F\uE317\uE27E\uE2EB\uE2FC\uE276\uE2DD\uE2A7";
        assertEquals(expected, result);
    }

    @Test
    public void programWord() throws Exception {
        String unicode = "ᠫᠷᠣᠭ᠍ᠷᠠᠮ"; // PROG(FVS3)RAM
        String result = convert(unicode);
        String expected = "\uE2C8\uE326\uE289\uE2EF\uE327\uE26C\uE2F3";
        assertEquals(expected, result);
    }

    @Test
    public void kartWord() throws Exception {
        String unicode = "ᠺᠠᠷᠲ"; // KART
        String result = convert(unicode);
        String expected = "\uE333\uE26D\uE326\uE30A";
        assertEquals(expected, result);
    }

    @Test
    public void dungnelteWord() throws Exception {
        String unicode = "ᠳ᠋ᠦᠩᠨᠡᠯᠲᠡ"; // D(FVS1)UNGNELTE
        String result = convert(unicode);
        String expected = "\uE310\uE2A9\uE2BC\uE2B7\uE276\uE2FA\uE30B\uE273";
        assertEquals(expected, result);
    }

    @Test
    public void gramWord() throws Exception {
        String unicode = "ᠭᠷᠠᠮ"; // GRAM
        String result = convert(unicode);
        String expected = "\uE2E3\uE327\uE26C\uE2F3";
        assertEquals(expected, result);
    }

    @Test
    public void minggaWord() throws Exception {
        String unicode = "ᠮᠢᠩᠭ᠎ᠠ"; // MINGG(MVS)A
        String result = convert(unicode);
        String expected = "\uE2F1\uE27E\uE2BC\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void mingganWord() throws Exception {
        String unicode = "ᠮᠢᠩᠭ᠎ᠠᠨ"; // MINGG(MVS)AN
        String result = convert(unicode);
        String expected = "\uE2F1\uE27E\uE2BC\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void naimaWord() throws Exception {
        String unicode = "ᠨᠠ\u200Dᠢᠮᠠ"; // NA(ZWJ)IMA
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE27E\uE2F5\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void naimaWordMenksoftVersion() throws Exception {
        String unicode = "ᠨᠠᠢ\u180Cᠮᠠ"; // NAI(FVS2)MA (not defined in Unicode 10.0)
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE27E\uE2F5\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void naijaWord() throws Exception {
        String unicode = "ᠨᠠᠢᠵᠠ"; // NAIJA
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE281\uE31D\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void nayijaWord() throws Exception {
        String unicode = "ᠨᠠᠶᠢᠵᠠ"; // NAYIJA
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE321\uE27E\uE31D\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void bainaWord() throws Exception {
        String unicode = "ᠪᠠᠢᠨ᠎ᠠ"; // BAIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE281\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void bayinaWord() throws Exception {
        String unicode = "ᠪᠠᠶᠢᠨ᠎ᠠ"; // BAYIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE321\uE27E\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void baiinaWord() throws Exception {
        String unicode = "ᠪᠠᠢᠢᠨ᠎ᠠ"; // BAIIN(MVS)A
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE27E\uE27E\uE2B6\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void saihanWord() throws Exception {
        String unicode = "ᠰᠠᠶᠢᠬᠠᠨ"; // SAYIHAN
        String result = convert(unicode);
        String expected = "\uE2FD\uE26C\uE321\uE27E\uE2D8\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void sayihanWord() throws Exception {
        String unicode = "ᠰᠠᠶ\u180Bᠢᠬᠠᠨ"; // SAY(FVS1)IHAN
        String result = convert(unicode);
        String expected = "\uE2FD\uE26C\uE320\uE27E\uE2D8\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void sayiWord() throws Exception {
        String unicode = "ᠰᠠᠶ\u180Bᠢ"; // SAY(FVS1)I
        String result = convert(unicode);
        String expected = "\uE2FD\uE26C\uE320\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void taiWord() throws Exception {
        String unicode = "ᠲᠠᠢ"; // TAI
        String result = convert(unicode);
        String expected = "\uE308\uE26C\uE27B";
        assertEquals(expected, result);
    }

    // no longer supporting
    //@Test
    //public void tayiWord() throws Exception {
    //    String unicode = "ᠲᠠᠶᠢ"; // TAYI
    //    String result = convert(unicode);
    //    String expected = "\uE308\uE26C\uE27B";
    //    assertEquals(expected, result);
    //}

    @Test
    public void namayiWord() throws Exception {
        String unicode = "ᠨᠠᠮᠠᠶᠢ"; // NAMAYI
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE2F4\uE26C\uE321\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void eyimuWord() throws Exception {
        String unicode = "ᠡᠶᠢᠮᠦ"; // EYIMU
        String result = convert(unicode);
        String expected = "\uE271\uE321\uE27E\uE2F5\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void soyolWord() throws Exception {
        String unicode = "ᠰᠣᠶᠣᠯ"; // SOYOL
        String result = convert(unicode);
        String expected = "\uE2FE\uE289\uE320\uE289\uE2F9";
        assertEquals(expected, result);
    }

    @Test
    public void buuWord() throws Exception {
        String unicode = "ᠪᠦᠦ"; // BUU
        String result = convert(unicode);
        String expected = "\uE2C2\uE2AC\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void quuWord() throws Exception {
        String unicode = "ᠬᠦᠦ"; // QUU
        String result = convert(unicode);
        String expected = "\uE2D4\uE2AA\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void qeuqenWord() throws Exception {
        String unicode = "ᠬᠡᠦᠬᠡᠳ"; // QEUQEN
        String result = convert(unicode);
        String expected = "\uE2D0\uE277\uE2AB\uE2DA\uE277\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void qeduyinWord() throws Exception {
        String unicode = "ᠬᠡᠳᠦᠶᠢᠨ"; // QEDUYIN
        String result = convert(unicode);
        String expected = "\uE2D0\uE277\uE313\uE2AB\uE321\uE27E\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void qeduinWord() throws Exception {
        String unicode = "ᠬᠡᠳᠦᠢᠨ"; // QEDUIN
        String result = convert(unicode);
        String expected = "\uE2D0\uE277\uE313\uE2AB\uE281\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void anggliWord() throws Exception {
        String unicode = "ᠠᠩᠭᠯᠢ"; // ANGGLI
        String result = convert(unicode);
        String expected = "\uE266\uE2BD\uE2EB\uE2FC\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void asiglajuWord() throws Exception {
        String unicode = "ᠠᠰᠢᠭᠯᠠᠵᠤ"; // ASIGLAJU
        String result = convert(unicode);
        String expected = "\uE266\uE301\uE27E\uE2EE\uE2FA\uE26C\uE31D\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void narangerelWord() throws Exception {
        String unicode = "ᠨᠠᠷᠠᠨᠭᠡᠷᠡᠯ"; // NARANGEREL
        String result = convert(unicode);
        String expected = "\uE2B1\uE26C\uE327\uE26C\uE2B8\uE2EB\uE277\uE327\uE276\uE2F9";
        assertEquals(expected, result);
    }

    @Test
    public void cholmonodoWord() throws Exception {
        String unicode = "ᠴᠣᠯᠮᠣᠨ\u200Dᠣ᠋ᠳᠣ"; // CHOLMON(ZWJ)O(FVS1)DO
        String result = convert(unicode);
        String expected = "\uE315\uE289\uE2FB\uE2F5\uE289\uE2BA\uE288\uE313\uE285";
        assertEquals(expected, result);
    }

    @Test
    public void cholmonodo2Word() throws Exception {
        String unicode = "ᠴᠣᠯᠮᠣᠨᠣ᠋ᠳᠣ"; // CHOLMONO(FVS1)DO
        String result = convert(unicode);
        String expected = "\uE315\uE289\uE2FB\uE2F5\uE289\uE2BA\uE288\uE313\uE285";
        assertEquals(expected, result);
    }

    @Test
    public void bayanunderWord() throws Exception {
        String unicode = "ᠪᠠᠶᠠᠨ\u200Dᠦ᠌ᠨᠳᠦᠷ"; // BAYAN(ZWJ)U(FVS1)NDUR
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE320\uE26C\uE2BA\uE2A8\uE2B8\uE313\uE2AB\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void bayanunder2Word() throws Exception {
        String unicode = "ᠪᠠᠶᠠᠨ\u200Dᠦ᠌ᠨᠳᠦᠷ"; // BAYANU(FVS1)NDUR
        String result = convert(unicode);
        String expected = "\uE2C1\uE26D\uE320\uE26C\uE2BA\uE2A8\uE2B8\uE313\uE2AB\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void sodobiligWord() throws Exception {
        String unicode = "ᠰᠣᠳᠣᠪᠢᠯᠢᠭ᠌"; // SODOBILIG(FVS2)
        String result = convert(unicode);
        String expected = "\uE2FE\uE289\uE313\uE289\uE2C5\uE27F\uE2FA\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void anggilumchechegWord() throws Exception {
        String unicode = "ᠠᠩᠭᠢᠯᠤᠮᠴᠡᠴᠡᠭ"; // ANGGILUMCHECHEG
        String result = convert(unicode);
        String expected = "\uE266\uE2BD\uE2EB\uE27F\uE2FB\uE291\uE2F5\uE317\uE276\uE317\uE276\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void chechegmaWord() throws Exception {
        String unicode = "ᠴᠡᠴᠡᠭᠮ᠎ᠡ"; // CHECHEGM(MVS)A
        String result = convert(unicode);
        String expected = "\uE315\uE276\uE317\uE276\uE2F0\uE2F3\uE274";
        assertEquals(expected, result);
    }


    @Test
    public void sigDefaultWord() throws Exception {
        String unicode = "ᠰᠢᠭ"; // SIG
        String result = convert(unicode);
        String expected = "\uE2FD\uE27E\uE2E8";
        assertEquals(expected, result);
    }

    @Test
    public void sigSpecifiedWord() throws Exception {
        String unicode = "ᠰᠢᠭ᠋"; // SIG(FVS1)
        String result = convert(unicode);
        String expected = "\uE2FD\uE27E\uE2E7";
        assertEquals(expected, result);
    }


    ///////////////////////// Suffixes ////////////////////////


    @Test
    public void yinSuffix() throws Exception {
        String unicode = " ᠶᠢᠨ"; //
        String result = convert(unicode);
        String expected = " \uE321\uE27E\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void unSuffix() throws Exception {
        String unicode = " ᠤᠨ"; //
        String result = convert(unicode);
        String expected = " \uE292\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void uenSuffix() throws Exception {
        String unicode = " ᠦᠨ"; //
        String result = convert(unicode);
        String expected = " \uE2AC\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void uSuffix() throws Exception {
        String unicode = " ᠤ"; //
        String result = convert(unicode);
        String expected = " \uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void ueSuffix() throws Exception {
        String unicode = " ᠦ"; //
        String result = convert(unicode);
        String expected = " \uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void iSuffix() throws Exception {
        String unicode = " ᠢ"; //
        String result = convert(unicode);
        String expected = " \uE282";
        assertEquals(expected, result);
    }

    @Test
    public void yiSuffix() throws Exception {
        String unicode = " ᠶᠢ"; //
        String result = convert(unicode);
        String expected = " \uE321\uE27B";
        assertEquals(expected, result);
    }

    @Test
    public void duSuffix() throws Exception {
        String unicode = " ᠳᠤ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void dueSuffix() throws Exception {
        String unicode = " ᠳᠦ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void tuSuffix() throws Exception {
        String unicode = " ᠲᠤ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void tueSuffix() throws Exception {
        String unicode = " ᠲᠦ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void durSuffix() throws Exception {
        String unicode = " ᠳᠤᠷ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE291\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void duerSuffix() throws Exception {
        String unicode = " ᠳᠦᠷ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE2AB\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void turSuffix() throws Exception {
        String unicode = " ᠲᠤᠷ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE291\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void tuerSuffix() throws Exception {
        String unicode = " ᠲᠦᠷ"; //
        String result = convert(unicode);
        String expected = " \uE309\uE2AB\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void daqiSuffix() throws Exception {
        String unicode = " ᠳᠠᠬᠢ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE26C\uE2DA\uE27C";
        assertEquals(expected, result);
    }

    @Test
    public void deqiSuffix() throws Exception {
        String unicode = " ᠳᠡᠬᠢ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE276\uE2DA\uE27C";
        assertEquals(expected, result);
    }

    @Test
    public void achaSuffix() throws Exception {
        String unicode = " ᠠᠴᠠ"; //
        String result = convert(unicode);
        String expected = " \uE267\uE317\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void echeSuffix() throws Exception {
        String unicode = " ᠡᠴᠡ"; //
        String result = convert(unicode);
        String expected = " \uE271\uE317\uE273";
        assertEquals(expected, result);
    }

    @Test
    public void barSuffix() throws Exception {
        String unicode = " ᠪᠠᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE26D\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void berSuffix() throws Exception {
        String unicode = " ᠪᠡᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE277\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void iyarSuffix() throws Exception {
        String unicode = " ᠢᠶᠠᠷ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE26C\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void iyerSuffix() throws Exception {
        String unicode = " ᠢᠶᠡᠷ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE276\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void taiSuffix() throws Exception {
        String unicode = " ᠲᠠᠢ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE27B";
        assertEquals(expected, result);
    }

    // no longer supporting this
    //@Test
    //public void tayiSuffix() throws Exception {
    //    String unicode = " ᠲᠠᠶᠢ"; //
    //    String result = convert(unicode);
    //    String expected = " \uE308\uE26C\uE27B";
    //    assertEquals(expected, result);
    //}

    @Test
    public void teiSuffix() throws Exception {
        String unicode = " ᠲᠡᠢ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE27B";
        assertEquals(expected, result);
    }

    // no longer supporting this
    //@Test
    //public void teyiSuffix() throws Exception {
    //    String unicode = " ᠲᠡᠶᠢ"; //
    //    String result = convert(unicode);
    //    String expected = " \uE308\uE276\uE27B";
    //    assertEquals(expected, result);
    //}

    @Test
    public void lugaSuffix() throws Exception {
        String unicode = " ᠯᠤᠭ᠎ᠠ"; //
        String result = convert(unicode);
        String expected = " \uE2F8\uE291\uE2E9\uE26A";
        assertEquals(expected, result);
    }

    @Test
    public void luegeSuffix() throws Exception {
        String unicode = " ᠯᠦᠭᠡ"; //
        String result = convert(unicode);
        String expected = " \uE2F8\uE2AB\uE2EB\uE275";
        assertEquals(expected, result);
    }

    @Test
    public void banSuffix() throws Exception {
        String unicode = " ᠪᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE26D\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void benSuffix() throws Exception {
        String unicode = " ᠪᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE2C1\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void iyanSuffix() throws Exception {
        String unicode = " ᠢᠶᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void iyenSuffix() throws Exception {
        String unicode = " ᠢᠶᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE280\uE321\uE276\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void yuganSuffix() throws Exception {
        String unicode = " ᠶᠤᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE31E\uE291\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void yuegenSuffix() throws Exception {
        String unicode = " ᠶᠦᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE31E\uE2AB\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void daganSuffix() throws Exception {
        String unicode = " ᠳᠠᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void degenSuffix() throws Exception {
        String unicode = " ᠳᠡᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void taganSuffix() throws Exception {
        String unicode = " ᠲᠠᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void tegenSuffix() throws Exception {
        String unicode = " ᠲᠡᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void achaganSuffix() throws Exception {
        String unicode = " ᠠᠴᠠᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE267\uE317\uE26C\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void echegenSuffix() throws Exception {
        String unicode = " ᠡᠴᠡᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE271\uE317\uE276\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void taiganSuffix() throws Exception {
        String unicode = " ᠲᠠᠢᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE281\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void tayiganSuffix() throws Exception {
        String unicode = " ᠲᠠᠶᠢᠭᠠᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE26C\uE321\uE27E\uE2EA\uE26C\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void teigenSuffix() throws Exception {
        String unicode = " ᠲᠡᠢᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE281\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void teyigenSuffix() throws Exception {
        String unicode = " ᠲᠡᠶᠢᠭᠡᠨ"; //
        String result = convert(unicode);
        String expected = " \uE308\uE276\uE321\uE27E\uE2EB\uE277\uE2B5";
        assertEquals(expected, result);
    }

    @Test
    public void udSuffix() throws Exception {
        String unicode = " ᠤᠳ"; //
        String result = convert(unicode);
        String expected = " \uE292\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void uedSuffix() throws Exception {
        String unicode = " ᠦᠳ"; //
        String result = convert(unicode);
        String expected = " \uE2AC\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void nugudSuffix() throws Exception {
        String unicode = " ᠨᠤᠭᠤᠳ"; //
        String result = convert(unicode);
        String expected = " \uE2B3\uE291\uE2EC\uE291\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void nueguedSuffix() throws Exception {
        String unicode = " ᠨᠦᠭᠦᠳ"; //
        String result = convert(unicode);
        String expected = " \uE2B3\uE2AB\uE2ED\uE2AC\uE311";
        assertEquals(expected, result);
    }

    @Test
    public void narSuffix() throws Exception {
        String unicode = " ᠨᠠᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2B1\uE26C\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void nerSuffix() throws Exception {
        String unicode = " ᠨᠡᠷ"; //
        String result = convert(unicode);
        String expected = " \uE2B1\uE276\uE325";
        assertEquals(expected, result);
    }

    @Test
    public void uuSuffix() throws Exception {
        String unicode = " ᠤᠤ"; //
        String result = convert(unicode);
        String expected = " \uE292\uE28D";
        assertEquals(expected, result);
    }

    @Test
    public void ueueSuffix() throws Exception {
        String unicode = " ᠦᠦ"; //
        String result = convert(unicode);
        String expected = " \uE2AC\uE2A3";
        assertEquals(expected, result);
    }

    @Test
    public void daSuffix() throws Exception {
        String unicode = " ᠳᠠ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE268";
        assertEquals(expected, result);
    }

    @Test
    public void deSuffix() throws Exception {
        String unicode = " ᠳᠡ"; //
        String result = convert(unicode);
        String expected = " \uE310\uE273";
        assertEquals(expected, result);
    }


}
