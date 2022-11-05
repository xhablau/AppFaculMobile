package com.example.appfacul.DataClass

import com.google.gson.annotations.SerializedName

data class Faltas(
    @SerializedName("Disciplina")
    val disciplina:String,
    @SerializedName("4 - Faltas")
    val falta:String,
    @SerializedName("9 - Frequencia")
    val frequencia:String,
    @SerializedName("PERCENTUAL")
    val percentual:String,
    @SerializedName("SITUACAOFALTAS")
    val situacaoFaltas:String,
)
