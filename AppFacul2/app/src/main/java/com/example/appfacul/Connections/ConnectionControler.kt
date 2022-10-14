package com.example.appfacul.Connections

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.appfacul.Constants.Constants
import com.example.appfacul.DataClass.AutenticationResponse
import com.example.appfacul.GlobalClass
import com.example.appfacul.Views.MenuPrincipal
import com.example.appfacul.StartNewActivity.StartNewActivity
import retrofit2.Call
import retrofit2.Response

class ConnectionControler {

    //returns true if the user and password are valid
    fun AutenticationConnection(user:String, password:String,context:Context):Boolean{
        try{
            val serverurl = Constants.serverUrl
            val retrofitClient = NetworkUtils.getRetrofitInstance(serverurl)
            val endpoint = retrofitClient.create(Endpoint::class.java)
            val callback = endpoint.getCurrentUser(user, password)
            callback.enqueue(object : retrofit2.Callback<AutenticationResponse>{
                override fun onResponse(
                    call: Call<AutenticationResponse>,
                    response: Response<AutenticationResponse>
                ) {
                    val result: AutenticationResponse? = response.body()
                    if(result?.authenticated!!){
                        val globalClass = GlobalClass()
                        val menuPrincipal = Intent(context, MenuPrincipal::class.java)

                        globalClass.id=result.login
                        globalClass.globalUserName=result.username
                        globalClass.email=result.email

                        context.startActivity(menuPrincipal)
                    }else{
                        Toast.makeText(context,"Usuário ou senha inválido!", Toast.LENGTH_SHORT).show()
                        println("Invalid credentials")
                    }
                }
                override fun onFailure(call: Call<AutenticationResponse>, t: Throwable) {
                    Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                    StartNewActivity(context).InitializeActivityMenuPrincipal(MenuPrincipal::class.java)
                    println("error")
                }
            })
            return true
        }catch (exception:Exception){
            println(exception)
            return false
        }
    }
}