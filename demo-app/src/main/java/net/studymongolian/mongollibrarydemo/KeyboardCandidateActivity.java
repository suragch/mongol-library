package net.studymongolian.mongollibrarydemo;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;
import net.studymongolian.mongollibrary.MongolTextView;
import net.studymongolian.mongollibrary.MongolToast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class KeyboardCandidateActivity extends AppCompatActivity
        implements ImeContainer.DataSource, ImeContainer.OnNonSystemImeListener {

    ImeContainer imeContainer;
    MongolTextView mongolTextView;
    MongolEditText mongolEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_candidate);

        imeContainer = findViewById(R.id.ime_container);
        mongolTextView = findViewById(R.id.mtv_database);

        // provide words for candidate selection
        imeContainer.setDataSource(this);
        // this activity will handle hide requests from the keyboard
        imeContainer.setOnNonSystemImeListener(this);
        imeContainer.showSystemKeyboardsOption("ᠰᠢᠰᠲ᠋ᠧᠮ");

        // set up input method manager
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mongolEditText = findViewById(R.id.mongoledittext);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);

        mongolEditText.requestFocus();

        // need to also disallow system keyboard in Manifest.xml
        // android:windowSoftInputMode="stateHidden" // todo make this unnecessary
    }

    // ImeContainer.DataSource methods

    @Override
    public void onRequestWordsStartingWith(String text) {
        if (text.equals(String.valueOf(MongolCode.Uni.NNBS))) {
            getSuffixes();
        } else {
            new GetWordsStartingWith(this).execute(text);
        }
    }

    @Override
    public void onWordFinished(String word, String previousWord) {
        MongolToast.makeText(this, "saving " + word, Toast.LENGTH_SHORT).show();
        new SaveWord(this).execute(word, previousWord);
    }

    @Override
    public void onCandidateClick(int position, String word, String previousWordInEditor) {
        Log.i("TAG", "onCandidateClick: ");
        addSpace();
        new GetWordsFollowing(this).execute(word);
    }

    private void addSpace() {
        InputConnection ic = imeContainer.getInputConnection();
        if (ic == null) return;
        ic.commitText(" ", 1);
    }

    @Override
    public void onCandidateLongClick(int position, String word, String previousWordInEditor) {
        MongolToast.makeText(this, word, Toast.LENGTH_SHORT).show();
    }

    private void getSuffixes() {
        // for demo only (not a full list)
        // real keyboard should do smart prediction depending on previous word
        // String previousWord = imeContainer.getPreviousMongolWord(true);
        List<String> suffixes = new ArrayList<>();
        suffixes.add("" + MongolCode.Suffix.UU);
        suffixes.add("" + MongolCode.Suffix.YI);
        suffixes.add("" + MongolCode.Suffix.YIN);
        suffixes.add("" + MongolCode.Suffix.IYAN);
        suffixes.add("" + MongolCode.Suffix.IYAR);
        suffixes.add("" + MongolCode.Suffix.ACHA);
        suffixes.add("" + MongolCode.Suffix.DU);
        suffixes.add("" + MongolCode.Suffix.TAI);

        imeContainer.setCandidates(suffixes);
    }

    // ImeContainer.OnNonSystemImeListener method

    @Override
    public void onHideKeyboardRequest() {
        // the activity should hide the keyboard here
        imeContainer.setVisibility(View.GONE);
    }

    @Override
    public void onSystemKeyboardRequest() {
        imeContainer.setVisibility(View.GONE);
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showSoftInput(mongolEditText, 0);
    }

    public void onShowKeyboardButtonClick(View view) {
        imeContainer.setVisibility(View.VISIBLE);
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
            DummyDatabase db = DummyDatabase.getInstance();
            return db.queryWordsStartingWith(prefix);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            KeyboardCandidateActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.imeContainer.setCandidates(result);

            DummyDatabase db = DummyDatabase.getInstance();
            String dbText = db.toString();
            activity.mongolTextView.setText(dbText);
        }
    }

    private static class SaveWord extends AsyncTask<String, Integer, Void> {

        private WeakReference<KeyboardCandidateActivity> activityReference;

        // only retain a weak reference to the activity
        SaveWord(KeyboardCandidateActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(String... params) {
            String word = params[0];
            String previousWord = params[1];
            DummyDatabase db = DummyDatabase.getInstance();
            db.insertOrUpdateWord(word);
            if (!TextUtils.isEmpty(previousWord)) {
                db.updateFollowingWords(previousWord, word);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            KeyboardCandidateActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            DummyDatabase db = DummyDatabase.getInstance();
            String dbText = db.toString();
            activity.mongolTextView.setText(dbText);
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
            DummyDatabase db = DummyDatabase.getInstance();
            db.insertOrUpdateWord(word);
            return db.queryWordsFollowing(word);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            KeyboardCandidateActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.imeContainer.setCandidates(result);

            DummyDatabase db = DummyDatabase.getInstance();
            String dbText = db.toString();
            activity.mongolTextView.setText(dbText);
        }
    }

    private static class DummyDatabase {

        List<Word> words = new ArrayList<>();

        private static final DummyDatabase INSTANCE = new DummyDatabase();

        private DummyDatabase() {
            if (INSTANCE != null)
                throw new IllegalStateException("Already instantiated");
        }

        public static DummyDatabase getInstance() {
            return INSTANCE;
        }

        private void simulateLongDatabaseOperation() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<String> queryWordsStartingWith(String prefix) {

            List<String> list = new ArrayList<>();
            for (Word word : words) {
                if (word.word.startsWith(prefix)) {
                    list.add(word.word);
                }
            }

            // default examples
            if (words.size() == 0) {
                list.add("ᠮᠣᠷᠢ");
                list.add("ᠦᠬᠡᠷ");
                list.add("ᠲᠡᠮᠡᠭᠡ");
            }

            simulateLongDatabaseOperation();

            return list;
        }

        List<String> queryWordsFollowing(String word) {

            List<String> list = new ArrayList<>();
            int index = indexOf(word);
            if (index >= 0) {
                String following = words.get(index).following;
                if (!TextUtils.isEmpty(following))
                    list.add(following);
            }

            // default examples
            if (words.size() < 2) {
                list.add("ᠬᠣᠨᠢ");
                list.add("ᠢᠮᠠᠭ᠎ᠠ");
            }

            simulateLongDatabaseOperation();

            return list;
        }

        public void insertOrUpdateWord(String word) {
            int index = indexOf(word);
            if (index >= 0) {
                words.get(index).incrementFrequency();
            } else {
                words.add(new Word(word));
            }
        }

        private int indexOf(String text) {
            int index = -1;
            for (Word word : words) {
                index++;
                if (word.word.equals(text))
                    return index;
            }
            return -1;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (Word word : words) {
                builder.append(word.word);
                builder.append(" ");
                builder.append(word.frequency);
                builder.append(" ");
                builder.append(word.following);
                builder.append("\n");
            }
            return builder.toString();
        }

        public void updateFollowingWords(String word, String followingWord) {
            int index = indexOf(word);
            if (index >= 0) {
                words.get(index).setFollowing(followingWord);
            } else {
                Word newWord = new Word(word);
                newWord.setFollowing(followingWord);
                words.add(newWord);
            }
        }
    }

    private static class Word {
        String word;
        int frequency;
        String following;

        Word(String word) {
            this.word = word;
            frequency = 1;
            following = "";
        }

        public void incrementFrequency() {
            frequency++;
        }

        public void setFollowing(String following) {
            this.following = following;
        }
    }
}