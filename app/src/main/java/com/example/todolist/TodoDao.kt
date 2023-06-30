package com.example.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface TodoDao {

    @Upsert
    fun upsertTodo(todo: Todo)

    @Delete
   fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    suspend fun getAllTodos(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<Todo>)

    @Query("DELETE FROM todo")
    suspend fun deleteAllTodos()

    @Transaction
    suspend fun overwriteTodoList(todos: List<Todo>) {
        // Delete all existing todos
        deleteAllTodos()

        // Insert the new list of todos
        insertTodos(todos)
    }


}