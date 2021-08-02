package com.microservices.Chapter5.repository

import com.microservices.Chapter5.database.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository : ReactiveCrudRepository<Customer, Int>