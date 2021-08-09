package com.microservices.chapter5.handler

import com.microservices.chapter5.database.Customer
import com.microservices.chapter5.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class CustomerHandler(val customerService: CustomerService) {

    fun get(serverRequest: ServerRequest) =
        customerService.getCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap { ServerResponse.ok().body(fromObject(it)) }
            .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())

    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
            ServerResponse.created(URI.create("/customer/${it.id}")).build()
        }

    fun delete(serverRequest: ServerRequest) =
        customerService.deleteCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap {
                if (it) ServerResponse.ok().build()
                else ServerResponse.status(HttpStatus.NOT_FOUND).build()
            }

    fun search(serverRequest: ServerRequest) =
        ServerResponse.ok().body(
            customerService.searchCustomers(
                serverRequest.queryParam("nameFilter").orElse("")
            ), Customer::class.java
        )
}