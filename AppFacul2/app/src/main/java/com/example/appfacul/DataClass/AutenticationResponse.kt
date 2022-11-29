package com.example.appfacul.DataClass


data class AutenticationResponse(
    val login:String,
    val dialect:String,
    val email:String,
    val username:String,
    val authenticated:Boolean,
    val sessionCookies: String,
)
