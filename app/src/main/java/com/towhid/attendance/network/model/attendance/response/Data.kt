package com.towhid.attendance.network.model.attendance.response

data class Data(
    val created_at: String,
    val id: Int,
    val latitude: String,
    val longitude: String,
    val name: String,
    val request_id: String,
    val uid: String,
    val updated_at: String
)