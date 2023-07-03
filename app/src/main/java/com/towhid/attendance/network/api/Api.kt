package com.towhid.attendance.network.api
import com.towhid.attendance.network.model.attendance.response.AttendanceRes
import com.towhid.attendance.network.model.store.response.StoreRes

import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("stores")
    fun storeReq_(
        @Query("page") page: Int,
    ): Call<StoreRes>

    @POST("attendance")
    fun attendanceReq_(
        @Field ("name") name : String,
        @Field ("uid") uid : String,
        @Field ("latitude") latitude : Double,
        @Field ("longitude") longitude : Double,
        @Field ("request_id") request_id : String,
    ): Call<AttendanceRes>



}


