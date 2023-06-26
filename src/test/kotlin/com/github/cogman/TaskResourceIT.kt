package com.github.cogman

import io.quarkus.test.junit.QuarkusIntegrationTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

//@QuarkusIntegrationTest
class TaskResourceIT {
    val task: Task = Task(task = "dummy")

    @BeforeEach
    fun `Create task`() {
        RestAssured.given()
            .`when`().post("/task", task)
            .then()
            .statusCode(200)
    }

    @Test fun `Request task`() {

    }
    @Test fun `Update task`() {

    }
    @Test fun `Delete task`() {

    }
    data class Task(val taskId: String = UUID.randomUUID().toString(), val task: String, val completed: Boolean = false)
}