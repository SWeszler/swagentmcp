package com.sebastianweszler

import com.sebastianweszler.modules.ollamaModule
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureFrameworks() {
    install(Koin) {
        modules(
            module {
                single<ApplicationConfig> { environment.config }
                single<HelloService> {
                    HelloService {
                        println(environment.log.info("Hello, World!"))
                    }
                }
            },
            ollamaModule
        )
    }
}
