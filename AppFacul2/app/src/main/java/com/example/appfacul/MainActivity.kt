package com.example.appfacul

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appfacul.LoginControler.LoginControler
import com.example.appfacul.Views.HomeActivity
import com.example.appfacul.Views.MenuPrincipal
import com.example.appfacul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        binding.btnAccess.setOnClickListener {
            val idUser = binding.idUser.text.toString()
            val passwordUser = binding.passwordUser.text.toString()

            if (idUser.isEmpty() || passwordUser.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val segundaTela = Intent(this, HomeActivity::class.java)
                startActivity(segundaTela)
            }
        }
    }
}