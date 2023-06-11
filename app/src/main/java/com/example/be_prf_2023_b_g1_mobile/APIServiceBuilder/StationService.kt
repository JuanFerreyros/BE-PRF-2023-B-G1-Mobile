package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.StationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface StationService {
    @GET("users/2/stations")
    fun getStations(): Call<List<StationResponse>>

    @PATCH("users/2/stations/{station-id}/rename")
    fun updateStation(
        @Path("station-id") stationId: String,
        @Body requestBody: Map<String, String>
    ): Call<StationResponse>

    @PATCH("users/2/stations/{station-id}/suspend")
    fun suspendStation(
        @Path("station-id") stationId: String,
    ): Call<StationResponse>

}