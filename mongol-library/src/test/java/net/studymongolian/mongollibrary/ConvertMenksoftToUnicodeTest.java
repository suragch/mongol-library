package net.studymongolian.mongollibrary;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConvertMenksoftToUnicodeTest {

    private MongolCode converter = MongolCode.INSTANCE;

    // extracting the method name so that it can change without breaking all of the tests
    private String convert(String unicode) {
        return converter.menksoftToUnicode(unicode);
    }

    ////////////////// non-Menksoft strings ////////////////////


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
        String expected = "";
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
    public void unicodeString() {
        String unicode = "ᠮᠤᠩᠭᠤᠯ";
        String result = convert(unicode);
        String expected = "ᠮᠤᠩᠭᠤᠯ";
        assertEquals(expected, result);
    }

    // tests for all isolate values

    @Test
    public void isolateE234() {
        String menksoft = "\uE234";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_BIRGA);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE235() {
        String menksoft = "\uE235";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE236() {
        String menksoft = "\uE236";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE237() {
        String menksoft = "\uE237";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_FULL_STOP);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE238() {
        String menksoft = "\uE238";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_COLON);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE239() {
        String menksoft = "\uE239";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_FOUR_DOTS);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE23A() {
        String menksoft = "\uE23A";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_TODO_SOFT_HYPHEN);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE23B() {
        String menksoft = "\uE23B";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_SIBE_SYLLABLE_BOUNDARY_MARKER);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE23C() {
        String menksoft = "\uE23C";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_MANCHU_COMMA);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE23D() {
        String menksoft = "\uE23D";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_MANCHU_FULL_STOP);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE23E() {
        String menksoft = "\uE23E";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_NIRUGU);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE23F() {
        String menksoft = "\uE23F";
        String result = convert(menksoft);
        String expected = "\uD805\uDE60"; //BIRGA_WITH_ORNAMENT
        assertEquals(expected, result);
    }

    @Test
    public void isolateE240() {
        String menksoft = "\uE240";
        String result = convert(menksoft);
        String expected = "\uD805\uDE61"; // ROTATED_BIRGA
        assertEquals(expected, result);
    }

    @Test
    public void isolateE241() {
        String menksoft = "\uE241";
        String result = convert(menksoft);
        String expected = "\uD805\uDE62"; // DOUBLE_BIRGA_WITH_ORNAMENT
        assertEquals(expected, result);
    }

    @Test
    public void isolateE242() {
        String menksoft = "\uE242";
        String result = convert(menksoft);
        String expected = "\uD805\uDE63"; // TRIPLE_BIRGA_WITH_ORNAMENT
        assertEquals(expected, result);
    }

    @Test
    public void isolateE243() {
        String menksoft = "\uE243";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MIDDLE_DOT);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE244() {
        String menksoft = "\uE244";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE245() {
        String menksoft = "\uE245";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ONE);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE246() {
        String menksoft = "\uE246";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_TWO);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE247() {
        String menksoft = "\uE247";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_THREE);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE248() {
        String menksoft = "\uE248";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE249() {
        String menksoft = "\uE249";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE24A() {
        String menksoft = "\uE24A";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SIX);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE24B() {
        String menksoft = "\uE24B";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE24C() {
        String menksoft = "\uE24C";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE24D() {
        String menksoft = "\uE24D";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_NINE);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE24E() {
        String menksoft = "\uE24E";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE24F() {
        String menksoft = "\uE24F";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.EXCLAMATION_QUESTION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE250() {
        String menksoft = "\uE250";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_EXCLAMATION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE251() {
        String menksoft = "\uE251";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE252() {
        String menksoft = "\uE252";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_SEMICOLON);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE253() {
        String menksoft = "\uE253";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE254() {
        String menksoft = "\uE254";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE255() {
        String menksoft = "\uE255";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE256() {
        String menksoft = "\uE256";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE257() {
        String menksoft = "\uE257";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_TORTOISE_SHELL_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE258() {
        String menksoft = "\uE258";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_TORTOISE_SHELL_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE259() {
        String menksoft = "\uE259";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE25A() {
        String menksoft = "\uE25A";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE25B() {
        String menksoft = "\uE25B";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_WHITE_CORNER_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE25C() {
        String menksoft = "\uE25C";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_WHITE_CORNER_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE25D() {
        String menksoft = "\uE25D";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_COMMA);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE25E() {
        String menksoft = "\uE25E";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.PUNCTUATION_X);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE25F() {
        String menksoft = "\uE25F";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.REFERENCE_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE260() {
        String menksoft = "\uE260";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_EN_DASH);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE261() {
        String menksoft = "\uE261";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_EM_DASH);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE262() {
        String menksoft = "\uE262";
        String result = convert(menksoft);
        String expected = " "; // TODO is this really what is expected
        assertEquals(expected, result);
    }

    @Test
    public void isolateE263() {
        String menksoft = "\uE263";
        String result = convert(menksoft);
        String expected = String.valueOf(MongolCode.Uni.NNBS);
        assertEquals(expected, result);
    }

    @Test
    public void isolateE264() {
        String menksoft = "\uE264";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.A;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE265() {
        String menksoft = "\uE265";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE266() {
        String menksoft = "\uE266";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.A + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE267() {
        String menksoft = "\uE267";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.A + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE268() {
        String menksoft = "\uE268";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE269() {
        String menksoft = "\uE269";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE26A() {
        String menksoft = "\uE26A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE26B() {
        String menksoft = "\uE26B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE26C() {
        String menksoft = "\uE26C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE26D() {
        String menksoft = "\uE26D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE26E() {
        String menksoft = "\uE26E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE26F() {
        String menksoft = "\uE26F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE270() {
        String menksoft = "\uE270";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.E;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE271() {
        String menksoft = "\uE271";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.E + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE272() {
        String menksoft = "\uE272";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.E + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE273() {
        String menksoft = "\uE273";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.E;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE274() {
        String menksoft = "\uE274";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.E + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE275() {
        String menksoft = "\uE275";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.E + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE276() {
        String menksoft = "\uE276";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.E + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE277() {
        String menksoft = "\uE277";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.E + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE278() {
        String menksoft = "\uE278";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.E + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE279() {
        String menksoft = "\uE279";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.I;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE27A() {
        String menksoft = "\uE27A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE27B() {
        String menksoft = "\uE27B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE27C() {
        String menksoft = "\uE27C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE27D() {
        String menksoft = "\uE27D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE27E() {
        String menksoft = "\uE27E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE27F() {
        String menksoft = "\uE27F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE280() {
        String menksoft = "\uE280";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE281() {
        String menksoft = "\uE281";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE282() {
        String menksoft = "\uE282";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE283() {
        String menksoft = "\uE283";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.O;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE284() {
        String menksoft = "\uE284";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.O + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE285() {
        String menksoft = "\uE285";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE286() {
        String menksoft = "\uE286";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE287() {
        String menksoft = "\uE287";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE288() {
        String menksoft = "\uE288";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE289() {
        String menksoft = "\uE289";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE28A() {
        String menksoft = "\uE28A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE28B() {
        String menksoft = "\uE28B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.U;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE28C() {
        String menksoft = "\uE28C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.U + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE28D() {
        String menksoft = "\uE28D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE28E() {
        String menksoft = "\uE28E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE28F() {
        String menksoft = "\uE28F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE290() {
        String menksoft = "\uE290";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE291() {
        String menksoft = "\uE291";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE292() {
        String menksoft = "\uE292";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE293() {
        String menksoft = "\uE293";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.OE;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE294() {
        String menksoft = "\uE294";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE295() {
        String menksoft = "\uE295";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.OE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE296() {
        String menksoft = "\uE296";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE297() {
        String menksoft = "\uE297";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE298() {
        String menksoft = "\uE298";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE299() {
        String menksoft = "\uE299";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE29A() {
        String menksoft = "\uE29A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE29B() {
        String menksoft = "\uE29B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE29C() {
        String menksoft = "\uE29C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE29D() {
        String menksoft = "\uE29D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE29E() {
        String menksoft = "\uE29E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE29F() {
        String menksoft = "\uE29F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A0() {
        String menksoft = "\uE2A0";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.UE;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A1() {
        String menksoft = "\uE2A1";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A2() {
        String menksoft = "\uE2A2";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.UE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A3() {
        String menksoft = "\uE2A3";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A4() {
        String menksoft = "\uE2A4";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A5() {
        String menksoft = "\uE2A5";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A6() {
        String menksoft = "\uE2A6";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A7() {
        String menksoft = "\uE2A7";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A8() {
        String menksoft = "\uE2A8";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2A9() {
        String menksoft = "\uE2A9";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2AA() {
        String menksoft = "\uE2AA";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2AB() {
        String menksoft = "\uE2AB";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2AC() {
        String menksoft = "\uE2AC";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2AD() {
        String menksoft = "\uE2AD";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.EE;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2AE() {
        String menksoft = "\uE2AE";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.EE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2AF() {
        String menksoft = "\uE2AF";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.EE;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B0() {
        String menksoft = "\uE2B0";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.EE + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B1() {
        String menksoft = "\uE2B1";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.NA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B2() {
        String menksoft = "\uE2B2";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B3() {
        String menksoft = "\uE2B3";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.NA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B4() {
        String menksoft = "\uE2B4";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B5() {
        String menksoft = "\uE2B5";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B6() {
        String menksoft = "\uE2B6";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B7() {
        String menksoft = "\uE2B7";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B8() {
        String menksoft = "\uE2B8";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2B9() {
        String menksoft = "\uE2B9";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2BA() {
        String menksoft = "\uE2BA";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2BB() {
        String menksoft = "\uE2BB";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.ANG;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2BC() {
        String menksoft = "\uE2BC";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ANG;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2BD() {
        String menksoft = "\uE2BD";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ANG;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2BE() {
        String menksoft = "\uE2BE";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ANG;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2BF() {
        String menksoft = "\uE2BF";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C0() {
        String menksoft = "\uE2C0";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C1() {
        String menksoft = "\uE2C1";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.BA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C2() {
        String menksoft = "\uE2C2";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.BA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C3() {
        String menksoft = "\uE2C3";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.BA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C4() {
        String menksoft = "\uE2C4";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.BA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C5() {
        String menksoft = "\uE2C5";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.BA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C6() {
        String menksoft = "\uE2C6";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.BA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C7() {
        String menksoft = "\uE2C7";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.BA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C8() {
        String menksoft = "\uE2C8";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.PA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2C9() {
        String menksoft = "\uE2C9";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.PA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2CA() {
        String menksoft = "\uE2CA";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.PA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2CB() {
        String menksoft = "\uE2CB";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.PA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2CC() {
        String menksoft = "\uE2CC";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.PA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2CD() {
        String menksoft = "\uE2CD";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.PA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2CE() {
        String menksoft = "\uE2CE";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2CF() {
        String menksoft = "\uE2CF";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D0() {
        String menksoft = "\uE2D0";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D1() {
        String menksoft = "\uE2D1";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D2() {
        String menksoft = "\uE2D2";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D3() {
        String menksoft = "\uE2D3";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D4() {
        String menksoft = "\uE2D4";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D5() {
        String menksoft = "\uE2D5";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D6() {
        String menksoft = "\uE2D6";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.QA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D7() {
        String menksoft = "\uE2D7";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D8() {
        String menksoft = "\uE2D8";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.QA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2D9() {
        String menksoft = "\uE2D9";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2DA() {
        String menksoft = "\uE2DA";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2DB() {
        String menksoft = "\uE2DB";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2DC() {
        String menksoft = "\uE2DC";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.QA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2DD() {
        String menksoft = "\uE2DD";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2DE() {
        String menksoft = "\uE2DE";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2DF() {
        String menksoft = "\uE2DF";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS3 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E0() {
        String menksoft = "\uE2E0";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E1() {
        String menksoft = "\uE2E1";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E2() {
        String menksoft = "\uE2E2";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E3() {
        String menksoft = "\uE2E3";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E4() {
        String menksoft = "\uE2E4";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E5() {
        String menksoft = "\uE2E5";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.QA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E6() {
        String menksoft = "\uE2E6";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E7() {
        String menksoft = "\uE2E7";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E8() {
        String menksoft = "\uE2E8";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2E9() {
        String menksoft = "\uE2E9";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2EA() {
        String menksoft = "\uE2EA";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2EB() {
        String menksoft = "\uE2EB";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2EC() {
        String menksoft = "\uE2EC";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2ED() {
        String menksoft = "\uE2ED";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.GA; // Menksoft form is not possible to make in Unicode?
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2EE() {
        String menksoft = "\uE2EE";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2EF() {
        String menksoft = "\uE2EF";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS3 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F0() {
        String menksoft = "\uE2F0";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS3 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F1() {
        String menksoft = "\uE2F1";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.MA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F2() {
        String menksoft = "\uE2F2";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.MA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F3() {
        String menksoft = "\uE2F3";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.MA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F4() {
        String menksoft = "\uE2F4";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.MA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F5() {
        String menksoft = "\uE2F5";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.MA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F6() {
        String menksoft = "\uE2F6";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.MA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F7() {
        String menksoft = "\uE2F7";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.LA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F8() {
        String menksoft = "\uE2F8";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.LA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2F9() {
        String menksoft = "\uE2F9";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.LA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2FA() {
        String menksoft = "\uE2FA";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.LA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2FB() {
        String menksoft = "\uE2FB";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.LA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2FC() {
        String menksoft = "\uE2FC";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.LA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2FD() {
        String menksoft = "\uE2FD";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.SA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2FE() {
        String menksoft = "\uE2FE";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.SA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE2FF() {
        String menksoft = "\uE2FF";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE300() {
        String menksoft = "\uE300";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE301() {
        String menksoft = "\uE301";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE302() {
        String menksoft = "\uE302";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE303() {
        String menksoft = "\uE303";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.SHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE304() {
        String menksoft = "\uE304";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.SHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE305() {
        String menksoft = "\uE305";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE306() {
        String menksoft = "\uE306";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SHA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE307() {
        String menksoft = "\uE307";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.SHA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE308() {
        String menksoft = "\uE308";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.TA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE309() {
        String menksoft = "\uE309";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.TA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE30A() {
        String menksoft = "\uE30A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE30B() {
        String menksoft = "\uE30B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE30C() {
        String menksoft = "\uE30C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE30D() {
        String menksoft = "\uE30D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE30E() {
        String menksoft = "\uE30E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.TA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE30F() {
        String menksoft = "\uE30F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.TA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE310() {
        String menksoft = "\uE310";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.DA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE311() {
        String menksoft = "\uE311";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE312() {
        String menksoft = "\uE312";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE313() {
        String menksoft = "\uE313";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE314() {
        String menksoft = "\uE314";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE315() {
        String menksoft = "\uE315";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.CHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE316() {
        String menksoft = "\uE316";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.CHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE317() {
        String menksoft = "\uE317";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.CHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE318() {
        String menksoft = "\uE318";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.JA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE319() {
        String menksoft = "\uE319";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.JA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE31A() {
        String menksoft = "\uE31A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.JA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE31B() {
        String menksoft = "\uE31B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.JA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE31C() {
        String menksoft = "\uE31C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE31D() {
        String menksoft = "\uE31D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.JA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE31E() {
        String menksoft = "\uE31E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.YA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE31F() {
        String menksoft = "\uE31F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE320() {
        String menksoft = "\uE320";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.YA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE321() {
        String menksoft = "\uE321";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE322() {
        String menksoft = "\uE322";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE323() {
        String menksoft = "\uE323";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE324() {
        String menksoft = "\uE324";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE325() {
        String menksoft = "\uE325";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE326() {
        String menksoft = "\uE326";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE327() {
        String menksoft = "\uE327";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE328() {
        String menksoft = "\uE328";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.RA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE329() {
        String menksoft = "\uE329";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.WA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE32A() {
        String menksoft = "\uE32A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.WA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE32B() {
        String menksoft = "\uE32B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE32C() {
        String menksoft = "\uE32C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.WA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE32D() {
        String menksoft = "\uE32D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.FA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE32E() {
        String menksoft = "\uE32E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.FA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE32F() {
        String menksoft = "\uE32F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.FA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE330() {
        String menksoft = "\uE330";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.FA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE331() {
        String menksoft = "\uE331";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.FA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE332() {
        String menksoft = "\uE332";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.FA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE333() {
        String menksoft = "\uE333";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE334() {
        String menksoft = "\uE334";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE335() {
        String menksoft = "\uE335";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.KA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE336() {
        String menksoft = "\uE336";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE337() {
        String menksoft = "\uE337";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE338() {
        String menksoft = "\uE338";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE339() {
        String menksoft = "\uE339";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE33A() {
        String menksoft = "\uE33A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE33B() {
        String menksoft = "\uE33B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.KHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE33C() {
        String menksoft = "\uE33C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE33D() {
        String menksoft = "\uE33D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE33E() {
        String menksoft = "\uE33E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.KHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE33F() {
        String menksoft = "\uE33F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.TSA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE340() {
        String menksoft = "\uE340";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TSA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE341() {
        String menksoft = "\uE341";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.TSA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE342() {
        String menksoft = "\uE342";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE343() {
        String menksoft = "\uE343";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.ZA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE344() {
        String menksoft = "\uE344";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE345() {
        String menksoft = "\uE345";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.HAA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE346() {
        String menksoft = "\uE346";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.HAA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE347() {
        String menksoft = "\uE347";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.HAA + MongolCode.Uni.ZWJ;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE348() {
        String menksoft = "\uE348";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZRA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE349() {
        String menksoft = "\uE349";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZRA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE34A() {
        String menksoft = "\uE34A";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.ZRA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE34B() {
        String menksoft = "\uE34B";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.LHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE34C() {
        String menksoft = "\uE34C";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.LHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE34D() {
        String menksoft = "\uE34D";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.LHA;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE34E() {
        String menksoft = "\uE34E";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.ZHI;
        assertEquals(expected, result);
    }

    @Test
    public void isolateE34F() {
        String menksoft = "\uE34F";
        String result = convert(menksoft);
        String expected = "" + MongolCode.Uni.CHI;
        assertEquals(expected, result);
    }

    //// Punctuation

    @Test
    public void punctuationBirga() {
        char menksoft = MongolCode.Glyph.BIRGA;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_BIRGA);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationEllipsis() {
        char menksoft = MongolCode.Glyph.ELLIPSIS;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationComma() {
        char menksoft = MongolCode.Glyph.COMMA;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationFullStop() {
        char menksoft = MongolCode.Glyph.FULL_STOP;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_FULL_STOP);
        assertEquals(expected, result);
    }
    @Test
    public void punctuationColon() {
        char menksoft = MongolCode.Glyph.COLON;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_COLON);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationFourDots() {
        char menksoft = MongolCode.Glyph.FOUR_DOTS;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_FOUR_DOTS);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationTodoSoftHyphen() {
        char menksoft = MongolCode.Glyph.TODO_SOFT_HYPHEN;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_TODO_SOFT_HYPHEN);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationSibeSyllableBoundaryMarker() {
        char menksoft = MongolCode.Glyph.SIBE_SYLLABLE_BOUNDARY_MARKER;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_SIBE_SYLLABLE_BOUNDARY_MARKER);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationManchuComma() {
        char menksoft = MongolCode.Glyph.MANCHU_COMMA;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_MANCHU_COMMA);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationManchuFullStop() {
        char menksoft = MongolCode.Glyph.MANCHU_FULL_STOP;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_MANCHU_FULL_STOP);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationNirugu() {
        char menksoft = MongolCode.Glyph.NIRUGU;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_NIRUGU);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationMiddleDot() {
        char menksoft = MongolCode.Glyph.MIDDLE_DOT;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MIDDLE_DOT);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationZero() {
        char menksoft = MongolCode.Glyph.ZERO;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationOne() {
        char menksoft = MongolCode.Glyph.ONE;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ONE);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationTwo() {
        char menksoft = MongolCode.Glyph.TWO;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_TWO);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationThree() {
        char menksoft = MongolCode.Glyph.THREE;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_THREE);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationFour() {
        char menksoft = MongolCode.Glyph.FOUR;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationFive() {
        char menksoft = MongolCode.Glyph.FIVE;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationSix() {
        char menksoft = MongolCode.Glyph.SIX;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SIX);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationSeven() {
        char menksoft = MongolCode.Glyph.SEVEN;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationEight() {
        char menksoft = MongolCode.Glyph.EIGHT;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationNine() {
        char menksoft = MongolCode.Glyph.NINE;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_NINE);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationQuestionExclamation() {
        char menksoft = MongolCode.Glyph.QUESTION_EXCLAMATION;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationExclamationQuestion() {
        char menksoft = MongolCode.Glyph.EXCLAMATION_QUESTION;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.EXCLAMATION_QUESTION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationExclamation() {
        char menksoft = MongolCode.Glyph.EXCLAMATION;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_EXCLAMATION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationQuestion() {
        char menksoft = MongolCode.Glyph.QUESTION;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationSemicolon() {
        char menksoft = MongolCode.Glyph.SEMICOLON;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_SEMICOLON);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationLeftParenthesis() {
        char menksoft = MongolCode.Glyph.LEFT_PARENTHESIS;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationRightParenthesis() {
        char menksoft = MongolCode.Glyph.RIGHT_PARENTHESIS;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationLeftAngleBracket() {
        char menksoft = MongolCode.Glyph.LEFT_ANGLE_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationRightAngleBracket() {
        char menksoft = MongolCode.Glyph.RIGHT_ANGLE_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationLeftBracket() {
        char menksoft = MongolCode.Glyph.LEFT_TORTOISE_SHELL_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_TORTOISE_SHELL_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationRightBracket() {
        char menksoft = MongolCode.Glyph.RIGHT_TORTOISE_SHELL_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_TORTOISE_SHELL_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationLeftDoubleAngleBracket() {
        char menksoft = MongolCode.Glyph.LEFT_DOUBLE_ANGLE_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationRightDoubleAngleBracket() {
        char menksoft = MongolCode.Glyph.RIGHT_DOUBLE_ANGLE_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationLeftWhiteCornerBracket() {
        char menksoft = MongolCode.Glyph.LEFT_WHITE_CORNER_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_WHITE_CORNER_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationRightWhiteCornerBracket() {
        char menksoft = MongolCode.Glyph.RIGHT_WHITE_CORNER_BRACKET;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_WHITE_CORNER_BRACKET);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationFullWidthComma() {
        char menksoft = MongolCode.Glyph.FULL_WIDTH_COMMA;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_COMMA);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationX() {
        char menksoft = MongolCode.Glyph.X;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.PUNCTUATION_X);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationReferenceMark() {
        char menksoft = MongolCode.Glyph.REFERENCE_MARK;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.REFERENCE_MARK);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationEnDash() {
        char menksoft = MongolCode.Glyph.EN_DASH;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_EN_DASH);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationEmDash() {
        char menksoft = MongolCode.Glyph.EM_DASH;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.VERTICAL_EM_DASH);
        assertEquals(expected, result);
    }

    @Test
    public void punctuationSuffixSpace() {
        char menksoft = MongolCode.Glyph.SUFFIX_SPACE;
        String result = convert(String.valueOf(menksoft));
        String expected = String.valueOf(MongolCode.Uni.NNBS);
        assertEquals(expected, result);
    }



    ///////////////////////// Words ////////////////////////////





    @Test
    public void bichigWord() {
        String menksoft = "\uE2C1\uE27F\uE317\uE27E\uE2E8"; // bichig
        String result = convert(menksoft);
        String expected = "ᠪᠢᠴᠢᠭ";
        assertEquals(expected, result);
    }

    @Test
    public void egeshigWord() {
        String menksoft = "\uE271\uE2EB\uE277\uE301\uE27E\uE2E8\uE263\uE27A\uE2B9\uE2A3"; // EGESIG (NNBSP) INU
        String result = convert(menksoft);
        String expected = "ᠡᠭᠡᠰᠢᠭ ᠢᠨᠦ";
        assertEquals(expected, result);
    }

    @Test
    public void bujigWord() {
        String menksoft = "\uE2C2\uE2AA\uE31D\uE27E\uE2E8\uE263\uE282\uE263\uE2C1\uE277\uE2B5\uE263\uE31E\uE2AB\uE2EB\uE277\uE2B5"; // BUJIG (NNBSP) I (NNBSP) BEN (NNBSP) YUGEN
        String result = convert(menksoft);
        String expected = "ᠪᠦᠵᠢᠭ ᠢ ᠪᠡᠨ ᠶᠦᠭᠡᠨ";
        assertEquals(expected, result);
    }

    @Test
    public void chirigWord() {
        String menksoft = "\uE315\uE27E\uE327\uE27E\uE2E8\uE263\uE2F1\uE26C\uE2B7\uE27B";
        String result = convert(menksoft);
        String expected = "ᠴᠢᠷᠢᠭ ᠮᠠᠨᠢ"; // CHIRIG (NNBSP) MANI
        assertEquals(expected, result);
    }

    @Test
    public void egchiWord() {
        String menksoft = "\uE271\uE2F0\uE317\uE273";
        String result = convert(menksoft);
        String expected = "ᠡᠭᠴᠡ"; // EGCHI
        assertEquals(expected, result);
    }

    @Test
    public void qugjimWord() {
        String menksoft = "\uE2D4\uE2AA\uE2F0\uE31D\uE27E\uE2F3\uE263\uE310\uE2AB\uE325\uE263\uE280\uE321\uE276\uE2B5\uE263\uE310\uE276\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠬᠦᠭᠵᠢᠮ ᠳᠦᠷ ᠢᠶᠡᠨ ᠳᠡᠭᠡᠨ"; // QUGJIM (NNBSP) DUR (NNBSP) IYEN (NNBSP) DEGEN
        assertEquals(expected, result);
    }

    @Test
    public void buridgelWord() {
        String menksoft = "\uE2C2\uE2AA\uE327\uE27E\uE314\uE2EB\uE277\uE2F9\uE263\uE280\uE321\uE276\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠪᠦᠷᠢᠳᠭᠡᠯ ᠢᠶᠡᠨ"; // BURIDGEL (NNBSP) IYEN
        assertEquals(expected, result);
    }

    @Test
    public void sedqilWord() {
        String menksoft = "\uE2FD\uE276\uE314\uE2DA\uE27F\uE2F9\uE263\uE2F1\uE27E\uE2B7\uE27B";
        String result = convert(menksoft);
        String expected = "ᠰᠡᠳᠬᠢᠯ ᠮᠢᠨᠢ"; // SEDQIL (NNBSP) MINI
        assertEquals(expected, result);
    }

    @Test
    public void uiledburiWord() {
        String menksoft = "\uE2A2\uE27E\uE2FA\uE276\uE314\uE2C6\uE2AC\uE327\uE27B\uE263\uE310\uE2A3";
        String result = convert(menksoft);
        String expected = "ᠦᠢᠯᠡᠳᠪᠦᠷᠢ ᠳᠦ"; // UILEDBURI (NNBSP) DU
        assertEquals(expected, result);
    }

    @Test
    public void jeligudqenWord() {
        String menksoft = "\uE31A\uE276\uE2FA\uE27E\uE2ED\uE2AC\uE314\uE2DA\uE277\uE2B5\uE263\uE2A3";
        String result = convert(menksoft);
        String expected = "ᠵᠡᠯᠢᠭᠦᠳᠬᠡᠨ ᠦ"; // JELIGUDQEN (NNBSP) U
        assertEquals(expected, result);
    }

    @Test
    public void manggalWord() {
        String menksoft = "\uE2F1\uE26C\uE2BC\uE2EA\uE26C\uE2F9\uE263\uE310\uE291\uE325\uE263\uE280\uE321\uE26C\uE2B5\uE263\uE310\uE26C\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠮᠠᠩᠭᠠᠯ ᠳᠤᠷ ᠢᠶᠠᠨ ᠳᠠᠭᠠᠨ"; // MANGGAL (NNBSP) DUR (NNBSP) IYAN (NNBSP) DAGAN
        assertEquals(expected, result);
    }

    @Test
    public void dengWord() {
        String menksoft = "\uE310\uE2A9\uE2BB\uE263\uE282";
        String result = convert(menksoft);
        String expected = "ᠳ᠋ᠦᠩ ᠢ"; // D(FVS1)UNG (NNBSP) I
        assertEquals(expected, result);
    }

    @Test
    public void sodnamWord() {
        String menksoft = "\uE2FE\uE289\uE314\uE2B7\uE26C\uE2F3\uE263\uE267\uE317\uE268\uE263\uE2C1\uE26D\uE2B5\uE263\uE267\uE317\uE26C\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠰᠣᠳᠨᠠᠮ ᠠᠴᠠ ᠪᠠᠨ ᠠᠴᠠᠭᠠᠨ"; // SODNAM (NNBSP) ACHA (NNBSP) BAN (NNBSP) ACHAGAN
        assertEquals(expected, result);
    }

    @Test
    public void lhagbaWord() {
        String menksoft = "\uE34B\uE26C\uE2EE\uE2C5\uE26B\uE263\uE2F8\uE291\uE2E9\uE26A";
        String result = convert(menksoft);
        String expected = "ᡀᠠᠭᠪᠠ ᠯᠤᠭ᠎ᠠ"; // LHAGBA (NNBSP) LUG(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void chebegmedWord() {
        String menksoft = "\uE315\uE276\uE2C5\uE277\uE2EB\uE2F6\uE276\uE311\uE263\uE2F8\uE2AB\uE2EB\uE275";
        String result = convert(menksoft);
        String expected = "ᠴᠡᠪᠡᠭᠮᠡᠳ ᠯᠦᠭᠡ"; // CHEBEGMED (NNBSP) LUGE
        assertEquals(expected, result);
    }

    @Test
    public void tsementWord() {
        String menksoft = "\uE33F\uE2B0\uE2F4\uE2B0\uE2BA\uE30A\uE263\uE308\uE26C\uE321\uE27E\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠼᠧᠮᠧᠨᠲ ᠲᠠᠶᠢᠭᠠᠨ"; // TSEMENT (NNBSP) TAYIGAN
        assertEquals(expected, result);
    }

    @Test
    public void uniyeWord() {
        String menksoft = "\uE2A2\uE2B7\uE27E\uE31F\uE274\uE263\uE308\uE276\uE321\uE27E\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠦᠨᠢᠶ᠎ᠡ ᠲᠡᠶᠢᠭᠡᠨ"; // UNIY(MVS)E (NNBSP) TEYIGEN
        assertEquals(expected, result);
    }

    @Test
    public void qoyinaWord() {
        String menksoft = "\uE2D2\uE289\uE321\uE27E\uE2B6\uE26A";
        String result = convert(menksoft);
        String expected = "ᠬᠣᠶᠢᠨ᠎ᠠ"; // QOYIN(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void angnaWord() {
        String menksoft = "\uE266\uE2BE\uE2B6\uE26A";
        String result = convert(menksoft);
        String expected = "ᠠᠩᠨ᠎ᠠ"; // ANGN(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void chinggaWord() {
        String menksoft = "\uE315\uE27E\uE2BC\uE2E9\uE26A";
        String result = convert(menksoft);
        String expected = "ᠴᠢᠩᠭ᠎ᠠ"; // CHINGG(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void chingalaquWord() {
        String menksoft = "\uE315\uE27E\uE2BC\uE2EA\uE26C\uE2FA\uE26C\uE2DC\uE28D";
        String result = convert(menksoft);
        String expected = "ᠴᠢᠩᠭᠠᠯᠠᠬᠤ"; // CHINGGALAQU
        assertEquals(expected, result);
    }

    @Test
    public void daljiygsanWord() {
        String menksoft = "\uE30E\uE26C\uE2FB\uE31D\uE27E\uE321\uE2EE\uE301\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠳᠠᠯᠵᠢᠶᠭᠰᠠᠨ"; // DALJIYGSAN
        assertEquals(expected, result);
    }

    @Test
    public void ilbigchiWord() {
        String menksoft = "\uE27A\uE2FB\uE2C5\uE27F\uE2F0\uE317\uE27B";
        String result = convert(menksoft);
        String expected = "ᠢᠯᠪᠢᠭᠴᠢ"; // ILBIGCHI
        assertEquals(expected, result);
    }

    @Test
    public void bichigchiWord() {
        String menksoft = "\uE2C1\uE27F\uE317\uE27E\uE2F0\uE317\uE27B";
        String result = convert(menksoft);
        String expected = "ᠪᠢᠴᠢᠭᠴᠢ"; // BICHIGCHI
        assertEquals(expected, result);
    }

    @Test
    public void sigsiglequWord() {
        String menksoft = "\uE2FD\uE27E\uE2EB\uE301\uE27E\uE2EB\uE2FC\uE276\uE2DD\uE2A7";
        String result = convert(menksoft);
        String expected = "ᠰᠢᠭᠰᠢᠭᠯᠡᠬᠦ"; // SIGSIGLEQU
        assertEquals(expected, result);
    }

    @Test
    public void diglimsigsenWord() {
        String menksoft = "\uE30E\uE27E\uE2EB\uE2FC\uE27E\uE2F4\uE301\uE27E\uE2EB\uE301\uE276\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠳᠢᠭᠯᠢᠮᠰᠢᠭᠰᠡᠨ"; // DIGLIMSIGSEN
        assertEquals(expected, result);
    }

    @Test
    public void chigigligWord() {
        String menksoft = "\uE315\uE27E\uE2EB\uE27F\uE2EB\uE2FC\uE27E\uE2E8";
        String result = convert(menksoft);
        String expected = "ᠴᠢᠭᠢᠭᠯᠢᠭ"; // CHIGIGLIG
        assertEquals(expected, result);
    }

    @Test
    public void monggeWord() {
        String menksoft = "\uE2F2\uE29C\uE2BD\uE2EB\uE275";
        String result = convert(menksoft);
        String expected = "ᠮᠥᠩᠭᠡ"; // MONGGE
        assertEquals(expected, result);
    }

    @Test
    public void kinoWord() {
        String menksoft = "\uE333\uE27F\uE2B9\uE286";
        String result = convert(menksoft);
        String expected = "ᠺᠢᠨᠣ᠋"; // KINO(FVS1)
        assertEquals(expected, result);
    }

    @Test
    public void bayigulquWord() {
        String menksoft = "\uE2C1\uE26D\uE321\uE27E\uE2EC\uE291\uE2FA\uE2DC\uE28D";
        String result = convert(menksoft);
        String expected = "ᠪᠠᠶᠢᠭᠤᠯᠬᠤ"; // BAYIGULQU
        assertEquals(expected, result);
    }

    @Test
    public void gregWord() {
        String menksoft = "\uE2E3\uE327\uE2B0\uE2E8";
        String result = convert(menksoft);
        String expected = "ᠭᠷᠧᠭ"; // GREG
        assertEquals(expected, result);
    }

    @Test
    public void motorWord() {
        String menksoft = "\uE2F2\uE289\uE30D\uE289\uE325";
        String result = convert(menksoft);
        String expected = "ᠮᠣᠲ᠋ᠣᠷ"; // MOT(FVS1)OR
        assertEquals(expected, result);
    }

    @Test
    public void neigemligWord() {
        String menksoft = "\uE2B1\uE276\uE321\uE27E\uE2EB\uE277\uE2F5\uE2FA\uE27E\uE2E8";
        String result = convert(menksoft);
        String expected = "ᠨᠡᠶᠢᠭᠡᠮᠯᠢᠭ"; // NEYIGEMLIG
        assertEquals(expected, result);
    }

    @Test
    public void mongolWord() {
        String menksoft = "\uE2F2\uE289\uE2BC\uE2EC\uE289\uE2F9";
        String result = convert(menksoft);
        String expected = "ᠮᠣᠩᠭᠣᠯ"; // MONGGOL
        assertEquals(expected, result);
    }

    @Test
    public void yatugaWord() {
        String menksoft = "\uE31E\uE26C\uE30B\uE291\uE2E9\uE26A";
        String result = convert(menksoft);
        String expected = "ᠶᠠᠲᠤᠭ᠎ᠠ"; // YATUG(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void olaganaWord() {
        String menksoft = "\uE28C\uE2FA\uE26C\uE2EA\uE26C\uE2B6\uE26A";
        String result = convert(menksoft);
        String expected = "ᠤᠯᠠᠭᠠᠨ᠎ᠠ"; // OLAGAN(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void bichiglequWord() {
        String menksoft = "\uE2C1\uE27F\uE317\uE27E\uE2EB\uE2FC\uE276\uE2DD\uE2A7";
        String result = convert(menksoft);
        String expected = "ᠪᠢᠴᠢᠭᠯᠡᠬᠦ"; // BICHIGLEQU
        assertEquals(expected, result);
    }

    @Test
    public void programWord() {
        String menksoft = "\uE2C8\uE326\uE289\uE2EF\uE327\uE26C\uE2F3";
        String result = convert(menksoft);
        String expected = "ᠫᠷᠣᠭ᠍ᠷᠠᠮ"; // PROG(FVS3)RAM
        assertEquals(expected, result);
    }

    @Test
    public void kartWord() {
        String menksoft = "\uE333\uE26D\uE326\uE30A";
        String result = convert(menksoft);
        String expected = "ᠺᠠᠷᠲ"; // KART
        assertEquals(expected, result);
    }

    @Test
    public void dungnelteWord() {
        String menksoft = "\uE310\uE2A9\uE2BC\uE2B7\uE276\uE2FA\uE30B\uE273";
        String result = convert(menksoft);
        String expected = "ᠳ᠋ᠦᠩᠨᠡᠯᠲᠡ"; // D(FVS1)UNGNELTE
        assertEquals(expected, result);
    }

    @Test
    public void gramWord() {
        String menksoft = "\uE2E3\uE327\uE26C\uE2F3";
        String result = convert(menksoft);
        String expected = "ᠭᠷᠠᠮ"; // GRAM
        assertEquals(expected, result);
    }

    @Test
    public void minggaWord() {
        String menksoft = "\uE2F1\uE27E\uE2BC\uE2E9\uE26A";
        String result = convert(menksoft);
        String expected = "ᠮᠢᠩᠭ᠎ᠠ"; // MINGG(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void naimaWordMenksoftVersion() {
        String menksoft = "\uE2B1\uE26C\uE27E\uE2F5\uE268";
        String result = convert(menksoft);
        String expected = "ᠨᠠᠢ\u180Cᠮᠠ"; // NAI(FVS2)MA (not defined in Unicode 10.0)
        assertEquals(expected, result);
    }

    @Test
    public void naijaWord() {
        String menksoft = "\uE2B1\uE26C\uE281\uE31D\uE268";
        String result = convert(menksoft);
        String expected = "ᠨᠠᠢᠵᠠ"; // NAIJA
        assertEquals(expected, result);
    }

    @Test
    public void nayijaWord() {
        String menksoft = "\uE2B1\uE26C\uE321\uE27E\uE31D\uE268";
        String result = convert(menksoft);
        String expected = "ᠨᠠᠶᠢᠵᠠ"; // NAYIJA
        assertEquals(expected, result);
    }

    @Test
    public void bainaWord() {
        String menksoft = "\uE2C1\uE26D\uE281\uE2B6\uE26A";
        String result = convert(menksoft);
        String expected = "ᠪᠠᠢᠨ᠎ᠠ"; // BAIN(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void bayinaWord() {
        String menksoft = "\uE2C1\uE26D\uE321\uE27E\uE2B6\uE26A";
        String result = convert(menksoft);
        String expected = "ᠪᠠᠶᠢᠨ᠎ᠠ"; // BAYIN(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void baiinaWord() {
        String menksoft = "\uE2C1\uE26D\uE27E\uE27E\uE2B6\uE26A";
        String result = convert(menksoft);
        String expected = "ᠪᠠᠢᠢᠨ᠎ᠠ"; // BAIIN(MVS)A
        assertEquals(expected, result);
    }

    @Test
    public void saihanWord() {
        String menksoft = "\uE2FD\uE26C\uE321\uE27E\uE2D8\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠰᠠᠶᠢᠬᠠᠨ"; // SAYIHAN
        assertEquals(expected, result);
    }

    @Test
    public void sayihanWord() {
        String menksoft = "\uE2FD\uE26C\uE320\uE27E\uE2D8\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠰᠠᠶ\u180Bᠢᠬᠠᠨ"; // SAY(FVS1)IHAN
        assertEquals(expected, result);
    }

    @Test
    public void sayiWord() {
        String menksoft = "\uE2FD\uE26C\uE320\uE27B";
        String result = convert(menksoft);
        String expected = "ᠰᠠᠶ\u180Bᠢ"; // SAY(FVS1)I
        assertEquals(expected, result);
    }

    @Test
    public void taiWord() {
        String menksoft = "\uE308\uE26C\uE27B";
        String result = convert(menksoft);
        String expected = "ᠲᠠᠢ"; // TAI
        assertEquals(expected, result);
    }

    @Test
    public void namayiWord() {
        String menksoft = "\uE2B1\uE26C\uE2F4\uE26C\uE321\uE27B";
        String result = convert(menksoft);
        String expected = "ᠨᠠᠮᠠᠶᠢ"; // NAMAYI
        assertEquals(expected, result);
    }

    @Test
    public void eyimuWord() {
        String menksoft = "\uE271\uE321\uE27E\uE2F5\uE2A3";
        String result = convert(menksoft);
        String expected = "ᠡᠶᠢᠮᠦ"; // EYIMU
        assertEquals(expected, result);
    }

    @Test
    public void soyolWord() {
        String menksoft = "\uE2FE\uE289\uE320\uE289\uE2F9";
        String result = convert(menksoft);
        String expected = "ᠰᠣᠶᠣᠯ"; // SOYOL
        assertEquals(expected, result);
    }

    @Test
    public void buuWord() {
        String menksoft = "\uE2C2\uE2AC\uE2A3";
        String result = convert(menksoft);
        String expected = "ᠪᠦᠦ"; // BUU
        assertEquals(expected, result);
    }

    @Test
    public void quuWord() {
        String menksoft = "\uE2D4\uE2AA\uE2A3";
        String result = convert(menksoft);
        String expected = "ᠬᠦᠦ"; // QUU
        assertEquals(expected, result);
    }

    @Test
    public void qeuqenWord() {
        String menksoft = "\uE2D0\uE277\uE2AB\uE2DA\uE277\uE311";
        String result = convert(menksoft);
        String expected = "ᠬᠡᠦᠬᠡᠳ"; // QEUQEN
        assertEquals(expected, result);
    }

    @Test
    public void qeduyinWord() {
        String menksoft = "\uE2D0\uE277\uE313\uE2AB\uE321\uE27E\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠬᠡᠳᠦᠶᠢᠨ"; // QEDUYIN
        assertEquals(expected, result);
    }

    @Test
    public void qeduinWord() {
        String menksoft = "\uE2D0\uE277\uE313\uE2AB\uE281\uE2B5";
        String result = convert(menksoft);
        String expected = "ᠬᠡᠳᠦᠢᠨ"; // QEDUIN
        assertEquals(expected, result);
    }

    @Test
    public void anggliWord() {
        String menksoft = "\uE266\uE2BD\uE2EB\uE2FC\uE27B";
        String result = convert(menksoft);
        String expected = "ᠠᠩᠭᠯᠢ"; // ANGGLI
        assertEquals(expected, result);
    }

    @Test
    public void asiglajuWord() {
        String menksoft = "\uE266\uE301\uE27E\uE2EE\uE2FA\uE26C\uE31D\uE28D";
        String result = convert(menksoft);
        String expected = "ᠠᠰᠢᠭᠯᠠᠵᠤ"; // ASIGLAJU
        assertEquals(expected, result);
    }

    @Test
    public void narangerelWord() {
        String menksoft = "\uE2B1\uE26C\uE327\uE26C\uE2B8\uE2EB\uE277\uE327\uE276\uE2F9";
        String result = convert(menksoft);
        String expected = "ᠨᠠᠷᠠᠨᠭᠡᠷᠡᠯ"; // NARANGEREL
        assertEquals(expected, result);
    }

    @Test
    public void cholmonodoWord() {
        String menksoft = "\uE315\uE289\uE2FB\uE2F5\uE289\uE2BA\uE288\uE313\uE285";
        String result = convert(menksoft);
        String expected = "ᠴᠣᠯᠮᠣᠨ\u200Dᠣ᠋ᠳᠣ"; // CHOLMON(ZWJ)O(FVS1)DO
        assertEquals(expected, result);
    }

    @Test
    public void cholmonodo2Word() {
        String menksoft = "\uE315\uE289\uE2FB\uE2F5\uE289\uE2BA\uE288\uE313\uE285";
        String result = convert(menksoft);
        String expected = "ᠴᠣᠯᠮᠣᠨᠣ᠋ᠳᠣ"; // CHOLMONO(FVS1)DO
        assertEquals(expected, result);
    }

    @Test
    public void bayanunderWord() {
        String menksoft = "\uE2C1\uE26D\uE320\uE26C\uE2BA\uE2A8\uE2B8\uE313\uE2AB\uE325";
        String result = convert(menksoft);
        String expected = "ᠪᠠᠶᠠᠨ\u200Dᠦ᠌ᠨᠳᠦᠷ"; // BAYAN(ZWJ)U(FVS1)NDUR
        assertEquals(expected, result);
    }

    @Test
    public void sodobiligWord() {
        String menksoft = "\uE2FE\uE289\uE313\uE289\uE2C5\uE27F\uE2FA\uE27E\uE2E8";
        String result = convert(menksoft);
        String expected = "ᠰᠣᠳᠣᠪᠢᠯᠢᠭ᠌"; // SODOBILIG(FVS2)
        assertEquals(expected, result);
    }

    @Test
    public void anggilumchechegWord() {
        String menksoft = "\uE266\uE2BD\uE2EB\uE27F\uE2FB\uE291\uE2F5\uE317\uE276\uE317\uE276\uE2E8";
        String result = convert(menksoft);
        String expected = "ᠠᠩᠭᠢᠯᠤᠮᠴᠡᠴᠡᠭ"; // ANGGILUMCHECHEG
        assertEquals(expected, result);
    }

    @Test
    public void chechegmaWord() {
        String menksoft = "\uE315\uE276\uE317\uE276\uE2F0\uE2F3\uE274";
        String result = convert(menksoft);
        String expected = "ᠴᠡᠴᠡᠭᠮ᠎ᠡ"; // CHECHEGM(MVS)A
        assertEquals(expected, result);
    }


    @Test
    public void sigDefaultWord() {
        String menksoft = "\uE2FD\uE27E\uE2E8";
        String result = convert(menksoft);
        String expected = "ᠰᠢᠭ"; // SIG
        assertEquals(expected, result);
    }

    @Test
    public void sigSpecifiedWord() {
        String menksoft = "\uE2FD\uE27E\uE2E7";
        String result = convert(menksoft);
        String expected = "ᠰᠢᠭ᠋"; // SIG(FVS1)
        assertEquals(expected, result);
    }


    ///////////////////////// Suffixes ////////////////////////


    @Test
    public void yinSuffix() {
        String menksoft = "\uE263\uE321\uE27E\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠶᠢᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void unSuffix() {
        String menksoft = "\uE263\uE292\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠤᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void uenSuffix() {
        String menksoft = "\uE263\uE2AC\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠦᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void uSuffix() {
        String menksoft = "\uE263\uE28D";
        String result = convert(menksoft);
        String expected = " ᠤ"; //
        assertEquals(expected, result);
    }

    @Test
    public void ueSuffix() {
        String menksoft = "\uE263\uE2A3";
        String result = convert(menksoft);
        String expected = " ᠦ"; //
        assertEquals(expected, result);
    }

    @Test
    public void iSuffix() {
        String menksoft = "\uE263\uE282";
        String result = convert(menksoft);
        String expected = " ᠢ"; //
        assertEquals(expected, result);
    }

    @Test
    public void yiSuffix() {
        String menksoft = "\uE263\uE321\uE27B";
        String result = convert(menksoft);
        String expected = " ᠶᠢ"; //
        assertEquals(expected, result);
    }

    @Test
    public void duSuffix() {
        String menksoft = "\uE263\uE310\uE28D";
        String result = convert(menksoft);
        String expected = " ᠳᠤ"; //
        assertEquals(expected, result);
    }

    @Test
    public void dueSuffix() {
        String menksoft = "\uE263\uE310\uE2A3";
        String result = convert(menksoft);
        String expected = " ᠳᠦ"; //
        assertEquals(expected, result);
    }

    @Test
    public void tuSuffix() {
        String menksoft = "\uE263\uE309\uE28D";
        String result = convert(menksoft);
        String expected = " ᠲᠤ"; //
        assertEquals(expected, result);
    }

    @Test
    public void tueSuffix() {
        String menksoft = "\uE263\uE309\uE2A3";
        String result = convert(menksoft);
        String expected = " ᠲᠦ"; //
        assertEquals(expected, result);
    }

    @Test
    public void durSuffix() {
        String menksoft = "\uE263\uE310\uE291\uE325";
        String result = convert(menksoft);
        String expected = " ᠳᠤᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void duerSuffix() {
        String menksoft = "\uE263\uE310\uE2AB\uE325";
        String result = convert(menksoft);
        String expected = " ᠳᠦᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void turSuffix() {
        String menksoft = "\uE263\uE309\uE291\uE325";
        String result = convert(menksoft);
        String expected = " ᠲᠤᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void tuerSuffix() {
        String menksoft = "\uE263\uE309\uE2AB\uE325";
        String result = convert(menksoft);
        String expected = " ᠲᠦᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void daqiSuffix() {
        String menksoft = "\uE263\uE310\uE26C\uE2DA\uE27C";
        String result = convert(menksoft);
        String expected = " ᠳᠠᠬᠢ"; //
        assertEquals(expected, result);
    }

    @Test
    public void deqiSuffix() {
        String menksoft = "\uE263\uE310\uE276\uE2DA\uE27C";
        String result = convert(menksoft);
        String expected = " ᠳᠡᠬᠢ"; //
        assertEquals(expected, result);
    }

    @Test
    public void achaSuffix() {
        String menksoft = "\uE263\uE267\uE317\uE268";
        String result = convert(menksoft);
        String expected = " ᠠᠴᠠ"; //
        assertEquals(expected, result);
    }

    @Test
    public void echeSuffix() {
        String menksoft = "\uE263\uE271\uE317\uE273";
        String result = convert(menksoft);
        String expected = " ᠡᠴᠡ"; //
        assertEquals(expected, result);
    }

    @Test
    public void barSuffix() {
        String menksoft = "\uE263\uE2C1\uE26D\uE325";
        String result = convert(menksoft);
        String expected = " ᠪᠠᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void berSuffix() {
        String menksoft = "\uE263\uE2C1\uE277\uE325";
        String result = convert(menksoft);
        String expected = " ᠪᠡᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void iyarSuffix() {
        String menksoft = "\uE263\uE280\uE321\uE26C\uE325";
        String result = convert(menksoft);
        String expected = " ᠢᠶᠠᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void iyerSuffix() {
        String menksoft = "\uE263\uE280\uE321\uE276\uE325";
        String result = convert(menksoft);
        String expected = " ᠢᠶᠡᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void taiSuffix() {
        String menksoft = "\uE263\uE308\uE26C\uE27B";
        String result = convert(menksoft);
        String expected = " ᠲᠠᠢ"; //
        assertEquals(expected, result);
    }

    @Test
    public void teiSuffix() {
        String menksoft = "\uE263\uE308\uE276\uE27B";
        String result = convert(menksoft);
        String expected = " ᠲᠡᠢ"; //
        assertEquals(expected, result);
    }

    @Test
    public void lugaSuffix() {
        String menksoft = "\uE263\uE2F8\uE291\uE2E9\uE26A";
        String result = convert(menksoft);
        String expected = " ᠯᠤᠭ᠎ᠠ"; //
        assertEquals(expected, result);
    }

    @Test
    public void luegeSuffix() {
        String menksoft = "\uE263\uE2F8\uE2AB\uE2EB\uE275";
        String result = convert(menksoft);
        String expected = " ᠯᠦᠭᠡ"; //
        assertEquals(expected, result);
    }

    @Test
    public void banSuffix() {
        String menksoft = "\uE263\uE2C1\uE26D\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠪᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void benSuffix() {
        String menksoft = "\uE263\uE2C1\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠪᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void iyanSuffix() {
        String menksoft = "\uE263\uE280\uE321\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠢᠶᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void iyenSuffix() {
        String menksoft = "\uE263\uE280\uE321\uE276\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠢᠶᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void yuganSuffix() {
        String menksoft = "\uE263\uE31E\uE291\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠶᠤᠭᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void yuegenSuffix() {
        String menksoft = "\uE263\uE31E\uE2AB\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠶᠦᠭᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void daganSuffix() {
        String menksoft = "\uE263\uE310\uE26C\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠳᠠᠭᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void degenSuffix() {
        String menksoft = "\uE263\uE310\uE276\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠳᠡᠭᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void taganSuffix() {
        String menksoft = "\uE263\uE308\uE26C\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠲᠠᠭᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void tegenSuffix() {
        String menksoft = "\uE263\uE308\uE276\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠲᠡᠭᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void achaganSuffix() {
        String menksoft = "\uE263\uE267\uE317\uE26C\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠠᠴᠠᠭᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void echegenSuffix() {
        String menksoft = "\uE263\uE271\uE317\uE276\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠡᠴᠡᠭᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void taiganSuffix() {
        String menksoft = "\uE263\uE308\uE26C\uE281\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠲᠠᠢᠭᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void tayiganSuffix() {
        String menksoft = "\uE263\uE308\uE26C\uE321\uE27E\uE2EA\uE26C\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠲᠠᠶᠢᠭᠠᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void teigenSuffix() {
        String menksoft = "\uE263\uE308\uE276\uE281\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠲᠡᠢᠭᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void teyigenSuffix() {
        String menksoft = "\uE263\uE308\uE276\uE321\uE27E\uE2EB\uE277\uE2B5";
        String result = convert(menksoft);
        String expected = " ᠲᠡᠶᠢᠭᠡᠨ"; //
        assertEquals(expected, result);
    }

    @Test
    public void udSuffix() {
        String menksoft = "\uE263\uE292\uE311";
        String result = convert(menksoft);
        String expected = " ᠤᠳ"; //
        assertEquals(expected, result);
    }

    @Test
    public void uedSuffix() {
        String menksoft = "\uE263\uE2AC\uE311";
        String result = convert(menksoft);
        String expected = " ᠦᠳ"; //
        assertEquals(expected, result);
    }

    @Test
    public void nugudSuffix() {
        String menksoft = "\uE263\uE2B3\uE291\uE2EC\uE291\uE311";
        String result = convert(menksoft);
        String expected = " ᠨᠤᠭᠤᠳ"; //
        assertEquals(expected, result);
    }

    @Test
    public void nueguedSuffix() {
        String menksoft = "\uE263\uE2B3\uE2AB\uE2ED\uE2AC\uE311";
        String result = convert(menksoft);
        String expected = " ᠨᠦᠭᠦᠳ"; //
        assertEquals(expected, result);
    }

    @Test
    public void narSuffix() {
        String menksoft = "\uE263\uE2B1\uE26C\uE325";
        String result = convert(menksoft);
        String expected = " ᠨᠠᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void nerSuffix() {
        String menksoft = "\uE263\uE2B1\uE276\uE325";
        String result = convert(menksoft);
        String expected = " ᠨᠡᠷ"; //
        assertEquals(expected, result);
    }

    @Test
    public void uuSuffix() {
        String menksoft = "\uE263\uE292\uE28D";
        String result = convert(menksoft);
        String expected = " ᠤᠤ"; //
        assertEquals(expected, result);
    }

    @Test
    public void ueueSuffix() {
        String menksoft = "\uE263\uE2AC\uE2A3";
        String result = convert(menksoft);
        String expected = " ᠦᠦ"; //
        assertEquals(expected, result);
    }

    @Test
    public void daSuffix() {
        String menksoft = "\uE263\uE310\uE268";
        String result = convert(menksoft);
        String expected = " ᠳᠠ"; //
        assertEquals(expected, result);
    }

    @Test
    public void deSuffix() {
        String menksoft = "\uE263\uE310\uE273";
        String result = convert(menksoft);
        String expected = " ᠳᠡ"; //
        assertEquals(expected, result);
    }

    // TODO test MVS words
}
