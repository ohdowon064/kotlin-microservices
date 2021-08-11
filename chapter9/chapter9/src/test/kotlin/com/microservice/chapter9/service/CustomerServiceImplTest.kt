package com.microservice.chapter9.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class CustomerServiceImplTest {

    @Autowired
    lateinit var customerServiceImpl: CustomerServiceImpl

    @Test
    fun getCustomer() {
        customerServiceImpl.getCustomer(1)
    }

    @Test
    fun getAllCustomers() {
        customerServiceImpl.getAllCustomers()
    }
}