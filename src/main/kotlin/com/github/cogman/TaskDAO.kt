package com.github.cogman

import io.smallrye.mutiny.coroutines.awaitSuspending
import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Tuple
import jakarta.enterprise.context.Dependent
import java.util.*

@Dependent
class TaskDAO(private val pgClient: PgPool) {
    suspend fun create(task: Task): UUID {
        val results = pgClient.preparedQuery("INSERT INTO tasks(id, task, completed) VALUES ($1, $2, $3)")
            .execute(Tuple.of(task.id, task.task, task.completed))
            .awaitSuspending()
        return task.id
    }
    suspend fun request(taskId: UUID): Task? {
        val results = pgClient.preparedQuery("SELECT task, completed FROM tasks WHERE id=$1")
            .execute(Tuple.of(taskId))
            .awaitSuspending()

        val row = results.firstOrNull()
        return row?.let { Task(id = taskId, task = it.getString("task"), completed = it.getBoolean("completed")) }
    }

    suspend fun update(task: Task) {
        val results = pgClient.preparedQuery("UPDATE tasks SET task=$2, completed=$3 WHERE id=$1")
            .execute(Tuple.of(task.id, task.task, task.completed))
            .awaitSuspending()
    }

    suspend fun delete(taskId: UUID) {
        val results = pgClient.preparedQuery("DELETE FROM tasks t WHERE id=$1")
            .execute(Tuple.of(taskId))
            .awaitSuspending()
    }

    data class Task(val id: UUID, val task: String, val completed: Boolean)
}