package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private  lateinit var binding:ActivityMainBinding

    private lateinit var todoAdapter:TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())
        binding.rvtodoitems.adapter = todoAdapter
        binding.rvtodoitems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTile = binding.etTodoItem.text.toString()
            if(todoTile.isNotEmpty()) {
                val todo = Todo(todoTile)
                todoAdapter.addtodo(todo)
                binding.etTodoItem.text.clear()
            }
        }
        binding.btnDeleteTodo.setOnClickListener {
             todoAdapter.deleteDoneTodos()
        }
    }
}