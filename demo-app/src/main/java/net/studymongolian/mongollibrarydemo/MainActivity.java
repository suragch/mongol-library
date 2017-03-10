package net.studymongolian.mongollibrarydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityRecyclerViewAdapter.ItemClickListener {

    MainActivityRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<String> apiDemos = new ArrayList<>();
        apiDemos.add("MongolLabel");
        apiDemos.add("MongolTextView");
        apiDemos.add("Unicode");


        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvApiDemoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainActivityRecyclerViewAdapter(this, apiDemos);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        switch (position) {
            case 0: // MongolLabel
                Intent labelIntent = new Intent(this, MongolLabelActivity.class);
                startActivity(labelIntent);
                break;
            case 1: // MongolTextView
                Intent textViewIntent = new Intent(this, MongolTextViewActivity.class);
                startActivity(textViewIntent);
                break;
            case 2: // Unicode
                Intent unicodeIntent = new Intent(this, UnicodeActivity.class);
                startActivity(unicodeIntent);
                break;
        }
    }
}
