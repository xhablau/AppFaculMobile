package com.example.appfacul.Shared

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.appfacul.R
import java.text.SimpleDateFormat
import java.util.*

class SetNameAndDate: AppCompatActivity() {
    fun setNameAndDate(context: Context){
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val username = sharedPreference.getString("username", "")
        val textViewTime = findViewById<TextView>(R.id.dateToday)
        val textViewName = findViewById<TextView>(R.id.nameUser)
        textViewName.text = getString(
            R.string.welcome_messages,
            username?.split(" ")?.get(0)?.lowercase()?.capitalize() ?: ""
        )
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) //or use getDateInstance()
        textViewTime.text = formatter.format(date)
    }
}