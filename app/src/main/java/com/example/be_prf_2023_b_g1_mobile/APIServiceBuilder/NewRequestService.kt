package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.NewRequestResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NewRequestService {
    @POST("users/2/requests")
    suspend fun createNewRequest(@Body params: NewRequestResponse)
}