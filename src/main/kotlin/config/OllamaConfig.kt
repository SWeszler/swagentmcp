package com.sebastianweszler.config

import io.ktor.server.config.ApplicationConfig

data class OllamaConfig(
    val ollamaConfing: ApplicationConfig
) {
    val url: String = ollamaConfing.property("ollama.url").getString()
    val model: String = ollamaConfing.property("ollama.model").getString()
}
