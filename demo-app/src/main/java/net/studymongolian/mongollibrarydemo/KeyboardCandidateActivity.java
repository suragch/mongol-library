package net.studymongolian.mongollibrarydemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;
import net.studymongolian.mongollibrary.MongolToast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class KeyboardCandidateActivity extends AppCompatActivity implements ImeContainer.DataSource {

    ImeContainer imeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // select one of the two following methods to load the Keyboards
        //loadKeyboardsProgrammatically();
        loadKeyboardsFromXml();

        // provide words for candidate selection
        imeContainer.setDataSource(this);

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);

        // need to also disallow system keyboard in Manifest.xml
        // android:windowSoftInputMode="stateHidden" // todo make this unnecessary
    }

    private void loadKeyboardsFromXml() {
        setContentView(R.layout.activity_keyboard_candidate_customized);
        imeContainer = findViewById(R.id.ime_container);
    }

    // programmatically loaded keyboards will have the default style
    private void loadKeyboardsProgrammatically() {

        // set a content view without preloaded keyboards
        setContentView(R.layout.activity_keyboard_candidate);

        // keyboards to include (default style)
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);

        // request a candidates view for each keyboard (default is NONE)
        aeiou.setCandidatesLocation(Keyboard.CandidatesLocation.VERTICAL_LEFT);
        qwerty.setCandidatesLocation(Keyboard.CandidatesLocation.HORIZONTAL_TOP);

        // add keyboards to the IME container
        imeContainer = findViewById(R.id.ime_container);
        imeContainer.addKeyboard(aeiou);
        imeContainer.addKeyboard(qwerty);
    }


    // ImeContainer.DataSource methods

    @Override
    public void onRequestWordsStartingWith(String text) {
        new GetWordsStartingWith(this).execute(text);
    }

    @Override
    public void onRequestWordsFollowing(String word) {
        new GetWordsFollowing(this).execute(word);
    }

    @Override
    public void onCandidateLongClick(int position, String text) {
        MongolToast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private static class GetWordsStartingWith extends AsyncTask<String, Integer, List<String>> {

        private WeakReference<KeyboardCandidateActivity> activityReference;

        // only retain a weak reference to the activity
        GetWordsStartingWith(KeyboardCandidateActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<String> doInBackground(String... params) {
            String prefix = params[0];
            DummyDatabase db = new DummyDatabase();
            return db.queryWordsStartingWith(prefix);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            KeyboardCandidateActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.imeContainer.setCandidates(result);
        }
    }

    private static class GetWordsFollowing extends AsyncTask<String, Integer, List<String>> {

        private WeakReference<KeyboardCandidateActivity> activityReference;

        // only retain a weak reference to the activity
        GetWordsFollowing(KeyboardCandidateActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<String> doInBackground(String... params) {
            String word = params[0];
            DummyDatabase db = new DummyDatabase();
            return db.queryWordsFollowing(word);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            KeyboardCandidateActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.imeContainer.setCandidates(result);
        }
    }

    private static class DummyDatabase {

        private void simulateLongDatabaseOperation() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<String> queryWordsStartingWith(String prefix) {

            // This is a dummy list.
            // In a production app you would use `word` to look up words
            // from a real database.
            List<String> list = new ArrayList<>();
            list.add(prefix);
            list.add("ᠮᠣᠷᠢ");
            list.add("ᠦᠬᠡᠷ");
            list.add("ᠲᠡᠮᠡᠭᠡ");

            simulateLongDatabaseOperation();

            return list;
        }

        List<String> queryWordsFollowing(String word) {

            // This is a dummy list.
            // In a production app you would use `word` to look up words
            // from a real database.
            List<String> list = new ArrayList<>();
            list.add("ᠬᠣᠨᠢ");
            list.add("ᠢᠮᠠᠭ᠎ᠠ");

            simulateLongDatabaseOperation();

            return list;
        }
    }
}