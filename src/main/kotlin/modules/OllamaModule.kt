package com.sebastianweszler.modules

import com.sebastianweszler.config.OllamaConfig
import com.sebastianweszler.services.OllamaService
import io.ktor.server.config.*
import org.koin.dsl.module

val ollamaModule = module {
    single { OllamaConfig(get<ApplicationConfig>().property("ollama.url").getString()) }
    single { OllamaService(get<OllamaConfig>().url) }
}
