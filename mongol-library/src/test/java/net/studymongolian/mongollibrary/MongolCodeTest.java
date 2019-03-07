package net.studymongolian.mongollibrary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MongolCodeTest {

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_A() {
        char wordLastChar = MongolCode.Uni.A;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_E() {
        char wordLastChar = MongolCode.Uni.E;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_I() {
        char wordLastChar = MongolCode.Uni.I;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_O() {
        char wordLastChar = MongolCode.Uni.O;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_U() {
        char wordLastChar = MongolCode.Uni.U;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_OE() {
        char wordLastChar = MongolCode.Uni.OE;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_UE() {
        char wordLastChar = MongolCode.Uni.UE;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithVowel_EE() {
        char wordLastChar = MongolCode.Uni.EE;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_A() {
        char wordLastChar = MongolCode.Uni.A;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_E() {
        char wordLastChar = MongolCode.Uni.E;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_I() {
        char wordLastChar = MongolCode.Uni.I;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_O() {
        char wordLastChar = MongolCode.Uni.O;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_U() {
        char wordLastChar = MongolCode.Uni.U;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_OE() {
        char wordLastChar = MongolCode.Uni.OE;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_UE() {
        char wordLastChar = MongolCode.Uni.UE;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithVowel_EE() {
        char wordLastChar = MongolCode.Uni.EE;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.YIN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithN() {
        char wordLastChar = MongolCode.Uni.NA;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.U;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithN() {
        char wordLastChar = MongolCode.Uni.NA;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.UE;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forMasculineWordEndingWithConsonant() {
        char wordLastChar = MongolCode.Uni.GA;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.MASCULINE, wordLastChar);
        String expected = MongolCode.Suffix.UN;
        assertEquals(expected, result);
    }

    @Test
    public void getSuffixYinUnU_forFeminineWordEndingWithConsonant() {
        char wordLastChar = MongolCode.Uni.GA;
        String result = MongolCode.getSuffixYinUnU(MongolCode.Gender.FEMININE, wordLastChar);
        String expected = MongolCode.Suffix.UEN;
        assertEquals(expected, result);
    }
}