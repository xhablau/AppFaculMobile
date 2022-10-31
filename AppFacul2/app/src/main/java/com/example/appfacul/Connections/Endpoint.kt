package com.example.appfacul.Connections

import com.example.appfacul.DataClass.AutenticationResponse
import com.example.appfacul.DataClass.Classes
import com.example.appfacul.DataClass.ContextResponse
import com.example.appfacul.DataClass.GradesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.util.*

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
    ):Call<Array<Map<String,String>>>

    @GET("/getSelecao")
    fun getCurrentUserSelecao(
        @Header("CorporePrincipal") corporePrincipal: String,
        @Header(".ASPXAUTH") aspxauth: String,
        @Header("DefaultAlias")defaultAlias: String,
    ):Call<Boolean>

    @GET("/getClasses")
    fun getCurrentUserClasses(
        @Header("CorporePrincipal") corporePrincipal:String,
        @Header(".ASPXAUTH")aspxauth:String,
        @Header("DefaultAlias")defaultAlias:String,
        @Header("EduContextoAlunoResponsavelAPI") eduContextoAlunoResponsavel:String
    ):Call<Map<String,Array<Map<String,String>>>>

    @GET("/getFaltas")
    fun getCurrentUserFaltas(
        @Header("CorporePrincipal") corporePrincipal:String,
        @Header(".ASPXAUTH")aspxauth:String,
        @Header("DefaultAlias")defaultAlias:String,
        @Header("EduContextoAlunoResponsavelAPI") eduContextoAlunoResponsavel:String
    ):Call<Map<String,Array<Map<String,String>>>>
}