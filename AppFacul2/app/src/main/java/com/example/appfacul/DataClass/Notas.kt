package com.example.appfacul.DataClass

import com.google.gson.annotations.SerializedName

data class Notas(
    @SerializedName("DISCIPLINA")
    val disciplina:String,
    @SerializedName("1 - P1")
    val p1:String,
    @SerializedName("1 - P2")
    val p2:String,
    @SerializedName("3 - Sub")
    val sub:String,
    @SerializedName("8 - MF")
    val NotaFinal:String,
)
