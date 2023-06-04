package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.StationResponse
import retrofit2.Call
import retrofit2.http.GET

interface StationService {
    @GET("users/2/stations")
    fun getStations(): Call<List<StationResponse>>
}