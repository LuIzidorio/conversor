package com.lucas.izidorio.conversor.presenter

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.lucas.izidorio.conversor.model.retrofit.provider.ServiceProvider
import com.lucas.izidorio.conversor.model.retrofit.service.ApiService
import com.lucas.izidorio.conversor.model.roomdb.CurrentUser
import com.lucas.izidorio.conversor.model.roomdb.User
import com.lucas.izidorio.conversor.view.ConversionActivity
import com.lucas.izidorio.conversor.view.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivityPresenter(private val view: View) {

    private val tag = "LoginActivityPresenter"

    enum class LoginType {
        NORMAL, FACEBOOK, TWITTER, GOOGLE
    }

    fun login(type: LoginType, source: Activity) {
        val intent = Intent(source, ConversionActivity::class.java)
        var auth = false
        when (type) {
            LoginType.NORMAL -> {
                val user = view.getUserFieldValue()
                val password = view.getPasswordFieldValue()

                auth = true
            }
            LoginType.FACEBOOK -> { }
            LoginType.TWITTER -> { }
            LoginType.GOOGLE -> { }
        }

        if (auth) view.startNextActivity(intent)
    }

    interface View {
        fun startNextActivity(i: Intent)
        fun getUserFieldValue(): String
        fun getPasswordFieldValue(): String
    }
}