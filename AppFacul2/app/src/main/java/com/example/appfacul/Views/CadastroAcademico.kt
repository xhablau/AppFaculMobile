package com.example.appfacul.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appfacul.Connections.ConnectionControler
import com.example.appfacul.R

class CadastroAcademico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_academico)
        supportActionBar?.hide()
        //ConnectionControler().GetCurrentUserContext(this)
        //ConnectionControler().GetCurrentUserGrades(this)
    }
}