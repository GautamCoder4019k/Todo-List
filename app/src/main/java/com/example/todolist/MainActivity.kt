package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import androidx.room.Room
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.mutableListOf

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "Todos.db"
        ).build()
    }

   private  lateinit var binding:ActivityMainBinding

    private lateinit var todoAdapter:TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddTodo.visibility = View.GONE

        todoAdapter = TodoAdapter(mutableListOf<Todo>())
        binding.rvtodoitems.adapter = todoAdapter
        binding.rvtodoitems.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            var todos = db.dao.getAllTodos()
            withContext(Dispatchers.Main) {
                todoAdapter.updateTodos(todos)
            }
        }

        binding.etTodoItem.addTextChangedListener {
            if(it.toString().trim().equals(""))
                binding.btnAddTodo.visibility=View.GONE
            else
                binding.btnAddTodo.visibility=View.VISIBLE
        }


        binding.btnAddTodo.setOnClickListener {
            val todoTile = binding.etTodoItem.text.toString()
            if(todoTile.isNotEmpty()) {
                val todo = Todo(title = todoTile)
                CoroutineScope(Dispatchers.IO).launch {
                    db.dao.upsertTodo(todo)
                }
                todoAdapter.addtodo(todo)
                binding.etTodoItem.text.clear()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.miDelete ->{
                todoAdapter.getTodos().removeAll { todo ->
                    todo.isCheck
                }
                CoroutineScope(Dispatchers.IO).launch{
                db.dao.overwriteTodoList(todoAdapter.getTodos())
                }
                todoAdapter.notifyDataSetChanged()

            }

        }
        return true
    }
}