package com.simya.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("classpath:secure.properties")
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
