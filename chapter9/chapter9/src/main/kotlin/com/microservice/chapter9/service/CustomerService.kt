package com.microservice.chapter9.service

import com.microservice.chapter9.model.Customer

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun getAllCustomers(): List<Customer>
}