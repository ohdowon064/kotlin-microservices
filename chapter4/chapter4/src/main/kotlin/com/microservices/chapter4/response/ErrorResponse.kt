package com.microservices.chapter4.response

data class ErrorResponse(
    val error: String,
    val message: String
)