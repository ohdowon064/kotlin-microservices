package com.microservices.chapter3.controller

import com.microservices.chapter3.SimpleObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsonExamplesController {

    @GetMapping(path=["/json"])
    fun getJson() = SimpleObject()
}