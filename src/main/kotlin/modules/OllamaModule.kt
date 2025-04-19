package com.sebastianweszler.modules

import com.sebastianweszler.services.OllamaService

class OllamaModule(private val ollamaService: OllamaService) {
    suspend fun handlePrompt(prompt: String, model: String): String {
        return ollamaService.processPrompt(prompt, model)
    }
}
