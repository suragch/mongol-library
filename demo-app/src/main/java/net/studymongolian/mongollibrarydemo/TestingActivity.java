package net.studymongolian.mongollibrarydemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolFont;
import net.studymongolian.mongollibrary.MongolInputMethodManager;
import net.studymongolian.mongollibrary.MongolTextView;

import java.io.File;


public class TestingActivity extends AppCompatActivity {

    MongolEditText editText;
    static final String WORD_ID_KEY = "word_id";
    static final String EDIT_MODE_KEY = "edit_mode";
    static final String WORDS_ADDED_KEY = "words_added";
    private boolean wordsWereAdded = false;
    //private boolean mIsInEditMode = false;

    private static final String LOG_TAG = "AddEditWordActivity";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static File mTempAudioFilePathName = null;
//    private static final String TEMP_AUDIO_FILE_NAME = "temp_recording.3gp";
//    private static final String AUDIO_FILE_EXTENSION = ".3gp";

    private ImageView mRecordButton = null;
    private MediaRecorder mRecorder = null;
    private Handler mRecordingIndicatorHandler;
    private static final int RECORDING_INDICATOR_DELAY = 500; // milliseconds

    private ImageView mPlayButton = null;
    private MediaPlayer mPlayer = null;


    MongolEditText mongolEditText;
    EditText etDefinition;
    EditText etPronunciation;

    private long mCurrentListId;
    private long mEditingWordId = -1; // -1 means adding new word, not editing

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        mongolEditText = (MongolEditText) findViewById(R.id.metMongolWord);
        etDefinition = (EditText) findViewById(R.id.etDefinition);
        etPronunciation = (EditText) findViewById(R.id.etPronunciation);
        ImeContainer imeContainer = (ImeContainer) findViewById(R.id.keyboard_container);

        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(etDefinition);
        mimm.addEditor(etPronunciation);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.SYSTEM_EDITOR_ONLY);
        mimm.startInput();



        // TODO handle hiding the keyboard in the library, don't make me do it here
//        mongolEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean focused) {
//                if (focused) {
//                    InputMethodManager imm = (InputMethodManager)
//                            view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                }
//            }
//        });

    }


}
