package com.microservices.chapter4.service

import com.microservices.chapter4.model.Customer

interface CustomerService {
    fun getCustomer(id: Int) : Customer?
    fun searchCustomers(nameFilter: String) : List<Customer>
}