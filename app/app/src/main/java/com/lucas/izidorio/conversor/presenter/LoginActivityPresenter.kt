package com.lucas.izidorio.conversor.presenter

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.lucas.izidorio.conversor.view.ConversionActivity
import com.lucas.izidorio.conversor.view.HistoryActivity

class LoginActivityPresenter(private val view: View) {

    private val tag = "LoginActivityPresenter"

    enum class LoginType {
        NORMAL, FACEBOOK, TWITTER, GOOGLE
    }

    fun login(type: LoginType, source: Activity) {
        val intent = Intent(source, ConversionActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        var auth = false
        
        when (type) {
            LoginType.NORMAL -> {
                val user = view.getUserFieldValue()
                val password = view.getPasswordFieldValue()

                // Autenticação no banco
                Log.i(tag, "$user | $password")

                auth = true
            }
            LoginType.FACEBOOK -> { Log.e(tag, "Login não implementado") }
            LoginType.TWITTER -> { Log.e(tag, "Login não implementado") }
            LoginType.GOOGLE -> { Log.e(tag, "Login não implementado") }
        }

        if (auth) view.startNextActivity(intent)
    }

    interface View {
        fun startNextActivity(i: Intent)
        fun getUserFieldValue(): String
        fun getPasswordFieldValue(): String
    }
}