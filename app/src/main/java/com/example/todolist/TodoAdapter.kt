package com.example.todolist

import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter(
    private  var todos: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    inner class TodoViewHolder(val binding: ItemTodoBinding) :RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
          val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                 return TodoViewHolder(view)
    }
    fun addtodo(todo:Todo)
    {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun updateTodos(todoList: List<Todo>) {
        todos = todoList as MutableList<Todo>
        notifyDataSetChanged()
    }

    fun getTodos(): MutableList<Todo> {
        return(todos)
    }

    private fun toggleStrinkeThrough(tvTodoTiltle : TextView, ischecked : Boolean)
    {
        if(ischecked)
        {
         tvTodoTiltle.paintFlags=tvTodoTiltle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTiltle.paintFlags = tvTodoTiltle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.binding.apply {
            tvTodoitem.text=curTodo.title
            cbDone.isChecked=curTodo.isCheck
            toggleStrinkeThrough(tvTodoitem,curTodo.isCheck)
            cbDone.setOnCheckedChangeListener { _, b ->
                toggleStrinkeThrough(tvTodoitem,!curTodo.isCheck)
                curTodo.isCheck = !curTodo.isCheck
            }
        }

    }

    override fun getItemCount(): Int {
            return todos.size
    }
}