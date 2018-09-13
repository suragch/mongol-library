package net.studymongolian.mongollibrarydemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
                Toast.makeText(MongolAlertDialogActivity.this, "Positive button", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Neutral button", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Neutral button", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Negative button", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Negative button", Toast.LENGTH_SHORT).show();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMongolAlertZeroButtonClick(View view) {
        // setup the alert builder
        MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
        builder.setMessage("This is a no-button MongolAlertDialog.");

        // create and show the alert dialog
        MongolAlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMongolAlertOneButtonClick(View view) {
        // setup the alert builder
        MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("This is a one-button MongolAlertDialog.");

        // add the button
        builder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Positive button", Toast.LENGTH_SHORT).show();
            }
        });

        // create and show the alert dialog
        MongolAlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMongolAlertTwoButtonClick(View view) {

        // setup the alert builder
        MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("This is a two-button MongolAlertDialog.");

        // add the buttons
        builder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Positive button", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Negative button", Toast.LENGTH_SHORT).show();
            }
        });

        // create and show the alert dialog
        MongolAlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMongolAlertThreeButtonClick(View view) {
        // setup the alert builder
        MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("This is a three-button MongolAlertDialog.");

        // add the buttons
        builder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Positive button", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Neutral button", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Negative button", Toast.LENGTH_SHORT).show();
            }
        });

        // create and show the alert dialog
        MongolAlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMongolAlertThreeButtonWithColorClick(View view) {

        // setup the alert builder
        MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("This is a three-button MongolAlertDialog with the colors set.");

        // add the buttons
        builder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Positive button", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Neutral button", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MongolAlertDialogActivity.this, "Negative button", Toast.LENGTH_SHORT).show();
            }
        });

        // set the button colors
        builder.setPositiveButtonTextColor(Color.GREEN);
        builder.setNegativeButtonTextColor(Color.RED);
        builder.setNeutralButtonTextColor(Color.BLUE);

        // create and show the alert dialog
        MongolAlertDialog dialog = builder.create();
        dialog.show();
    }
}
