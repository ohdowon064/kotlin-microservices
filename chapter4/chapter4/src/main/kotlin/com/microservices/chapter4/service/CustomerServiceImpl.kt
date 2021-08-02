package com.microservices.chapter4.service

import com.microservices.chapter4.model.Customer
import com.microservices.chapter4.router.CustomerExistException
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
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

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter: String): Flux<Customer> {
        return customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toFlux()
    }

    override fun createCustomer(customerMono: Mono<Customer>) =
        customerMono.flatMap {
            if(customers[it.id] == null) {
                customers[it.id] = it
                it.toMono()
            } else {
                Mono.error(CustomerExistException("Customer ${it.id} already Exist"))
            }
        }
}