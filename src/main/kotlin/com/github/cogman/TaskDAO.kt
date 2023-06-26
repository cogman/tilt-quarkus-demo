package com.github.cogman

import io.vertx.mutiny.pgclient.PgPool
import jakarta.enterprise.context.Dependent
import java.util.*

@Dependent
class TaskDAO(private val pgClient: PgPool) {
    suspend fun request(taskId: UUID) {
        pgClient.connection.map {  }
    }
}