package com.towhid.attendance

import com.towhid.attendance.network.api.Api
import com.towhid.attendance.network.model.attendance.response.AttendanceRes
import com.towhid.attendance.network.model.store.response.StoreRes
import retrofit2.Call
import retrofit2.Retrofit

class ApiService constructor(private val retrofit: Retrofit) :
    Api {
    private val endpoint by lazy { retrofit.create(Api::class.java) }
    override fun storeReq_(page: Int): Call<StoreRes> = endpoint.storeReq_(page)


    override fun attendanceReq_(
        name: String,
        uid: String,
        latitude: Double,
        longitude: Double,
        request_id: String
    ): Call<AttendanceRes> = attendanceReq_(
        name,
        uid,
        latitude,
        longitude,
        request_id
    )


}