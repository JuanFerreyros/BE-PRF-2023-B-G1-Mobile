package com.example.be_prf_2023_b_g1_mobile.model

data class RequestResponse(
    val _id: String,
    val serial_number: String,
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val brand: String,
    val model: String,
    val status: String,
    val created_by: Int,
    val created_at: String,
    val approved_by: Int,
    val approved_at: String,
    val user: User
)
