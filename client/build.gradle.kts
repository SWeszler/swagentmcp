plugins {
    application
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.1.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.modelcontextprotocol.sample.client.MainKt")
}

dependencies {
    implementation(project(":"))
    // Add Ktor server dependencies
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-cio")
    implementation("io.ktor:ktor-server-sse")

    // Add MCP SDK dependency
    implementation("io.modelcontextprotocol:kotlin-sdk:0.4.0")

    // Add Kotlinx IO dependencies
    implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.1")
    implementation("com.anthropic:anthropic-java:0.8.0")
}