package com.example.be_prf_2023_b_g1_mobile.model

data class NewRequestResponse(
    val serial_number: String,
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val brand: String,
    val model: String
)
