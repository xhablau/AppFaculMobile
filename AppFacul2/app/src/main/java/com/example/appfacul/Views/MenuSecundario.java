package com.example.appfacul.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appfacul.R;

public class MenuSecundario extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu_principal);

            drawerLayout=findViewById(R.id.drawer);

    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void Clickhome (View view){
        recreate();
    }

    public void clickDashbord(View view) {
        redirecActivity(this, DashBoardActivity.class);
    }
    public void clickAboutUs(View view){
        redirecActivity(this, AboutUsActivity.class);
    }

    public void clickLogOut( View view){
        logout(this);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Tem certeza que quer sair?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishActivity(0);
                System.exit(0);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    public static void redirecActivity(Activity activity, Class Class) {
        Intent intent=new Intent(activity, Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
