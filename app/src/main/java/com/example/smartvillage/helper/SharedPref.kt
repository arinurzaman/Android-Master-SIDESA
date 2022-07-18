package com.example.smartvillage.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.smartvillage.model.User
import com.google.gson.Gson

class SharedPref (activity: Activity) {
    val login = "login"
    val nama = "nama"
    val nik = "nik"
    val email = "email"

    val user = "user"

    val mypref = "MAIN_PRF"
    val sp: SharedPreferences

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean) {
        sp.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean {
        return sp.getBoolean(login, false)
    }

    fun getUser(): User? {
        val data:String = sp.getString(user, null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }

    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        sp.edit().putString(user, data).apply()
    }
}