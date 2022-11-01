package com.example.appfacul.Views

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.appfacul.R
import android.util.Base64
import com.example.appfacul.MainActivity
import java.text.SimpleDateFormat
import java.util.*


class ProfileActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null
    private lateinit var imgUserProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar!!.hide()
        val colorStatusBar = R.color.dark_blue_unilins
        window.statusBarColor = resources.getColor(colorStatusBar)
        drawerLayout = findViewById(R.id.drawer_layout)




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




        // ============ Image Profile ============
        val sharedPreferenceImg = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val userImgProfile = sharedPreferenceImg.getString("FotoAluno", "")
        val imgUserProfile = findViewById<ImageView>(R.id.imgUserProfile)

        val imageBytes = Base64.decode(userImgProfile, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        imgUserProfile.setImageBitmap(decodedImage)


        // ============ Name Profile ============
        val sharedPreferenceName = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val userNameProfile = sharedPreferenceName.getString("username", "")
        val textViewNameProfile = findViewById<TextView>(R.id.nameUserProfile)
        textViewNameProfile.text = getString(
            R.string.withoutSpace,
            userNameProfile
        )


        // ============ Email Profile ============
        val sharedPreferenceEmail = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val userEmailProfile = sharedPreferenceEmail.getString("email", "")
        val textViewEmailProfile = findViewById<TextView>(R.id.emailUserProfile)
        textViewEmailProfile.text = getString(
            R.string.withoutSpace,
            userEmailProfile?.split(" ")?.get(0)?.capitalize() ?: ""
        )


        // ============ Curso Profile ============
        val sharedPreferenceCurso = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val userCursoProfile = sharedPreferenceCurso.getString("NomeCurso", "")
        val textViewCursoProfile = findViewById<TextView>(R.id.cursoUserProfile)
        textViewCursoProfile.text = getString(
            R.string.withoutSpace,
            userCursoProfile ?: ""
        )

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
        recreate()
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

    fun clickAgradecimentos(view: View?) {
        redirecActivity(this, AgradecimentoActivity::class.java)
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

