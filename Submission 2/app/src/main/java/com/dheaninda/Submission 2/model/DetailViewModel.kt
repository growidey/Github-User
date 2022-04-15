package com.dheaninda.part1.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dheaninda.part1.api.RetrofitUser
import com.dheaninda.part1.data.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()

    fun setUserDetail(username: String) {
        RetrofitUser.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }
        fun getUserDetail(): LiveData<DetailUserResponse> {
            return user
    }
}