package com.example.appfacul.Views

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.appfacul.GlobalClass
import com.example.appfacul.R
import com.example.appfacul.StartNewActivity.StartNewActivity
import com.example.appfacul.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class MenuPrincipal : AppCompatActivity() {
    lateinit var drawerOpen:ImageView

    lateinit var navigationDrawer: NavigationView

    lateinit var drawerLayout:DrawerLayout

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        //supportActionBar?.hide()



        navigationDrawer=findViewById(R.id.mobile_navigation)

        drawerLayout=findViewById(R.id.drawer_layout)

        drawerOpen.setOnClickListener {

            drawerLayout.openDrawer(GravityCompat.START)

        }



    }
}