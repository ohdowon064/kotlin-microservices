package com.microservices.chapter3.controller

import com.microservices.chapter3.advice.CustomerNotFoundException
import com.microservices.chapter3.objects.Customer
import com.microservices.chapter3.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService // 컨트롤러에서 사용하는 빈을 서비스로 변경
    // 컴포넌트 스캔이 빈 구현을 해당 인터페이스(CustomerService)에 주입한다.

    @GetMapping(path=["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer?> {
        val customer = customerService.getCustomer(id) ?:
            throw CustomerNotFoundException("customer '$id' not found")
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @PostMapping(path=["/customer"])
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit?> {
        customerService.createCustomer(customer)
        return ResponseEntity<Unit?>(null, HttpStatus.CREATED)
    }

    @DeleteMapping(path=["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit?> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity<Unit?>(null, status)
    }

    @PutMapping(path=["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit?> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity<Unit?>(null, status)
    }

    @GetMapping(path= ["/customers"])
    fun getCustomers(
        @RequestParam(required = false, defaultValue = "") nameFilter: String
    ): ResponseEntity<List<Customer>> {
        val customers = customerService.searchCustomers(nameFilter)
        val status = if (customers.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customers, status)
    }

}