package com.microservices.chapter4.router

class CustomerExistException(override val message: String): Exception(message)