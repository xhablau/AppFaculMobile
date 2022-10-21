package com.example.appfacul.Connections

import com.example.appfacul.DataClass.AutenticationResponse
import com.example.appfacul.DataClass.ContextResponse
import com.example.appfacul.DataClass.GradesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Endpoint {
    @GET("/getCurrentUser/{user}/{password}")
    fun getCurrentUser(@Path("user")user:String,@Path("password")password:String):Call<AutenticationResponse>
    @GET("/getCurrentUser/context")
    fun getCurrentUserContext(
        @Header("CorporePrincipal") corporePrincipal:String,
        @Header(".ASPXAUTH")aspxauth:String,
        @Header("DefaultAlias")defaultAlias:String
    ):Call<ContextResponse>
    @GET("/getGrades")
    fun getCurrentUserGrades(
        @Header("CorporePrincipal") corporePrincipal:String,
        @Header(".ASPXAUTH")aspxauth:String,
        @Header("DefaultAlias")defaultAlias:String,
        @Header("EduContextoAlunoResponsavelAPI") eduContextoAlunoResponsavel:String
    ):Call<Boolean>
    @GET("/getSelecao")
    fun getCurrentUserSelecao(
        @Header("CorporePrincipal") corporePrincipal: String,
        @Header(".ASPXAUTH") aspxauth: String,
        @Header("DefaultAlias")defaultAlias: String,
    ):Call<Boolean>
}