package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @Autowired
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    @GetMapping(path=["/customer"])
    fun getCustomers() = customers.map(Map.Entry<Int, Customer>::value).toList()

    @GetMapping(path= ["/customer/{id}"])
    fun getCustomer(
        @PathVariable id: Int
    ) = customers[id]

    @PostMapping(path=["/customer"])
    fun createCustomer(
        @RequestBody customer: Customer
    ): String {
        customers[customer.id] = customer
        return "${customer.id} customer를 생성했습니다."
    }

    @DeleteMapping(path=["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int): String {
        customers.remove(id)
        return "$id customer를 삭제했습니다."
    }

    @RequestMapping(path=["/customer/{id}"], method=[RequestMethod.PUT])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): String {
        customers.remove(id)
        customers[customer.id] = customer
        return "$id customer를 업데이트 했습니다."
    }
}