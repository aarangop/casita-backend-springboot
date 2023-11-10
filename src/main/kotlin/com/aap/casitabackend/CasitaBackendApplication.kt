package com.aap.casitabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class CasitaBackendApplication

fun main(args: Array<String>) {
    runApplication<CasitaBackendApplication>(*args)
}
