package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.NewRequestResponse
import com.example.be_prf_2023_b_g1_mobile.model.RequestResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestService {
    @GET("users/{id}/requests")
    fun getRequests(@Path("id") id:String): Call<List<RequestResponse>>

    @POST("users/{id}/requests")
    fun createNewRequest(@Body params: NewRequestResponse, @Path("id") id:String) : Call<NewRequestResponse>
}


