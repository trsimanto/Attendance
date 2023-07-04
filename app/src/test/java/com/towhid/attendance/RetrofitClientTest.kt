package com.towhid.attendance

import com.google.common.truth.Truth
import com.towhid.attendance.network.api.RetrofitClient
import com.towhid.attendance.network.server_info.ServerInfo
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {

    @Test
    fun testRetrofitInstance() {
        val instance: Retrofit = RetrofitClient().getRetrofit()
        val result = instance.baseUrl().toUrl().toString() == ServerInfo.BASE_URL
        //Assert that, Retrofit's base url matches to our BASE_URL
        println("${ServerInfo.BASE_URL}  ->  $result")
        Truth.assertThat(result).isEqualTo(true)
    }

    @Test
    fun testStoreListInputIsValid() {

        for (page in PositiveData.pageLimit) {
            val service = ApiService(RetrofitClient().getRetrofit())
            val response = service.storeReq_(page).execute()
            val errorBody = response.errorBody()
            assert(errorBody == null)
            val responseWrapper = response.body()
            assert(responseWrapper != null)
            val result = response.code() == 200
            println("$page  ->  ${result}")
            Truth.assertThat(result).isEqualTo(true)
        }

    }

    @Test
    fun testStoreListInputIsInvalid() {

        for (page in NegativeData.pageLimit) {
            val service = ApiService(RetrofitClient().getRetrofit())
            val response = service.storeReq_(page).execute()
            val errorBody = response.errorBody()
            assert(errorBody == null)
            val responseWrapper = response.body()
            assert(responseWrapper != null)
            val result = response.code() == 200
            println("$page  ->  ${result}")
            Truth.assertThat(result).isEqualTo(true)
        }

    }

}