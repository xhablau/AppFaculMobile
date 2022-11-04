package com.example.appfacul.Views

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.split
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import android.view.View
import android.widget.TextView
import com.example.appfacul.Connections.ConnectionControler
import com.example.appfacul.DataClass.Classes
import com.example.appfacul.DataClass.Notas
import com.example.appfacul.MainActivity
import com.example.appfacul.R
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class NotaActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)
        supportActionBar!!.hide()
        val colorStatusBar = R.color.dark_blue_unilins
        window.statusBarColor = resources.getColor(colorStatusBar)
        drawerLayout = findViewById(R.id.drawer_layout)
        ConnectionControler().GetCurrentUserGrades(this)


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

        val conversor = Gson()
        val quantNotas = sharedPreference.getInt("QuantidadeNotas", 0)

        val notas = arrayOfNulls<Notas>(quantNotas+1)



        for (i in 0..quantNotas){
            val nota = sharedPreference.getString("nota${i}","")
            val notaclass = conversor.fromJson(nota, Notas::class.java)

            notas[i]=notaclass

        }
     disciplinaNota()
     P1()
     P2()
     SUB()
     notaFinal()

    }


    // ================= Fun Nota Layout =================

    fun disciplinaNota(){
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val conversor = Gson()
        val quantNotas = sharedPreference.getInt("QuantidadeNotas", 0)
        val notas = arrayOfNulls<Notas>(quantNotas+1)

        for (i in 0..quantNotas){
            val nota = sharedPreference.getString("nota${i}","")
            val notaclass = conversor.fromJson(nota, Notas::class.java)
            notas[i]=notaclass
        }

        var aux = ""
        for (i in 0..quantNotas - 1){
            aux = aux + (notas[i]?.disciplina ?: "") + "\n" + "\n"
        }

        val disciplinaNota = findViewById<TextView>(R.id.disciplinaNota)
        disciplinaNota.setText(aux)
    }

    fun P1(){
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val conversor = Gson()
        val quantNotas = sharedPreference.getInt("QuantidadeNotas", 0)
        val notas = arrayOfNulls<Notas>(quantNotas+1)
        for (i in 0..quantNotas){
            val nota = sharedPreference.getString("nota${i}","")
            val notaclass = conversor.fromJson(nota, Notas::class.java)
            notas[i]=notaclass
        }
        var aux = ""
        for (i in 0..quantNotas - 1){
            lateinit var valorNotaP1: Array<String>
            valorNotaP1 = (notas[i]?.p1 ?: "0.00").split("<",">").toTypedArray()

            val quantSplitNota = valorNotaP1.size
            if (quantSplitNota > 2){
                aux = aux + valorNotaP1[2] + "\n" + "\n"

            } else {
                aux = aux + valorNotaP1[0] + "\n" + "\n"
            }
        }

        val p1Nota = findViewById<TextView>(R.id.p1Nota)
        p1Nota.setText(aux)
    }

    fun P2(){
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val conversor = Gson()
        val quantNotas = sharedPreference.getInt("QuantidadeNotas", 0)
        val notas = arrayOfNulls<Notas>(quantNotas+1)

        for (i in 0..quantNotas){
            val nota = sharedPreference.getString("nota${i}","")
            val notaclass = conversor.fromJson(nota, Notas::class.java)
            notas[i]=notaclass
        }
        var aux = ""
        for (i in 0..quantNotas - 1){
            lateinit var valorNotaP2: Array<String>
            valorNotaP2 = (notas[i]?.p2 ?: "0.00").split("<",">").toTypedArray()

            val quantSplitNota = valorNotaP2.size
            if (quantSplitNota > 2){
                aux = aux + valorNotaP2[2] + "\n" + "\n"

            } else {
                aux = aux + valorNotaP2[0] + "\n" + "\n"
            }
        }
        val p2Nota = findViewById<TextView>(R.id.p2Nota)
        p2Nota.setText(aux)
    }

    fun SUB(){
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val conversor = Gson()
        val quantNotas = sharedPreference.getInt("QuantidadeNotas", 0)
        val notas = arrayOfNulls<Notas>(quantNotas+1)

        for (i in 0..quantNotas){
            val nota = sharedPreference.getString("nota${i}","")
            val notaclass = conversor.fromJson(nota, Notas::class.java)
            notas[i]=notaclass
        }
        var aux = ""
        for (i in 0..quantNotas - 1){
            lateinit var valorNotaSub: Array<String>
            valorNotaSub = (notas[i]?.sub ?: "0.00").split("<",">").toTypedArray()

            val quantSplitNota = valorNotaSub.size
            if (quantSplitNota > 2){
                aux = aux + valorNotaSub[2] + "\n" + "\n"

            } else {
                aux = aux + valorNotaSub[0] + "\n" + "\n"
            }
        }
        val subNota = findViewById<TextView>(R.id.subNota)
        subNota.setText(aux)
    }

    fun notaFinal(){
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val conversor = Gson()
        val quantNotas = sharedPreference.getInt("QuantidadeNotas", 0)
        val notas = arrayOfNulls<Notas>(quantNotas+1)

        for (i in 0..quantNotas){
            val nota = sharedPreference.getString("nota${i}","")
            val notaclass = conversor.fromJson(nota, Notas::class.java)
            notas[i]=notaclass
        }
        var aux = ""
        for (i in 0..quantNotas - 1){
            lateinit var valorNotaFinal: Array<String>
            valorNotaFinal = (notas[i]?.NotaFinal ?: "0.00").split("<",">").toTypedArray()

            val quantSplitNota = valorNotaFinal.size
            if (quantSplitNota > 2){
                aux = aux + valorNotaFinal[2] + "\n" + "\n"

            } else {
                aux = aux + valorNotaFinal[0] + "\n" + "\n"
            }
        }
        val notaFinal = findViewById<TextView>(R.id.notaFinalNota)
        notaFinal.setText(aux)
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

    fun Clickhome(View: View?) {
        redirecActivity(this, HomeActivity::class.java)
    }

    fun clickProfile(view: View?) {
        redirecActivity(this, ProfileActivity::class.java)
    }

    fun clickNotification(view: View?) {
        redirecActivity(this, NotificationActivity::class.java)
    }

    fun clickNota(view: View?) {
        recreate()
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
            this.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE).edit().clear().apply()
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("EXIT", true)
            startActivity(intent)
        }
        builder.setNegativeButton(
            "Não"
        ) { dialogInterface, i -> dialogInterface.dismiss() }
        builder.show()
    }

    override fun onPause() {
        super.onPause()
        closeDrawer(drawerLayout)
    }

    fun redirecActivity(activity: Activity, Class: Class<*>?) {
        val intent = Intent(activity, Class)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }
}