package com.example.appfacul.StartNewActivity

import android.content.Context
import android.content.Intent
import com.example.appfacul.MenuPrincipal

class StartNewActivity(context: Context) {
    private var internalContext = context

    fun InitializeActivity(activityClass:Class<MenuPrincipal>){
        val internalIntent = Intent(internalContext,activityClass)
        internalContext.startActivity(internalIntent)
    }
}