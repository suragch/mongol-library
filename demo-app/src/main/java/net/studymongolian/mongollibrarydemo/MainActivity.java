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
        apiDemos.add("Unicode <--> Menksoft");
        apiDemos.add("MongolFont");
        apiDemos.add("MongolEditText");
        apiDemos.add("Keyboard");
        //apiDemos.add("Testing");


        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvApiDemoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainActivityRecyclerViewAdapter(this, apiDemos);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent;
        switch (position) {
            case 0: // MongolLabel
                intent = new Intent(this, MongolLabelActivity.class);
                startActivity(intent);
                break;
            case 1: // MongolTextView
                intent = new Intent(this, MongolTextViewActivity.class);
                startActivity(intent);
                break;
            case 2: // Unicode
                intent = new Intent(this, UnicodeActivity.class);
                startActivity(intent);
                break;
            case 3: // Unicode <--> Menksoft
                intent = new Intent(this, ConvertCodeActivity.class);
                startActivity(intent);
                break;
            case 4: // MongolFont
                intent = new Intent(this, MongolFontActivity.class);
                startActivity(intent);
                break;
            case 5: // MongolEditText
                intent = new Intent(this, MongolEditTextActivity.class);
                startActivity(intent);
                break;
            case 6: // Keyboard
                intent = new Intent(this, KeyboardActivity.class);
                startActivity(intent);
                break;
//            case 7: // Testing
//                intent = new Intent(this, TestingActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
