package com.afrasiyab.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserBackendApplication

fun main(args: Array<String>) {
	runApplication<UserBackendApplication>(*args)
}
