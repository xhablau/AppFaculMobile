package com.example.appfacul.Views

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appfacul.MainActivity
import com.example.appfacul.R
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CampusActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null

    private lateinit var downloadImage: Button
    private val STORAGE_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campus)
        supportActionBar!!.hide()
        val colorStatusBar = R.color.dark_blue_unilins
        window.statusBarColor = resources.getColor(colorStatusBar)

        drawerLayout = findViewById(R.id.drawer_layout)
        downloadImage = findViewById(R.id.downloadImg)

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


        val url =
            "https://unilins.edu.br/wp-content/uploads/2022/04/mapa_campus-04-22-1-scaled-1536x921.jpg"

        downloadImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                DownloadImage("Campus Unilins", url)
            } else {
                askPermission()
            }
        }
    }

    // ================= Fun Download Img  e Permissão =================
    private fun askPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE
        )
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray

    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val url =
                    "https://unilins.edu.br/wp-content/uploads/2022/04/mapa_campus-04-22-1-scaled-1536x921.jpg"
                DownloadImage("Imagem Campus Unilins", url)
            } else {
                Toast.makeText(
                    this,
                    "Forneça as permissões necessárias",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun DownloadImage(fileName: String, imageURL: String?) {
        try {
            var downloadManager: DownloadManager? = null
            downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val downloaduri = Uri.parse(imageURL)
            val request = DownloadManager.Request(downloaduri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(fileName)
                .setMimeType("CampusUnilins/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + fileName + ".jpg"
                )
            downloadManager.enqueue(request)
            Toast.makeText(this, "Download Concluido", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Falha no Download", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUEST_CODE = 100
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
        redirecActivity(this, NotaActivity::class.java)
    }

    fun clickFalta(view: View?) {
        redirecActivity(this, FaltaActivity::class.java)
    }

    fun clickCampus(view: View?) {

        recreate()
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