package com.semicolon.project.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class DrawerItemClickListener extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView lvNavList;
    private FrameLayout flContainer;


    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long id) {
        switch (position) {
            case 0:
                //flContainer.setBackgroundColor(Color.parseColor("#A52A2A"));
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "네비메뉴 1번", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "네비메뉴 2번", Toast.LENGTH_LONG).show();
                break;
            /*case 3:
                flContainer.setBackgroundColor(Color.parseColor("#FF8C00"));
                break;
            case 4:
                flContainer.setBackgroundColor(Color.parseColor("#DAA520"));
                break;
*/
        }
        //drawerLayout.closeDrawer(listView);
    }
}