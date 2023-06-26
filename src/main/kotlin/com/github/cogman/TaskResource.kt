package com.github.cogman

import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import org.jboss.resteasy.reactive.RestPath
import java.util.*

@Path("task")
class TaskResource(private val dao: TaskDAO) {
    @POST
    suspend fun create(task: TaskDTO): UUID {
        return dao.create(TaskDAO.Task(id=task.taskId, task=task.task, completed=task.completed))
    }

    @GET
    @Path("/{taskId}")
    suspend fun request(@PathParam("taskId") taskId: UUID): TaskDTO {
        val task = dao.request(taskId) ?: throw NotFoundException("Task $taskId doesn't exist")
        return TaskDTO(taskId = taskId, task = task.task, completed = task.completed)
    }

    @PATCH
    suspend fun update(task: TaskDTO) {
        dao.update(TaskDAO.Task(id=task.taskId, task=task.task, completed=task.completed))
    }

    @DELETE
    @Path("{taskId}")
    suspend fun delete(@RestPath taskId: UUID) {
        dao.delete(taskId)
    }

    data class TaskDTO(val taskId: UUID, val task: String, val completed: Boolean)
}