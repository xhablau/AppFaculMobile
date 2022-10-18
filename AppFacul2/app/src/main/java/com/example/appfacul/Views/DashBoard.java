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

        drawerLayout=findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view) {
            MenuSecundario.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MenuSecundario.closeDrawer(drawerLayout);
    }

    public void Clickhome(View View){
        MenuSecundario.redirecActivity(this,MenuPrincipal.class);
    }

    public void clickDashbord(View view){
        recreate();
    }
    public void clickAboutUs(View view){
        MenuSecundario.redirecActivity(this, AboutUs.class);
    }

    public void clickLogOut(View view){
        MenuSecundario.logout(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        MenuSecundario.closeDrawer(drawerLayout);
    }

}
