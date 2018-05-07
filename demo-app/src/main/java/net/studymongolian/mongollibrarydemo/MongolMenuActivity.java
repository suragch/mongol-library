package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolMenu;
import net.studymongolian.mongollibrary.MongolMenuItem;
import net.studymongolian.mongollibrary.MongolToast;

public class MongolMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_menu);
    }

    public void onStandardMenuButtonClick(View view) {
        Button standardMenuButton = findViewById(R.id.standard_menu_button);
        PopupMenu popup = new PopupMenu(this, standardMenuButton);
        popup.getMenuInflater().inflate(R.menu.mongol_menu_activity, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        MongolMenuActivity.this,"You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
        popup.show();
    }

    private MongolMenu getMenu() {
        MongolMenu menu = new MongolMenu(this);
        menu.add(new MongolMenuItem("ᠨᠢᠭᠡ", R.drawable.ic_sun));
        menu.add(new MongolMenuItem("ᠬᠤᠶᠠᠷ", R.drawable.ic_moon));
        menu.add(new MongolMenuItem("ᠭᠤᠷᠪᠠ", R.drawable.ic_star));
        menu.setOnMenuItemClickListener(new MongolMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MongolMenuItem item) {
                MongolToast.makeText(MongolMenuActivity.this, item.getTitle(), MongolToast.LENGTH_SHORT).show();
                return true;
            }
        });
        return menu;
    }

    public void onShowAsDropDownClick(View view) {
        MongolMenu menu = getMenu();
        menu.showAsDropDown(view,0,0);
    }

    public void onShowAtLocationClick(View view) {
        MongolMenu menu = getMenu();
        menu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}
