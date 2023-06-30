package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private  lateinit var binding:ActivityMainBinding

    private lateinit var todoAdapter:TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddTodo.visibility = View.GONE

        todoAdapter = TodoAdapter(mutableListOf())
        binding.rvtodoitems.adapter = todoAdapter
        binding.rvtodoitems.layoutManager = LinearLayoutManager(this)


        binding.etTodoItem.addTextChangedListener {
            if(it.toString().trim().equals(""))
                binding.btnAddTodo.visibility=View.GONE
            else
                binding.btnAddTodo.visibility=View.VISIBLE
        }


        binding.btnAddTodo.setOnClickListener {
            val todoTile = binding.etTodoItem.text.toString()
            if(todoTile.isNotEmpty()) {
                val todo = Todo(todoTile)
                todoAdapter.addtodo(todo)
                binding.etTodoItem.text.clear()
            }
        }
//        binding.btnDeleteTodo.setOnClickListener {
//             todoAdapter.deleteDoneTodos()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.miDelete ->{
               todoAdapter.deleteDoneTodos()
            }

        }
        return true
    }
}