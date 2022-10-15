package com.example.appfacul

import android.app.Application

class GlobalClass:Application() {
    var globalUserName = "test"
    var email = ""
    var id = ""
    var responseHeaders = okhttp3.Headers.Builder().build()
}