package net.studymongolian.testingapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;

import java.util.ArrayList;
import java.util.List;


public class KeyboardActivity extends AppCompatActivity {

    private static final String TAG = "TESTING TAG";
    private static final int TEST_DELAY = 0;
    MongolEditText mongolEditText;
    ImeContainer imeContainer;

    Handler handler = new Handler();
    List<Runnable> backspaceTests;
    int testIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);


        mongolEditText = findViewById(R.id.editText);

        // keyboards to include (default style)
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);

        // add keyboards to the IME container
        imeContainer = findViewById(R.id.imeContainer);
        imeContainer.addKeyboard(aeiou); // first one is the default
        imeContainer.addKeyboard(qwerty);

        // The MongolInputMethodManager handles communication between the keyboards and
        // the MongolEditText (or EditText).
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);


    }

    public void onTestDeleteKeyClick(View view) {

        testIndex = 0;

        backspaceTests  = new ArrayList<>();
        backspaceTests.add(backspaceOneChar);
        backspaceTests.add(backspace_M_MVS_M);
        backspaceTests.add(backspace_M_MVS_X);
        backspaceTests.add(backspace_X_MVS_X);
        backspaceTests.add(backspace_M_FVS1_M);
        backspaceTests.add(backspace_M_FVS1_X);
        backspaceTests.add(backspace_X_FVS1_X);
        backspaceTests.add(backspace_M_ZWJ_M);
        backspaceTests.add(backspace_M_ZWJ_X);
        backspaceTests.add(backspace_X_ZWJ_M);
        backspaceTests.add(backspace_X_ZWJ_X);
        backspaceTests.add(backspace_X_ZWJ_M_FVS1);
        backspaceTests.add(backspace_everything_highlighted);

        handler.post(runnableCode);
    }

    final Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            if (testIndex >= backspaceTests.size()) {
                handler.removeCallbacks(null);
                return;
            }
            handler.post(backspaceTests.get(testIndex));
            handler.postDelayed(runnableCode, TEST_DELAY);
            testIndex++;
        }
    };

    private void assertEqualsMetText(final String message, final String expected) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String actual = mongolEditText.getText().toString();
                if (!expected.equals(actual)) {
                    Log.e(TAG, "assertEquals failed on " + message
                            + ": expected = " + expected
                            + ", actual = " + actual);
                } else {
                    Log.i(TAG, "assertEquals passed: " + message);
                }
            }
        }, TEST_DELAY / 4);

    }

    // Deleting

    Runnable backspaceOneChar = new Runnable() {
        @Override
        public void run() {
            final String message = "backspaceOneChar";

            // setup
            final String original = "abc";
            final String expected = "ab";
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_M_MVS_M = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_M_MVS_M";

            // setup
            final String original = "" + MongolCode.Uni.NA + MongolCode.Uni.MVS + MongolCode.Uni.A;
            final String expected = "" + MongolCode.Uni.NA;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_M_MVS_X = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_M_MVS_X";

            // setup
            final String original = "" + MongolCode.Uni.NA + MongolCode.Uni.MVS + "X";
            final String expected = "" + MongolCode.Uni.NA;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_X_MVS_X = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_X_MVS_X";

            // setup
            final String original = "X" + MongolCode.Uni.MVS + "X";
            final String expected = "X";
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_M_FVS1_M = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_M_FVS1_M";

            // setup
            final String original = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.A;
            final String expected = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_M_FVS1_X = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_M_FVS1_X";

            // setup
            final String original = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + "X";
            final String expected = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_X_FVS1_X = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_X_FVS1_X";

            // setup
            final String original = "X" + MongolCode.Uni.FVS1 + "X";
            final String expected = "X" + MongolCode.Uni.FVS1;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_M_ZWJ_M = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_M_ZWJ_M";

            // setup
            final String original = "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ + MongolCode.Uni.A;
            final String expected = "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_M_ZWJ_X = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_M_ZWJ_X";

            // setup
            final String original = "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ + "X";
            final String expected = "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ;
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_X_ZWJ_M = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_X_ZWJ_M";

            // setup
            final String original = "X" + MongolCode.Uni.ZWJ + MongolCode.Uni.A;
            final String expected = "X";
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_X_ZWJ_X = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_X_ZWJ_X";

            // setup
            final String original = "X" + MongolCode.Uni.ZWJ + "X";
            final String expected = "X";
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_X_ZWJ_M_FVS1 = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_X_ZWJ_M_FVS1";

            // setup
            final String original = "X" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1;
            final String expected = "X";
            mongolEditText.setText(original);

            // action
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };

    Runnable backspace_everything_highlighted = new Runnable() {
        @Override
        public void run() {
            final String message = "backspace_everything_highlighted";

            // setup
            final String original = "abc";
            final String expected = "";
            mongolEditText.setText(original);

            // action
            mongolEditText.selectAll();
            Keyboard keyboard = imeContainer.getCurrentKeyboard();
            keyboard.onBackspace();

            // results
            assertEqualsMetText(message, expected);
        }
    };
}