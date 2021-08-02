package com.microservices.Chapter5.router

import com.microservices.Chapter5.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

    @Bean
    fun customerRoutes() = router {
        "/customer".nest {
            GET("/{id}", customerHandler::get)
        }
    }
}