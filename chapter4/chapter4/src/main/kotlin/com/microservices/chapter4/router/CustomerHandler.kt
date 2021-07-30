package com.microservices.chapter4.router

import com.microservices.chapter4.model.Customer
import com.microservices.chapter4.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status

@Component
class CustomerHandler(val customerService: CustomerService) {

    fun get(serverRequest: ServerRequest) =
        customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                .flatMap { ServerResponse.ok().body(fromObject(it)) }
                .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())
}