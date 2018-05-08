package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.studymongolian.mongollibrary.MongolMenu;
import net.studymongolian.mongollibrary.MongolMenuItem;
import net.studymongolian.mongollibrary.MongolToast;

public class MongolMenuActivity extends AppCompatActivity {

    private static final int MARGIN_DP = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mongol_menu);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mongol_menu_activity_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_menu:
                View menuButton = findViewById(R.id.action_menu); // any view is ok
                int[] location = new int[2];
                menuButton.getLocationInWindow(location);
                int gravity = Gravity.TOP | Gravity.RIGHT;
                int marginPx = convertDpToPx(MARGIN_DP);
                int yOffset = location[1] + marginPx;
                MongolMenu menu = getMenu();
                menu.showAtLocation(menuButton, gravity, marginPx, yOffset);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int convertDpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    public void onStandardMenuButtonClick(View view) {
        Button standardMenuButton = findViewById(R.id.standard_menu_button);
        PopupMenu popup = new PopupMenu(this, standardMenuButton);
        popup.getMenuInflater().inflate(R.menu.mongol_menu_activity, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MongolMenuActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
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

    public void onNoIconsClick(View view) {
        MongolMenu menu = new MongolMenu(this);
        menu.add(new MongolMenuItem("ᠨᠢᠭᠡ"));
        menu.add(new MongolMenuItem("ᠬᠤᠶᠠᠷ"));
        menu.add(new MongolMenuItem("ᠭᠤᠷᠪᠠ"));
        menu.setOnMenuItemClickListener(new MongolMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MongolMenuItem item) {
                MongolToast.makeText(MongolMenuActivity.this, item.getTitle(), MongolToast.LENGTH_SHORT).show();
                return true;
            }
        });
        menu.showAsDropDown(view,0,0);
    }
}
