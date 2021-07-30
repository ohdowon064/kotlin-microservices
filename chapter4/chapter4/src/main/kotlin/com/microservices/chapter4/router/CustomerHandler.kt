package com.microservices.chapter4.router

import com.microservices.chapter4.model.Customer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerHandler {

    fun get(serverRequest: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(
            Customer(1, "functional web").toMono(),
            Customer::class.java
        )
}