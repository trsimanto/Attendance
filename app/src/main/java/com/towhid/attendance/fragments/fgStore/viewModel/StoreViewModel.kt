package com.towhid.attendance.fragments.fgStore.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.towhid.attendance.network.api.RetrofitClient
import com.towhid.attendance.network.model.store.response.StoreRes
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor() : ViewModel() {
    fun getStores(
        page: Int,
    ): MutableLiveData<Any> {
        val resultLiveData = MutableLiveData<Any>()
        val call: Call<StoreRes> =
            RetrofitClient.getInstance().getApi().storeReq_(page = page)

            // Ora live data bade coroutine chaise naki bujtesinah 

        call.enqueue(object : Callback<StoreRes> {
            override fun onResponse(
                call: Call<StoreRes>,
                response: Response<StoreRes>
            ) {
                print(response)
                if (response.isSuccessful) {
                    resultLiveData.value = response.body()
                }
            }
            override fun onFailure(call: Call<StoreRes>, t: Throwable) {
                resultLiveData.value = t
            }
        })
        return resultLiveData
    }
}
