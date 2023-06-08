package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class TodoAdapter(
    private  var todos: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    inner class TodoViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
          var view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
                 return TodoViewHolder(view)
    }
    fun addtodo(todo:Todo)
    {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodos()
    {
        todos.removeAll{ todo ->
            todo.isCheck
        }
        notifyDataSetChanged()
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
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTodoitem).text=curTodo.title
            findViewById<CheckBox>(R.id.cbDone).isChecked=curTodo.isCheck
            toggleStrinkeThrough(findViewById(R.id.tvTodoitem),curTodo.isCheck)
            findViewById<CheckBox>(R.id.cbDone).setOnCheckedChangeListener { _, b ->
                toggleStrinkeThrough(findViewById(R.id.tvTodoitem),!curTodo.isCheck)
                curTodo.isCheck = !curTodo.isCheck
            }
        }

    }

    override fun getItemCount(): Int {
            return todos.size
    }
}