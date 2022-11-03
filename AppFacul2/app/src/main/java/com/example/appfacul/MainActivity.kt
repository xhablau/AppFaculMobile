package com.example.appfacul

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appfacul.LoginControler.LoginControler
import com.example.appfacul.Views.HomeActivity
import com.example.appfacul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference.edit().clear().apply()
        supportActionBar!!.hide()
        val colorStatusBar = R.color.dark_blue_unilins
        window.statusBarColor = resources.getColor(colorStatusBar)

        binding.btnAccess.setOnClickListener {
            val idUser = binding.idUser.text.toString()
            val passwordUser = binding.passwordUser.text.toString()

            if (idUser.isEmpty() || passwordUser.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                LoginControler().FormLoginControler(idUser,passwordUser,this)
                //val segundaTela = Intent(this, HomeActivity::class.java)
                //startActivity(segundaTela)
            }
        }
    }
}