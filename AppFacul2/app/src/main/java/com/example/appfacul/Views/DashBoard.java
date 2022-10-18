package com.example.appfacul.Views;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.appfacul.R;

public class DashBoard extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();

        drawerLayout=findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view) {
        MenuDrawer.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MenuDrawer.closeDrawer(drawerLayout);
    }

    public void Clickhome(View View){
        MenuDrawer.redirecActivity(this,MenuDrawer.class);
    }

    public void clickDashbord(View view){
        recreate();
    }
    public void clickAboutUs(View view){
        MenuDrawer.redirecActivity(this, AboutUs.class);
    }

    public void clickLogOut(View view){
        MenuDrawer.logout(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        MenuDrawer.closeDrawer(drawerLayout);
    }

}