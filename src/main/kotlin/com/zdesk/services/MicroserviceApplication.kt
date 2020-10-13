package com.zdesk.services

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import com.zdesk.services.properties.SpotifyProperties

@SpringBootApplication
@EnableConfigurationProperties(SpotifyProperties::class)
class MicroserviceApplication

fun main(args: Array<String>) {
    runApplication<MicroserviceApplication>(*args)
}
