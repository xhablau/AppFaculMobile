package com.example.appfacul.Views

import android.content.Intent
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
import com.example.appfacul.databinding.ActivityMenuPrincipalBinding
import java.text.SimpleDateFormat

import java.util.*


class MenuPrincipal : AppCompatActivity() {


    lateinit var drawerOpen:ImageView

    lateinit var navigationDrawer:NavigationView

    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        supportActionBar?.hide()


        drawerOpen=findViewById(R.id.drawer_open)

        navigationDrawer=findViewById(R.id.navigation_drawer)

        drawerLayout=findViewById(R.id.drawer_layout)

        drawerOpen.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val navController:NavController=Navigation.findNavController(this,R.id.fragment)

        NavigationUI.setupWithNavController(navigationDrawer,navController)


        val globalClass= GlobalClass()
        val textViewName = findViewById<TextView>(R.id.menuPrincipalHeader)
        val textViewTime = findViewById<TextView>(R.id.menuPrincipalHeaderTime)
        textViewName.text = getString(R.string.welcome_messages,globalClass.globalUserName)

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH) //or use getDateInstance()
        textViewTime.text=formatter.format(date)

        val buttonCadastroAcademico:Button = findViewById(R.id.buttonCadastroAcademico)
        buttonCadastroAcademico.setOnClickListener{
            StartNewActivity(this).InitializeActivityCadastroAcademico(CadastroAcademico::class.java)
        }
        
    }
}