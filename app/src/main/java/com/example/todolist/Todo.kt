package com.example.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Todo (
    val title:String,
    var isCheck:Boolean=false,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)