package com.example.appfacul.LoginControler

import android.content.Context
import com.example.appfacul.Connections.ConnectionControler

class LoginControler {
    fun FormLoginControler(user:String,pass:String,context: Context){
        ConnectionControler().AutenticationConnection(user,pass,context)
    }
}