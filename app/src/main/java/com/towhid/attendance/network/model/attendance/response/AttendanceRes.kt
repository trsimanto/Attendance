package com.towhid.attendance.network.model.attendance.response

data class AttendanceRes(
    val app_message: String,
    val code: Int,
    val data: Data,
    val user_message: String
)