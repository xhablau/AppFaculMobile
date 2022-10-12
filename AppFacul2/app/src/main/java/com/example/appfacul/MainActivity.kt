package com.example.appfacul

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.appfacul.LoginControler.LoginControler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoginControler().FormLoginControler()
    }
}