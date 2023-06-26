package com.github.cogman

import jakarta.ws.rs.Path
import java.util.*

@Path("task")
class TaskResource {
    suspend fun request(val taskId: UUID): TaskDTO {

    }

    data class TaskDTO(val taskId: UUID, val task: String, val completed: Boolean)
}