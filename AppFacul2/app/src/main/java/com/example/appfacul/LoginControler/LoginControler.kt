package com.example.appfacul.LoginControler

import com.example.appfacul.Connections.ConnectionControler

class LoginControler {
    fun FormLoginControler(){
        val inputTextLogin ="206974"
        val inputTextPassword = "Paulinho_123321"
        val teste = ConnectionControler().AutenticationConnection(inputTextLogin,inputTextPassword)
        println(teste)
    }
}