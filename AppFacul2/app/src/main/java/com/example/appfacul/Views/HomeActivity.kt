package com.example.appfacul.Views

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.appfacul.Connections.ConnectionControler
import com.example.appfacul.R
import com.example.appfacul.Shared.SetNameAndDate
import java.text.SimpleDateFormat
import java.util.*


class HomeActivity : AppCompatActivity() {

    var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        drawerLayout = findViewById(R.id.drawer)
        ConnectionControler().GetCurrentUserSelecao(this)
        ConnectionControler().GetCurrentUserClasses(this)

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



        val sdf = SimpleDateFormat("HH:mm")
        val hora = Calendar.getInstance().time // Ou qualquer outra forma que tem
        val dataFormatada = sdf.format(hora)



        timeBackground()
        aulaHoje()
    }


    // ================= Fun Background Time =================
    fun timeBackground(){
        val sdf = SimpleDateFormat("HH:mm")
        val hora = Calendar.getInstance().time // Ou qualquer outra forma que tem
        val dataFormatada = sdf.format(hora)
        val firtClassVal = findViewById<TextView>(R.id.firstClass)
        val firtsPeriodVal = findViewById<TextView>(R.id.firtsPeriod)
        val secondClassVal = findViewById<TextView>(R.id.secondClass)
        val secondPeriodVal = findViewById<TextView>(R.id.secondPeriod)

        if (dataFormatada.toString() >= "19:00" && dataFormatada.toString() <= "20:40"){
            firtClassVal.setBackgroundResource(R.color.red)
            firtsPeriodVal.setBackgroundResource(R.color.red)
        } else {
            firtClassVal.setBackgroundResource(R.color.purple_500)
            firtsPeriodVal.setBackgroundResource(R.color.purple_500)
        }


        if (dataFormatada.toString()  >= "21:00" && dataFormatada.toString() <= "22:40"){
            secondClassVal.setBackgroundResource(R.color.red)
            secondPeriodVal.setBackgroundResource(R.color.red)
        } else {
            secondClassVal.setBackgroundResource(R.color.purple_500)
            secondPeriodVal.setBackgroundResource(R.color.purple_500)
        }
    }

    // ================= Fun Date Today =================

    fun aulaHoje() {
        val c = Calendar.getInstance()
        val day = c[Calendar.DAY_OF_WEEK]
        if (day === 1){
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Domingo"
            )
        } else if (day === 2) {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Segunda-feira"
            )
        } else if (day === 3) {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Terça-feira"
            )
        } else if (day === 4) {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Quarta-feira"
            )
        } else if (day === 5){
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Quinta-feira"
            )
        } else if (day === 6){
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Sexta-feira"
            )
        } else {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Sábado"
            )
        }


    }

    // ================= Fun Menu =================
    fun ClickMenu(view: View?) {
        openDrawer(drawerLayout)
    }

    fun openDrawer(drawerLayout: DrawerLayout?) {
        drawerLayout!!.openDrawer(GravityCompat.START)
    }

    fun ClickLogo(view: View?) {
        closeDrawer(drawerLayout)
    }

    fun closeDrawer(drawerLayout: DrawerLayout?) {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun Clickhome(view: View?) {
        recreate()
    }

    fun clickProfile(view: View?) {
        redirecActivity(this, ProfileActivity::class.java)
    }

    fun clickNotification(view: View?) {
        redirecActivity(this, NotificationActivity::class.java)
    }


    fun clickNota(view: View?) {
        redirecActivity(this, NotaActivity::class.java)
    }


    fun clickFalta(view: View?) {
        redirecActivity(this, FaltaActivity::class.java)
    }

    fun clickCampus(view: View?) {
        redirecActivity(this, CampusActivity::class.java)
    }

    fun clickLogOut(view: View?) {
        logout(this)
    }

    fun logout(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Logout")
        builder.setMessage("Tem certeza que quer sair?")
        builder.setPositiveButton(
            "Sim"
        ) { dialogInterface, i ->
            activity.finishActivity(0)
            System.exit(0)
        }
        builder.setNegativeButton(
            "Não"
        ) { dialogInterface, i -> dialogInterface.dismiss() }
        builder.show()
    }

    fun redirecActivity(activity: Activity, Class: Class<*>?) {
        val intent = Intent(activity, Class)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        closeDrawer(drawerLayout)
    }
}