package com.anugrahdev.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anugrahdev.mvvmsampleapp.R
import com.anugrahdev.mvvmsampleapp.data.db.entities.User
import com.anugrahdev.mvvmsampleapp.databinding.ActivityLoginBinding
import com.anugrahdev.mvvmsampleapp.databinding.ActivitySignupBinding
import com.anugrahdev.mvvmsampleapp.ui.home.HomeActivity
import com.anugrahdev.mvvmsampleapp.utils.hide
import com.anugrahdev.mvvmsampleapp.utils.show
import com.anugrahdev.mvvmsampleapp.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener=this

        viewModel.getLoggedInUser().observe(this, Observer {
            if (it!=null){
                Intent(this, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
//        toast("Login Started")
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//        root_layout.snackbar("${user.name} is logged in")
    }

    override fun onFailure(message:String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
