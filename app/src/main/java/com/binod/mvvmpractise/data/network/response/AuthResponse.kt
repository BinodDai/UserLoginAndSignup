package com.binod.mvvmpractise.data.network.response

import com.binod.mvvmpractise.db.entities.User

data class AuthResponse(
    val isSuccessful:Boolean?,
    val message:String?,
    val user:User?
)