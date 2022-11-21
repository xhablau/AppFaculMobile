package com.example.appfacul.DataClass


data class AutenticationResponse(
    val login:String,
    val dialect:String,
    val email:String,
    val username:String,
    val isAuthenticated:Boolean,
    val sessionCookies: String,
)
