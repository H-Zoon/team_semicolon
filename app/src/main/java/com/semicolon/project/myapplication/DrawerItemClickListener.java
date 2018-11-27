package com.semicolon.project.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private ListView lvNavList;
    private FrameLayout flContainer;


    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long id) {
        switch (position) {
            case 0:
                flContainer.setBackgroundColor(Color.parseColor("#A52A2A"));
                break;
            case 1:
                flContainer.setBackgroundColor(Color.parseColor("#5F9EA0"));
                break;
            case 2:
                flContainer.setBackgroundColor(Color.parseColor("#556B2F"));
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