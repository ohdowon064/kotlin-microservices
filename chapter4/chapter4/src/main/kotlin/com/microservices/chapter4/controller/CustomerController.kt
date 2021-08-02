package com.microservices.chapter4.controller

import com.microservices.chapter4.model.Customer
import com.microservices.chapter4.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController

class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping(path = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Mono<Customer>> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @GetMapping(path = ["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "")
    nameFilter: String) =
        customerService.searchCustomers(nameFilter)

    @PostMapping(path = ["/customer"])
    fun createCustomer(@RequestBody customerMono: Mono<Customer>) =
        ResponseEntity(customerService.createCustomer(customerMono), HttpStatus.CREATED)
}