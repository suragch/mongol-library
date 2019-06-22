package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolButton;


public class MongolButtonActivity extends AppCompatActivity {

    MongolButton mMongolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_button);

        mMongolButton = findViewById(R.id.mongol_button);
    }

    public void normalButtonClick(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "system button", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void mongolButtonClick(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "mongol button", Toast.LENGTH_SHORT);
        toast.show();
    }
}
