package com.example.appfacul.Views

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.appfacul.GlobalClass
import com.example.appfacul.R
import com.example.appfacul.StartNewActivity.StartNewActivity
import com.example.appfacul.databinding.ActivityMenuFinalBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityMenuFinalBinding

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        supportActionBar?.hide()

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val username = sharedPreference.getString("username","")

        val textViewName = findViewById<TextView>(R.id.menuPrincipalHeader)
        val textViewTime = findViewById<TextView>(R.id.menuPrincipalHeaderTime)
        textViewName.text = getString(R.string.welcome_messages,
            username?.split(" ")?.get(0)?.lowercase()?.capitalize() ?:"")

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH) //or use getDateInstance()
        textViewTime.text=formatter.format(date)

        val buttonCadastroAcademico:Button = findViewById(R.id.buttonCadastroAcademico)
        buttonCadastroAcademico.setOnClickListener{
            StartNewActivity(this).InitializeActivityCadastroAcademico(CadastroAcademico::class.java)
        }
        
    }
}