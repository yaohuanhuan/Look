package com.look.yx.look.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.look.yx.look.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp,"home")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp,"book"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp,"favorite"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.e("test","onTabSelected");
            }

            @Override
            public void onTabUnselected(int position) {
                Log.e("test","onTabUnselected");
            }

            @Override
            public void onTabReselected(int position) {
                Log.e("test","onTabReselected");
            }
        });
    }
}
