package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.Model.StationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationService {
    @GET("/users/2/stations")
    fun getCars(): Call<List<StationResponse>>
}