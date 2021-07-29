package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService // 컨트롤러에서 사용하는 빈을 서비스로 변경
    // 컴포넌트 스캔이 빈 구현을 해당 인터페이스(CustomerService)에 주입한다.

    @GetMapping(path=["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int) = customerService.getCustomer(id)

    @PostMapping(path=["/customer"])
    fun createCustomer(@RequestBody customer: Customer) {
        customerService.createCustomer(customer)
    }

    @DeleteMapping(path=["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int) {
        customerService.deleteCustomer(id)
    }

    @PutMapping(path=["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) {
        customerService.updateCustomer(id, customer)
    }

    @GetMapping(path= ["/customers"])
    fun getCustomers(
        @RequestParam(required = false, defaultValue = "") nameFilter: String
    ) = customerService.searchCustomers(nameFilter)

}