package com.example.be_prf_2023_b_g1_mobile.Model

data class StationResponse(
    private val _id: String,
    private val serial_number: String,
    private val name: String,
    private val longitude: Double,
    private val latitude: Double,
    private val brand: String,
    private val model: String,
    private val status: String,
    private val created_by: Int,
    private val created_at: String,
    private val user: User
)

data class User(
    private val id: Int,
    private val user_name: String,
    private val mail: String
)