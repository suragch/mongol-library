package net.studymongolian.mongollibrarydemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.studymongolian.mongollibrary.MongolAlertDialog;

public class MongolAlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_alert_dialog);
    }

    public void showNormalAlertClick(View view) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My Title");
        builder.setMessage("This is a message.");

        // add the buttons
        builder.setPositiveButton("Positive button", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // do something...
            }
        });
        builder.setNeutralButton("Neutral button", null);
        builder.setNegativeButton("Negative button", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMongolAlertClick(View view) {
        // setup the alert builder
        MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("This is a message");

        // add the buttons
        builder.setPositiveButton("Positive button", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // do something...
            }
        });
        builder.setNeutralButton("Neutral button", null);
        builder.setNegativeButton("Negative button", null);

        // create and show the alert dialog
        MongolAlertDialog dialog = builder.create();
        dialog.show();
    }
}
