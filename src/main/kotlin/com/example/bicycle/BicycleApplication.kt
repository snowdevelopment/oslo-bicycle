package com.example.bicycle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Henrik Solberg
 * */
@SpringBootApplication
class BicycleApplication

fun main(args: Array<String>) {
	runApplication<BicycleApplication>(*args)
}
