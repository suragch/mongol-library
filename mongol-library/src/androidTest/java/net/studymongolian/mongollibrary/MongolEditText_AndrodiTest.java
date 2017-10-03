package net.studymongolian.mongollibrary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.Editable;
import android.text.TextWatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class MongolEditText_AndrodiTest {

    Context context;
    boolean addTextChangedListenerTest_textWasChanged = false;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
    }



    // FIXME how do you test custom views?
//    @Test
//    public void addTextChangedListener_notifiedOnChange() throws Exception {
//
//        // setup
//        addTextChangedListenerTest_textWasChanged = false;
//        MongolEditText editText = new MongolEditText(context);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                addTextChangedListenerTest_textWasChanged = true;
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        // change text
//        editText.setText("hello");
//
//        boolean result = addTextChangedListenerTest_textWasChanged;
//        boolean expected = true;
//        assertEquals(expected, result);
//    }

}