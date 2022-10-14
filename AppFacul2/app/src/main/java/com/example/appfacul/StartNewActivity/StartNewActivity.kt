package com.example.appfacul.StartNewActivity

import android.content.Context
import android.content.Intent
import com.example.appfacul.Views.CadastroAcademico
import com.example.appfacul.Views.MenuPrincipal

class StartNewActivity(context: Context) {
    private var internalContext = context


    fun InitializeActivityMenuPrincipal(activityClass:Class<MenuPrincipal>){
        val internalIntent = Intent(internalContext,activityClass)
        internalContext.startActivity(internalIntent)
    }
    fun InitializeActivityCadastroAcademico(activityClass: Class<CadastroAcademico>){
        val internalIntent = Intent(internalContext,activityClass)
        internalContext.startActivity(internalIntent)
    }
}