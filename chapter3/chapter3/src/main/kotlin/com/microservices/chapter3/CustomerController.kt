package com.microservices.chapter3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {

    @GetMapping(path= ["/customer"])
    fun getCustomer() = Customer(1, "Kotlin")
}