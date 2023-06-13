package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.NewRequestResponse
import com.example.be_prf_2023_b_g1_mobile.model.RequestResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RequestService {
    @GET("users/2/requests")
    fun getRequests(): Call<List<RequestResponse>>

    @POST("users/2/requests")
    fun createNewRequest(@Body params: NewRequestResponse) : Call<NewRequestResponse>
}


