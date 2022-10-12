package com.example.appfacul.Connections

import com.example.appfacul.DataClass.AutenticationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("/getCurrentUser/{user}/{password}")
    fun getCurrentUser(@Path("user")user:String,@Path("password")password:String):Call<AutenticationResponse>
}