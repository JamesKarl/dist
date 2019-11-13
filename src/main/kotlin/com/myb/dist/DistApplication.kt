package com.myb.dist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DistApplication

fun main(args: Array<String>) {
	runApplication<DistApplication>(*args)
}
