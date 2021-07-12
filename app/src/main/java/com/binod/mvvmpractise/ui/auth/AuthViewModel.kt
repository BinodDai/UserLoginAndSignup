package com.binod.mvvmpractise.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.binod.mvvmpractise.data.network.response.AuthResponse
import com.binod.mvvmpractise.data.repo.UserRepository
import com.binod.mvvmpractise.db.entities.User
import com.binod.mvvmpractise.ui.home.HomeActivity
import com.binod.mvvmpractise.ui.util.Coroutines
import com.binod.mvvmpractise.ui.util.NoInternetException
import java.lang.Exception

class AuthViewModel(private val repository: UserRepository): ViewModel() {

    var email:String?=null
    var username:String?=null
    var password:String?=null
    var passwordComform:String?=null

    var authListner:AuthListner?=null

    fun getLoggedInUser()=repository.getUser()

    fun loginButton(view:View)
    {
        authListner?.onStart()
        if (username.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListner?.onFailure("something went wrong")
            return
        }
//        val loginResponse=UserRepository().userLogin(username!!,password!!)
//        authListner?.onSuccess(loginResponse)

        Coroutines.main {
            try {


                val authResponse=repository.userLogin(username!!,password!!)
                authResponse.user?.let {
                    repository.saveUser(it)
                    authListner?.onSuccess(it)
                    return@main

                }
                authListner?.onFailure(authResponse.message!!)


            }catch (e:Exception)
            {

                authListner?.onFailure(e.message!!)
            }catch (e:NoInternetException)
            {
                authListner?.onFailure(e.message!!)
            }


        }


    }
    fun onSignIn(view: View)
    {
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }

    }


    fun onSignUp(view: View)
    {
        Intent(view.context,SignUp_activity::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun onSignUpButton(view:View)
    {
        authListner?.onStart()
        if (username.isNullOrEmpty())
        {
            authListner?.onFailure("name is empty")
            return
        }
        if (email.isNullOrEmpty())
            {
                authListner?.onFailure("email is empty")
                return
            }
        if (password.isNullOrEmpty())
        {
            authListner?.onFailure("password is empty")
            return
        }

        if (password !=passwordComform)
        {
            authListner?.onFailure("password didnt match")
            return
        }
//        val loginResponse=UserRepository().userLogin(username!!,password!!)
//        authListner?.onSuccess(loginResponse)

        Coroutines.main {
            try {


                val authResponse=repository.userSignUp(username!!,email!!,password!!)
                authResponse.user?.let {
                    repository.saveUser(it)
                    authListner?.onSuccess(it)
                    return@main

                }
                authListner?.onFailure(authResponse.message!!)


            }catch (e:Exception)
            {

                authListner?.onFailure(e.message!!)
            }catch (e:NoInternetException)
            {
                authListner?.onFailure(e.message!!)
            }


        }


    }
}