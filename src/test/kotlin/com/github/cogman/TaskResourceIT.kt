package com.github.cogman

import io.quarkus.test.junit.QuarkusIntegrationTest
import org.junit.jupiter.api.Test
import java.util.*

@QuarkusIntegrationTest
class TaskResourceIT {
    @Test fun `Request task`() {

    }
    @Test fun `Update task`() {

    }
    @Test fun `Delete task`() {

    }
    data class Task(val id: String = UUID.randomUUID().toString(), val name: String, val completed: Boolean = false)
}