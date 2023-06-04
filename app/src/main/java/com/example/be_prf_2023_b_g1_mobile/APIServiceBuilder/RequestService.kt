package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.RequestResponse
import retrofit2.Call
import retrofit2.http.GET

interface RequestService {
    @GET("users/2/requests")
    fun getRequests(): Call<List<RequestResponse>>
}