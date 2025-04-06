package com.example.bicycle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BicycleApplication

fun main(args: Array<String>) {
	runApplication<BicycleApplication>(*args)
}
