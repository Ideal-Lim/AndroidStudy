package com.example.todolistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.TodoitemRecyclerBinding
import com.example.todolistapp.roomdatabase.TodoEntity

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.ViewHolder>(){

    private val todoList = ArrayList<TodoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapter.ViewHolder {
        val binding = TodoitemRecyclerBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoListAdapter.ViewHolder, position: Int) {
        val todoEntity = todoList[position]
        holder.setTodoListUI(todoEntity,position)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTodoList(todo: List<TodoEntity>?){
        if (todo != null){
            todoList.clear()
            todoList.addAll(todo)
            println(todoList)
        } else {
            todoList.clear()
        }
    }

    inner class ViewHolder(private val binding: TodoitemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun setTodoListUI(todo: TodoEntity, position: Int){
            binding.todoDescription.text = todo.description
            binding.todoId.text = "$position"
            binding.todoDate.text = todo.date
        }
    }
}