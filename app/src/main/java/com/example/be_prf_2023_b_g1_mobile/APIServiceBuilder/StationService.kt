package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import com.example.be_prf_2023_b_g1_mobile.model.StationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface StationService {
    @GET("users/{id}/stations")
    fun getStations(@Path("id") id:String): Call<List<StationResponse>>

    @PATCH("users/{id}/stations/{station-id}/rename")
    fun updateStation(
        @Path("station-id") stationId: String,
        @Body requestBody: Map<String, String>,
        @Path("id") id:String
    ): Call<StationResponse>

    @PATCH("users/{id}/stations/{station-id}/suspend")
    fun suspendStation(
        @Path("station-id") stationId: String,
        @Path("id") id:String
    ): Call<StationResponse>

}