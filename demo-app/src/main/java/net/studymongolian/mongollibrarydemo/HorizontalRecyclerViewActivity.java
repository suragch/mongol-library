package net.studymongolian.mongollibrarydemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class HorizontalRecyclerViewActivity extends AppCompatActivity
        implements HorizontalRecyclerViewAdapter.ItemClickListener {

    private HorizontalRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_recyclerview);

        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("ᠨᠢᠭᠡ");
        numbers.add("ᠬᠣᠶᠠᠷ");
        numbers.add("ᠭᠣᠷᠪᠠ");
        numbers.add("ᠳᠥᠷᠪᠡ");
        numbers.add("ᠲᠠᠪᠤ");
        numbers.add("ᠵᠢᠷᠭᠤᠭ᠎ᠠ");
        numbers.add("ᠳᠣᠯᠣᠭ᠎ᠠ");
        numbers.add("ᠨᠠ‍ᠢᠮᠠ");
        numbers.add("ᠶᠢᠰᠦ");
        numbers.add("ᠠᠷᠪᠠ");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new HorizontalRecyclerViewAdapter(this, numbers);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

}
