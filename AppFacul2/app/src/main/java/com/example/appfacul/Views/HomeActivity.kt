package com.example.appfacul.Views

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appfacul.Connections.ConnectionControler
import com.example.appfacul.DataClass.Classes
import com.example.appfacul.R
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


class HomeActivity : AppCompatActivity() {

    var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        val colorStatusBar = R.color.dark_blue_unilins
        window.statusBarColor = resources.getColor(colorStatusBar)
        drawerLayout = findViewById(R.id.drawer)
        ConnectionControler().GetCurrentUserSelecao(this)
        ConnectionControler().GetCurrentUserContext(this)
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



        val dia = aulaHoje()
        timeBackground(this,dia)
    }

    // dia 0:domingo
    // dia 1:segunda
    // dia 2:terca
    // dia 3 :quarta
    // dia 4:quinta
    // dia5:sexta

    // ================= Fun Background Time =================
    fun timeBackground(context: Context,dia:Int){
        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val sdf = SimpleDateFormat("HH:mm")
        val hora = Calendar.getInstance().time // Ou qualquer outra forma que tem
        val dataFormatada = sdf.format(hora)
        val firtClassVal = findViewById<TextView>(R.id.firstClass)
        val firtsPeriodVal = findViewById<TextView>(R.id.firtsPeriod)
        val secondClassVal = findViewById<TextView>(R.id.secondClass)
        val secondPeriodVal = findViewById<TextView>(R.id.secondPeriod)
        val conversor = Gson()

        if(dia==0){
            val aula1 = sharedPreference.getString("aula0","")
            val aula2 = sharedPreference.getString("aula6","")

            val aula1Class = conversor.fromJson(aula1, Classes::class.java)
            val aula2Class = conversor.fromJson(aula2, Classes::class.java)
            if(aula1Class==null){
                firtClassVal.text = "Não há aula"
                secondClassVal.text = "Não há aula"
            }else{
                val aula1nome = aula1Class.nome
                val aula2nome = aula2Class.nome

                val aula1Sala = aula1Class.sala.split("-")[1]
                val aula2Sala = aula2Class.sala.split("-")[1]

                firtClassVal.text = "${aula1nome}-${aula1Sala}"
                secondClassVal.text = "${aula2nome}-${aula2Sala}"
            }
        }
        if(dia==1){
            val aula1 = sharedPreference.getString("aula1","")
            val aula2 = sharedPreference.getString("aula7","")

            val aula1Class = conversor.fromJson(aula1, Classes::class.java)
            val aula2Class = conversor.fromJson(aula2, Classes::class.java)

            if(aula1Class==null){
                firtClassVal.text = "Não há aula"
                secondClassVal.text = "Não há aula"
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        finish()
                        startActivity(intent)
                    }
                }, 2000)
            }else{
                val aula1nome = aula1Class.nome
                val aula2nome = aula2Class.nome

                val aula1Sala = aula1Class.sala.split("-")[1]
                val aula2Sala = aula2Class.sala.split("-")[1]

                firtClassVal.text = "${aula1nome}-${aula1Sala}"
                secondClassVal.text = "${aula2nome}-${aula2Sala}"
            }
        }
        if(dia==2){
            val aula1 = sharedPreference.getString("aula2","")
            val aula2 = sharedPreference.getString("aula8","")

            val aula1Class = conversor.fromJson(aula1, Classes::class.java)
            val aula2Class = conversor.fromJson(aula2, Classes::class.java)

            if(aula1Class==null){
                firtClassVal.text = "Não há aula"
                secondClassVal.text = "Não há aula"
            }else{
                val aula1nome = aula1Class.nome
                val aula2nome = aula2Class.nome

                val aula1Sala = aula1Class.sala.split("-")[1]
                val aula2Sala = aula2Class.sala.split("-")[1]

                firtClassVal.text = "${aula1nome}-${aula1Sala}"
                secondClassVal.text = "${aula2nome}-${aula2Sala}"
            }
        }
        if(dia==3){
            val aula1 = sharedPreference.getString("aula3","")
            val aula2 = sharedPreference.getString("aula9","")

            val aula1Class = conversor.fromJson(aula1, Classes::class.java)
            val aula2Class = conversor.fromJson(aula2, Classes::class.java)

            if(aula1Class==null){
                firtClassVal.text = "Não há aula"
                secondClassVal.text = "Não há aula"
            }else{
                val aula1nome = aula1Class.nome
                val aula2nome = aula2Class.nome

                val aula1Sala = aula1Class.sala.split("-")[1]
                val aula2Sala = aula2Class.sala.split("-")[1]

                firtClassVal.text = "${aula1nome}-${aula1Sala}"
                secondClassVal.text = "${aula2nome}-${aula2Sala}"
            }
        }
        if(dia==4){
            val aula1 = sharedPreference.getString("aula4","")
            val aula2 = sharedPreference.getString("aula10","")

            val aula1Class = conversor.fromJson(aula1, Classes::class.java)
            val aula2Class = conversor.fromJson(aula2, Classes::class.java)

            if(aula1Class==null){
                firtClassVal.text = "Não há aula"
                secondClassVal.text = "Não há aula"
            }else{
                val aula1nome = aula1Class.nome
                val aula2nome = aula2Class.nome

                val aula1Sala = aula1Class.sala.split("-")[1]
                val aula2Sala = aula2Class.sala.split("-")[1]

                firtClassVal.text = "${aula1nome}-${aula1Sala}"
                secondClassVal.text = "${aula2nome}-${aula2Sala}"
            }
        }
        if(dia==5){
            val aula1 = sharedPreference.getString("aula5","")
            val aula2 = sharedPreference.getString("aula11","")

            val aula1Class = conversor.fromJson(aula1, Classes::class.java)
            val aula2Class = conversor.fromJson(aula2, Classes::class.java)

            if(aula1Class==null){
                firtClassVal.text = "Não há aula"
                secondClassVal.text = "Não há aula"
            }else{
                val aula1nome = aula1Class.nome
                val aula2nome = aula2Class.nome

                val aula1Sala = aula1Class.sala.split("-")[1]
                val aula2Sala = aula2Class.sala.split("-")[1]

                firtClassVal.text = "${aula1nome}-${aula1Sala}"
                secondClassVal.text = "${aula2nome}-${aula2Sala}"
            }
        }

        if (dataFormatada.toString() >= "19:00" && dataFormatada.toString() <= "20:40"){
            firtClassVal.setBackgroundResource(R.color.red)
            firtsPeriodVal.setBackgroundResource(R.color.red)
        } else {
            firtClassVal.setBackgroundResource(R.color.grey)
            firtsPeriodVal.setBackgroundResource(R.color.grey)
        }


        if (dataFormatada.toString()  >= "21:00" && dataFormatada.toString() <= "22:40"){
            secondClassVal.setBackgroundResource(R.color.red)
            secondPeriodVal.setBackgroundResource(R.color.red)
        } else {
            secondClassVal.setBackgroundResource(R.color.grey)
            secondPeriodVal.setBackgroundResource(R.color.grey)
        }
    }

    // ================= Fun Date Today =================

    fun aulaHoje(): Int {
        val c = Calendar.getInstance()
        val day = c[Calendar.DAY_OF_WEEK]
        if (day == 1){
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Domingo"
            )
            return 0
        } else if (day == 2) {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Segunda-feira"
            )
            return 1
        } else if (day == 3) {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Terça-feira"
            )
            return 2
        } else if (day == 4) {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Quarta-feira"
            )
            return 3
        } else if (day == 5){
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Quinta-feira"
            )
            return 4
        } else if (day == 6){
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Sexta-feira"
            )
            return 5
        } else {
            val today = findViewById<TextView>(R.id.dataHoje)
            today.text = getString(
                R.string.withoutSpace,
                "Sábado"
            )
            return 6
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
            this.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE).edit().clear().apply()
            finishAffinity()
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