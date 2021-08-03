package com.microservices.Chapter5.service

import com.microservices.Chapter5.database.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {

    fun getCustomer(id: Int): Mono<Customer>
    fun createCustomer(customer: Mono<Customer>) : Mono<Customer>
    fun deleteCustomer(id: Int): Mono<Boolean>
    fun searchCustomers(nameFilter: String): Flux<Customer>
}