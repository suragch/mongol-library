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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import java.util.ArrayList;


public class TestingActivity extends AppCompatActivity {


    RecyclerView rvResults;
//    SearchWordListAdapter mWordAdapter;
//    SearchVerseResultsAdapter mVerseAdapter;
    int searchScope;
    ArrayList<String> searchTerms;
    StringBuilder currentTerm;
    MongolEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        editText = findViewById(R.id.met_input_box);
        ImeContainer keyboard = findViewById(R.id.ime_container);

        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(editText);
        mimm.setIme(keyboard);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
        mimm.startInput();

        searchTerms = new ArrayList<>();
        currentTerm = new StringBuilder();
        //searchScope = Book.SEARCH_ALL;

        // listen for keyboard input changes
        editText.addTextChangedListener(mTextWatcher);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
//                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i("TAG", "onTextChanged: " + currentTerm);
            //new GetWordsThatStartWith().execute(charSequence.toString());
        }

        // these do nothing
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        @Override
        public void afterTextChanged(Editable editable) {}
    };

    public void onButtonClick(View view) {
        //textView.setTextSize(56);
    }
}
