package com.microservices.chapter3.controller

import com.microservices.chapter3.objects.ComplexObject
import com.microservices.chapter3.objects.SimpleObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsonExamplesController {

    @GetMapping(path=["/json/simple"])
    fun getJson() = SimpleObject("hi", "kotlin")

    @GetMapping(path=["/json/complex"])
    fun getComplexJson() = ComplexObject(object1 = SimpleObject("more", "complex"))
}