package com.binod.mvvmpractise.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.binod.mvvmpractise.data.network.Api
import com.binod.mvvmpractise.data.network.SafeApiRequest
import com.binod.mvvmpractise.data.network.response.AuthResponse
import com.binod.mvvmpractise.db.AppDatabase
import com.binod.mvvmpractise.db.entities.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
        private val api:Api,
        private val db:AppDatabase
) :SafeApiRequest(){
    suspend fun userLogin(username:String, password:String):AuthResponse
    {

        return apiRequest { api.userLogin(username,password) }

    }

    suspend fun userSignUp(name:String,email:String,password: String):AuthResponse{
        return apiRequest{api.userSignUp(name,email,password)}
    }
    suspend fun saveUser(user:User)=db.getUserDao().insert(user)

    fun getUser()=db.getUserDao().getUser()
}