package com.binod.mvvmpractise.data.network

import com.binod.mvvmpractise.ui.util.Exception
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T:Any> apiRequest(call:suspend ()->Response<T>):T
    {
        val response=call.invoke()
        if (response.isSuccessful)
        {
            return response.body()!!
        }
        else
        {
            val error=response.errorBody()?.toString()
            val message=StringBuilder()
            error?.let {
                try {

                    val message=JSONObject(it).getString("message")

                }catch (e:JSONException)
                {

                    message.append("\n")
                }

            }
            message.append("error Code:${response.code()}")
            throw Exception(message.toString())
        }

    }



}