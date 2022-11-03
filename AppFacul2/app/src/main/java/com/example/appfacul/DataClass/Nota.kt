package com.example.appfacul.DataClass

import com.google.gson.annotations.SerializedName

data class Nota(
    @SerializedName("NOME")
    val nome:String,
    @SerializedName("SALA")
    val sala:String
)
