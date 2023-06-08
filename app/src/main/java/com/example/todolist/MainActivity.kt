package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter:TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        findViewById<RecyclerView>(R.id.rvtodoitems).adapter = todoAdapter
        findViewById<RecyclerView>(R.id.rvtodoitems).layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnAddTodo).setOnClickListener {
            val todoTile = findViewById<TextView>(R.id.etTodoItem).text.toString()
            if(todoTile.isNotEmpty()) {
                val todo = Todo(todoTile)
                todoAdapter.addtodo(todo)
                findViewById<TextView>(R.id.etTodoItem).text=""
            }
        }
        findViewById<Button>(R.id.btnDeleteTodo).setOnClickListener {
             todoAdapter.deleteDoneTodos()
        }
    }
}