package com.dheaninda.part1.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dheaninda.part1.api.RetrofitUser
import com.dheaninda.part1.data.User
import com.dheaninda.part1.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String){
        RetrofitUser.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUser
    }
}