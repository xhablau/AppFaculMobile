package com.example.appfacul.Connections

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.appfacul.Constants.Constants
import com.example.appfacul.DataClass.AutenticationResponse
import com.example.appfacul.DataClass.ContextResponse
import com.example.appfacul.Views.HomeActivity
import com.google.gson.Gson
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

                        //StartNewActivity(context).InitializeActivityMenuPrincipal(MenuPrincipal::class.java)

                        val segundaTela = Intent(context, HomeActivity::class.java)
                        context.startActivity(segundaTela)

                    }else{
                        Toast.makeText(context,"Usuário ou senha inválido!", Toast.LENGTH_SHORT).show()
                        println("Invalid credentials")
                    }
                }
                override fun onFailure(call: Call<AutenticationResponse>, t: Throwable) {
                    Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                    println(t.message)
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
                Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                println("error")
            }
        })

    }
    fun GetCurrentUserGrades(context: Context){
        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

        val serverUrl = Constants.serverUrl
        val retrofitClient = NetworkUtils.getRetrofitInstance(serverUrl)
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val corporePrincipal = sharedPreference.getString("CorporePrincipal","")?:""
        val aspxAuth = sharedPreference.getString(".ASPXAUTH","")?:""
        val defaultAlias = sharedPreference.getString("DefaultAlias","")?:""
        val EduContextoAlunoResponsavelAPI = sharedPreference.getString("EduContextoAlunoResponsavelAPI","")?:""

        val callback = endpoint.getCurrentUserGrades(corporePrincipal,aspxAuth,defaultAlias,EduContextoAlunoResponsavelAPI)
        callback.enqueue(object : retrofit2.Callback<Array<Map<String,String>>>{
            override fun onResponse(
                call: Call<Array<Map<String, String>>>,
                response: Response<Array<Map<String, String>>>
            ) {
                val editor = sharedPreference.edit()
                val notas = response.body()
                notas?.size?.let { editor.putInt("QuantidadeNotas", it) }
                notas?.forEachIndexed { index, nota ->
                    val conversor = Gson()
                    editor.putString("nota${index}",conversor.toJson(nota))
                }
                editor.commit()
            }

            override fun onFailure(call: Call<Array<Map<String,String>>>, t: Throwable) {
                Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                println("Error")
            }
        })


    }
    fun GetCurrentUserSelecao(context: Context){
        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

        val serverUrl = Constants.serverUrl
        val retrofitClient = NetworkUtils.getRetrofitInstance(serverUrl)
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val corporePrincipal = sharedPreference.getString("CorporePrincipal","")?:""
        val aspxAuth = sharedPreference.getString(".ASPXAUTH","")?:""
        val defaultAlias = sharedPreference.getString("DefaultAlias","")?:""

        val callback = endpoint.getCurrentUserSelecao(corporePrincipal,aspxAuth,defaultAlias)
        callback.enqueue(object : retrofit2.Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val editor = sharedPreference.edit()
                editor.putString("EduContextoAlunoResponsavelAPI",response.headers().get("EduContextoAlunoResponsavelAPI"))
                editor.commit()
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                println(t.message)
            }
        })

    }
    fun GetCurrentUserClasses(context: Context){
        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val serverUrl = Constants.serverUrl
        val retrofitClient = NetworkUtils.getRetrofitInstance(serverUrl)
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val corporePrincipal = sharedPreference.getString("CorporePrincipal","")?:""
        val aspxAuth = sharedPreference.getString(".ASPXAUTH","")?:""
        val defaultAlias = sharedPreference.getString("DefaultAlias","")?:""
        val EduContextoAlunoResponsavelAPI = sharedPreference.getString("EduContextoAlunoResponsavelAPI","")?:""


        val callback = endpoint.getCurrentUserClasses(corporePrincipal,aspxAuth,defaultAlias,EduContextoAlunoResponsavelAPI);
        callback.enqueue(object:retrofit2.Callback<Array<String>>{
            override fun onResponse(call: Call<Array<String>>, response: Response<Array<String>>) {
                val response = response.body()
            }

            override fun onFailure(call: Call<Array<String>>, t: Throwable) {
                Toast.makeText(context,"Ocorreu um erro ao se comunicar com o servidor!", Toast.LENGTH_SHORT).show()
                println(t.message)
            }
        })

    }
}