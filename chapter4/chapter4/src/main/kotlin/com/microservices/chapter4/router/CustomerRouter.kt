package com.microservices.chapter4.router

import com.microservices.chapter4.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component // 새 컴포넌트 생성 -> 컴포넌트 스캔이 해당 컴포넌트를 스캔한다.
class CustomerRouter(private var customerHandler: CustomerHandler) {

//    @Autowired // 핸들러를 라우터에 연결
//    lateinit var customerHandler: CustomerHandler

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        // 빈이 노출되면 새로운 방식의 RouterFunction을 생성한다.
        // 웹 애플리케이션 경로 정의 가능
        "/functional".nest {
            // 라우터가 /functional 경로의 모든 요청을 처리한다.
            "/customer".nest { GET("/", customerHandler::get) }
        }
    }
}