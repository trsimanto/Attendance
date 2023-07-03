package com.towhid.attendance.fragments.fgSubmission.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.towhid.attendance.network.api.RetrofitClient
import com.towhid.attendance.network.model.attendance.response.AttendanceRes
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SubmissionViewModel @Inject constructor() : ViewModel() {

    fun submission(
        name: String,
        latitude: Double,
        longitude: Double,
    ): MutableLiveData<Any> {
        val resultLiveData = MutableLiveData<Any>()
        val call: Call<AttendanceRes> =
            RetrofitClient.getInstance().getApi().attendanceReq_(
                name = name,
                uid = UUID.randomUUID().toString(),
                latitude = latitude,
                longitude = longitude,
                request_id = UUID.randomUUID().toString()
            )

        call.enqueue(object : Callback<AttendanceRes> {
            override fun onResponse(
                call: Call<AttendanceRes>,
                response: Response<AttendanceRes>
            ) {
                if (response.isSuccessful) {
                    resultLiveData.value = response.body()
                }
            }
            override fun onFailure(call: Call<AttendanceRes>, t: Throwable) {
                resultLiveData.value = t
            }
        })
        return resultLiveData
    }
}