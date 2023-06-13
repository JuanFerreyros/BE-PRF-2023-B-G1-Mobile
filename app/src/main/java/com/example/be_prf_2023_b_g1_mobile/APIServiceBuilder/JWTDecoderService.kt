package com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder

import android.util.Base64
import com.example.be_prf_2023_b_g1_mobile.model.Context
import com.google.gson.Gson
import com.google.gson.JsonParser

object JWTDecoderService {

    val API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2ODQzNjg2NTUsImV4cCI6MTcxNTkwNDY1NSwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsImlkIjoiMiIsInJvbCI6InVzZXIifQ.4MrjfaEglT5NOHLsf_cldZCcxbUX8vNC62qoLp9XpaY"
    val split1 = API_KEY.split("." )[1]
    val decode = String(Base64.decode(split1, Base64.DEFAULT))
    val context = Gson().fromJson(decode, Context::class.java)


}