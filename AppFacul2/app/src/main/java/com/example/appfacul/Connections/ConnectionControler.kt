package com.example.appfacul.Connections

import android.content.Context
import android.widget.Toast
import com.example.appfacul.Constants.Constants
import com.example.appfacul.DataClass.AutenticationResponse
import com.example.appfacul.DataClass.ContextResponse
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
                        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()

                        editor.putString("login",result.login)
                        editor.putString("username",result.username)
                        editor.putString("email",result.email)

                        editor.putString("CorporePrincipal",response.headers().get("CorporePrincipal"))
                        editor.putString(".ASPXAUTH",response.headers().get(".ASPXAUTH"))
                        editor.putString("DefaultAlias",response.headers().get("DefaultAlias"))

                        editor.commit()

                        StartNewActivity(context).InitializeActivityMenuPrincipal(MenuPrincipal::class.java)

                    }else{
                        Toast.makeText(context,"Usuário ou senha inválido!", Toast.LENGTH_SHORT).show()
                        println("Invalid credentials")
                    }
                }
                override fun onFailure(call: Call<AutenticationResponse>, t: Throwable) {
                    Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                    println("error")
                }
            })
            return true
        }catch (exception:Exception){
            println(exception)
            return false
        }
    }
    fun GetCurrentUserContext(context: Context){
        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

        val serverUrl = Constants.serverUrl
        val retrofitClient = NetworkUtils.getRetrofitInstance(serverUrl)
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val corporePrincipal = sharedPreference.getString("CorporePrincipal","")?:""
        val aspxAuth = sharedPreference.getString(".ASPXAUTH","")?:""
        val defaultAlias = sharedPreference.getString("DefaultAlias","")?:""

        val callback = endpoint.getCurrentUserContext(corporePrincipal,aspxAuth,defaultAlias)
        callback.enqueue(object : retrofit2.Callback<ContextResponse>{
            override fun onResponse(call: Call<ContextResponse>, response: Response<ContextResponse>) {
                val responseBody:ContextResponse?=response.body()
                val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()

                editor.putString("FotoAluno",responseBody?.FOTOALUNO)
                editor.putString("NomeCurso",responseBody?.NOMECURSO)
                editor.commit()


            }

            override fun onFailure(call: Call<ContextResponse>, t: Throwable) {
                println("error")
            }
        })

    }
}