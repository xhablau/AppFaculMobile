package com.example.appfacul.DataClass

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class Classes(
    @SerializedName("NOME")
    val nome:String,
    @SerializedName("SALA")
    val sala:String,
    @SerializedName("HORAINICIAL")
    val horainicial:String,
    @SerializedName("HORAFINAL")
    val horafinal:String,
)
