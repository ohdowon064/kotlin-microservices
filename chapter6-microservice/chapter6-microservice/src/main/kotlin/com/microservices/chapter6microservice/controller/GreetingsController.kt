package com.microservices.chapter6microservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greetings")
class GreetingsController {

    @Value("\${microservice.example.greetings}")
    private lateinit var greetings: String

    @GetMapping()
    fun greetings() = greetings

    @GetMapping(path=["/info"])
    fun info(
        @Value("\${server.port}") port: String
    ) = "greetings 서비스의 기본 동작 Port: ${port}"

}