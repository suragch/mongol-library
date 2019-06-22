package net.studymongolian.mongollibrary;


import android.text.SpannableStringBuilder;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MongolTextStorageInstrumentedTest {


    // setText

    @Test
    public void setText_nullString() {
        CharSequence unicode = null;
        //noinspection ConstantConditions
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = new SpannableStringBuilder();
        assertEquals(expected, result);
    }

    @Test
    public void setText_emptyString() {
        CharSequence unicode = "";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = new SpannableStringBuilder(unicode);
        assertEquals(expected, result);
    }

    @Test
    public void setText_mongolString() {
        CharSequence unicode = "ᠪᠢᠴᠢᠭ"; // bichig
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = new SpannableStringBuilder(unicode);
        assertEquals(expected, result);
    }

    @Test
    public void setText_string() {
        String unicode = "abc";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = new SpannableStringBuilder(unicode);
        assertEquals(expected, result);
    }

    // delete

    @Test
    public void delete_everything() {
        String unicode = "abc";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.delete(0, unicode.length());
        CharSequence expected = new SpannableStringBuilder();
        assertEquals(expected, result);
    }

    @Test
    public void delete_lastChar() {
        String unicode = "abc";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.delete(unicode.length() - 1, unicode.length());
        CharSequence expected = new SpannableStringBuilder("ab");
        assertEquals(expected, result);
    }

    @Test
    public void delete_firstChar() {
        String unicode = "abc";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.delete(0, 1);
        CharSequence expected = new SpannableStringBuilder("bc");
        assertEquals(expected, result);
    }

    @Test
    public void delete_middleChar() {
        String unicode = "abc";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.delete(1, 2);
        CharSequence expected = new SpannableStringBuilder("ac");
        assertEquals(expected, result);
    }

    // insert

    @Test
    public void insert_nothing() {
        String unicode = "abc";
        String insertionString = "";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.insert(0, insertionString);
        CharSequence expected = new SpannableStringBuilder("abc");
        assertEquals(expected, result);
    }

    @Test
    public void insert_atBeginning() {
        String unicode = "abc";
        String insertionString = "1";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.insert(0, insertionString);
        CharSequence expected = new SpannableStringBuilder("1abc");
        assertEquals(expected, result);
    }

    @Test
    public void insert_atEnd() {
        String unicode = "abc";
        String insertionString = "1";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.insert(unicode.length(), insertionString);
        CharSequence expected = new SpannableStringBuilder("abc1");
        assertEquals(expected, result);
    }

    @Test
    public void insert_inMiddle() {
        String unicode = "abc";
        String insertionString = "1";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.insert(1, insertionString);
        CharSequence expected = new SpannableStringBuilder("a1bc");
        assertEquals(expected, result);
    }

    // append

    @Test
    public void append_toEmptyString() {
        String unicode = "";
        String appendString = "1";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.append(appendString);
        CharSequence expected = new SpannableStringBuilder("1");
        assertEquals(expected, result);
    }

    @Test
    public void append_toNormalString() {
        String unicode = "abc";
        String appendString = "1";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.append(appendString);
        CharSequence expected = new SpannableStringBuilder("abc1");
        assertEquals(expected, result);
    }

    // replace

    @Test
    public void replace_partialString() {
        String unicode = "abc";
        String replacementString = "_";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.replace(1, 2, replacementString);
        CharSequence expected = new SpannableStringBuilder("a_c");
        assertEquals(expected, result);
    }

    @Test
    public void replace_partialStringFromPartialString() {
        String unicode = "abc";
        String replacementString = "123";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.replace(1, 2, replacementString, 1, 2);
        CharSequence expected = new SpannableStringBuilder("a2c");
        assertEquals(expected, result);
    }
}
