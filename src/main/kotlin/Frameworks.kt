package com.sebastianweszler

import com.sebastianweszler.config.OllamaConfig
import com.sebastianweszler.modules.OllamaModule
import com.sebastianweszler.services.OllamaService
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureFrameworks() {
    install(Koin) {
        modules(module {
            single<ApplicationConfig> { environment.config }
            single<HelloService> {
                HelloService {
                    println(environment.log.info("Hello, World!"))
                }
            }
            single { OllamaConfig(get<ApplicationConfig>().property("ollama.url").getString()) }
            single { OllamaService(get<OllamaConfig>().url) }
            single { OllamaModule(get()) }
        })
    }
}
