package com.example.appfacul.Connections

import com.example.appfacul.DataClass.AutenticationResponse
import retrofit2.Call
import retrofit2.Response

class ConnectionControler {

    //returns true if the user and password are valid
    fun AutenticationConnection(user:String, password:String):Boolean{
        try{
            val serverurl = "http://192.168.15.6:8080/"
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
                        println("Valid user and password")
                    }else{
                        println("Invalid credentials")
                    }
                }
                override fun onFailure(call: Call<AutenticationResponse>, t: Throwable) {
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