package com.microservices.Chapter5.database

import com.microservices.Chapter5.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    companion object {
        val initialCustomers = listOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Customer.Telephone("+44", "7123456789"))
        )
    }

    @PostConstruct
    fun initData() {
        mongoOperations.collectionExists("Customer").subscribe {
            if (it != true) {
                mongoOperations.createCollection("Customer").subscribe {
                    println("Customers collections created")
                }
            } else println("Customers collections already exists")

            customerRepository.saveAll(initialCustomers).subscribe() {
                println("Default customers created")
            }
        }
    }
}