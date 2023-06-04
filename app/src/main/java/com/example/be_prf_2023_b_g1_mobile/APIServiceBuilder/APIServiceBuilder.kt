package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIServiceBuilder {
    private const val BASE_URL = "http://localhost:8080/api/v1"
    private const val API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ" +
            "1aWxkZXIiLCJpYXQiOjE2ODQzNjg2NTUsImV4cCI6MTcxNTkwNDY1NSwiYXVkIjoid3d3LmV4YW1wbGUuY29" +
            "tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsImlkIjoiMiIsInJvbCI6InVzZXIifQ.4MrjfaEglT5N" +
            "OHLsf_cldZCcxbUX8vNC62qoLp9XpaY"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("X-Api-Key", API_KEY)
                .build()
            chain.proceed(modifiedRequest)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getStations(): StationService {
        return retrofit.create(StationService::class.java)
    }
}