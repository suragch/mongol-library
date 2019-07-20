package net.studymongolian.testingapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityRecyclerViewAdapter.ItemClickListener {

    MainActivityRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<String> testingActivities = new ArrayList<>();
        testingActivities.add("Keyboard");
        testingActivities.add("MongolTextView");
        testingActivities.add("MongolTextView text spacing");
        testingActivities.add("MongolEditText IME options");


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvApiTestingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainActivityRecyclerViewAdapter(this, testingActivities);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent;
        switch (position) {
            case 0: // Keyboard
                intent = new Intent(this, KeyboardActivity.class);
                startActivity(intent);
                break;
            case 1: // MongolTextView
                intent = new Intent(this, MongolTextViewActivity.class);
                startActivity(intent);
                break;
            case 2: // MongolTextView text spacing
                intent = new Intent(this, MongolTextViewSpacingActivity.class);
                startActivity(intent);
                break;
            case 3: // MongolEditText IME options
                intent = new Intent(this, MongolEditTextImeOptions.class);
                startActivity(intent);
                break;
        }
    }
}
