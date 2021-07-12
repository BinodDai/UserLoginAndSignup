 package com.binod.mvvmpractise.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.binod.mvvmpractise.R
import com.binod.mvvmpractise.data.network.Api
import com.binod.mvvmpractise.data.network.NetworkConnectionInterceptor
import com.binod.mvvmpractise.data.repo.UserRepository
import com.binod.mvvmpractise.databinding.ActivityLoginBinding
import com.binod.mvvmpractise.db.AppDatabase
import com.binod.mvvmpractise.db.entities.User
import com.binod.mvvmpractise.ui.home.HomeActivity
import com.binod.mvvmpractise.ui.util.hide
import com.binod.mvvmpractise.ui.util.snakbar
import com.binod.mvvmpractise.ui.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

 class LoginActivity : AppCompatActivity() , AuthListner, KodeinAware {

     override val kodein by kodein()
     private val factory:AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)

//        val networkConnectionInterceptor=NetworkConnectionInterceptor(this)
//        val api=Api(networkConnectionInterceptor)
//        val db=AppDatabase(this)
//        val repository=UserRepository(api,db)
//        val factory=AuthViewModelFactory(repository)


        val binding:ActivityLoginBinding=DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        val viewModel=ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewModel=viewModel
        viewModel.authListner=this
        viewModel.getLoggedInUser().observe(this, Observer { user->
            if (user !=null)
            {
                Intent(this,HomeActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }


        })


    }

    override fun onStart() {
        super.onStart()


    }
    override fun onSuccess(user: User) {
        progress_bar.hide()
        //toast("${user.name} is login successfully")

    }

    override fun onFailure(message: String) {

        progress_bar.hide()
        root_layout.snakbar(message)
    }
}