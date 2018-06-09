package net.studymongolian.mongollibrarydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainKeyboardActivity extends AppCompatActivity
        implements KeyboardActivityRecyclerViewAdapter.ItemClickListener {

    KeyboardActivityRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_keyboard);

        // data to populate the RecyclerView with
        ArrayList<String> apiDemos = new ArrayList<>();
        apiDemos.add("Keyboards");
        apiDemos.add("Candidate View");
        apiDemos.add("Navigation");
        apiDemos.add("System Keyboard");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvKeyboardDemoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KeyboardActivityRecyclerViewAdapter(this, apiDemos);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent;
        switch (position) {
            case 0: // Keyboards
                intent = new Intent(this, KeyboardActivity.class);
                startActivity(intent);
                break;
            case 1: // Candidate View
                intent = new Intent(this, KeyboardCandidateActivity.class);
                startActivity(intent);
                break;
            case 2: // Navigation
                intent = new Intent(this, KeyboardNavigationActivity.class);
                startActivity(intent);
                break;
            case 3: // System Keyboard
                intent = new Intent(this, CustomSystemKeyboardActivity.class);
                startActivity(intent);
                break;
        }
    }


}