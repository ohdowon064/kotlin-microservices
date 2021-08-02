package com.microservices.Chapter5.service

import com.microservices.Chapter5.database.Customer
import com.microservices.Chapter5.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerServiceImpl : CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    override fun getCustomer(id: Int): Mono<Customer> =customerRepository.findById(id)
}