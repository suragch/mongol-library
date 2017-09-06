package net.studymongolian.mongollibrary;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.BackgroundColorSpan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;


public class MongolTextStorageTest {

    // FIXME these tests don't work because SpannableStringBuilder not mocked
    // But I don't know how to mock it. Help me write some tests if you know how.

    @Test
    public void setText_emptyString() throws Exception {
        CharSequence unicode = "";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = unicode;
        assertEquals(expected, result);
    }

    @Test
    public void setText_mongolString() throws Exception {
        CharSequence unicode = "ᠪᠢᠴᠢᠭ"; // bichig
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = unicode;
        assertEquals(expected, result);
    }

    @Test
    public void setText_string() throws Exception {
        String unicode = "abc";
        MongolTextStorage storage = new MongolTextStorage(unicode);
        CharSequence result = storage.getUnicodeText();
        CharSequence expected = unicode;
        assertEquals(expected, result);
    }

    @Test
    // learning how to use Mockito
    public void myTest() throws Exception {
        // create mock
        Spanned word = mock(SpannedString.class);

        // tell the mock how to behave
        when(word.length()).thenReturn(4);

        // validate
        int expected = 4;
        int result = word.length();
        assertEquals(expected, result);
    }
}
