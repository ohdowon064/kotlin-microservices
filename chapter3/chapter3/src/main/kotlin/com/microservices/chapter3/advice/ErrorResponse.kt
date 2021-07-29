package com.microservices.chapter3.advice

data class ErrorResponse(
    val error: String,
    val message: String
)