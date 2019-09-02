package com.lucas.izidorio.conversor.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.izidorio.conversor.R
import com.lucas.izidorio.conversor.presenter.LoginActivityPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityPresenter.View {

    private var presenter: LoginActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginActivityPresenter(this)

        loginButton.setOnClickListener {
            presenter!!.login(LoginActivityPresenter.LoginType.NORMAL, this)
        }

        loginWithFbButton.setOnClickListener {
            presenter!!.login(LoginActivityPresenter.LoginType.FACEBOOK, this)
        }

        loginWithTwitterButton.setOnClickListener {
            presenter!!.login(LoginActivityPresenter.LoginType.TWITTER, this)
        }

        loginWithGoogleButton.setOnClickListener {
            presenter!!.login(LoginActivityPresenter.LoginType.GOOGLE, this)
        }
    }

    override fun startNextActivity(i: Intent) {
        this.startActivity(i)
    }

    override fun getUserFieldValue(): String {
        return userInput.text.toString()
    }

    override fun getPasswordFieldValue(): String {
        return passwordInput.text.toString()
    }
}
