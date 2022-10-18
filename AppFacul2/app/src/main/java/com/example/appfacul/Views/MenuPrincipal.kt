package com.example.appfacul.Views

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.appfacul.GlobalClass
import com.example.appfacul.R
import com.example.appfacul.StartNewActivity.StartNewActivity
import java.text.SimpleDateFormat
import java.util.*


class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        supportActionBar?.hide()

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val username = sharedPreference.getString("username","")






    }
}