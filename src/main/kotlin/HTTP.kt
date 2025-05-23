package com.sebastianweszler

import io.ktor.server.application.Application
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    routing {
        openAPI(path = "openapi")
    }
    routing {
        swaggerUI(path = "openapi")
    }
}
