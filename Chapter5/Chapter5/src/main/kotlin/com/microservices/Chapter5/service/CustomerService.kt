package com.microservices.Chapter5.service

import com.microservices.Chapter5.database.Customer
import reactor.core.publisher.Mono

interface CustomerService {

    fun getCustomer(id: Int): Mono<Customer>
}