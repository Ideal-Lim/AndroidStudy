package com.example.todolistapp.roomdatabase

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.util.Log

class TodoRepository(application: Application) {
    private val todoDAO: TodoDAO

    init{
        val db = TodoDatabase.getInstance(application)
        todoDAO = db!!.TodoDAO()
    }

    fun insert(todo: TodoEntity){
        todoDAO.insert(todo)
    }

    fun getAllByDate(date: String): LiveData<List<TodoEntity>?>{
        return todoDAO.getAllByDate(date)
    }

    fun getAll(): LiveData<List<TodoEntity>>{
        return todoDAO.getAll()
    }

    fun delete(todo: TodoEntity) {
        todoDAO.delete(todo)
    }
}