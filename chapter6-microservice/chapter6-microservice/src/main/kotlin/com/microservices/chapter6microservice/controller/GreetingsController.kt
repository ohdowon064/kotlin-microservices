package com.microservices.chapter6microservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingsController {

    @Value("\${microservice.example.greetings}")
    private lateinit var greetings: String

    @GetMapping(path=["/greetings"])
    fun greetings() = greetings

}