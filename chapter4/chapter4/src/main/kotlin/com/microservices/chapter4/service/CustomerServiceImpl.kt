package com.microservices.chapter4.service

import com.microservices.chapter4.model.Customer
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Customer.Telephone("+44", "7123456789")),
        )

        val customers = ConcurrentHashMap<Int, Customer>(
            initialCustomers.associateBy(Customer::id)
        )
    }

    override fun getCustomer(id: Int): Customer? = customers[id]

    override fun searchCustomers(nameFilter: String): List<Customer> {
        return customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()
    }
}