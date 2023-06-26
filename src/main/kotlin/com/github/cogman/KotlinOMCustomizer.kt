package com.github.cogman

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.quarkus.jackson.ObjectMapperCustomizer
import jakarta.enterprise.context.Dependent

@Dependent
class KotlinOMCustomizer: ObjectMapperCustomizer {
    override fun customize(objectMapper: ObjectMapper?) {
        objectMapper?.registerKotlinModule()
    }

}