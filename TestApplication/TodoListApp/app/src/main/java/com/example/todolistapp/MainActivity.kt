package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.adapter.TodoListAdapter
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.roomdatabase.TodoDatabase
import com.example.todolistapp.roomdatabase.TodoEntity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var adapter: TodoListAdapter
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        //ViewModel
//        val todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        //RecyclerView
        val recyclerView = binding.todoRecyclerView
        setRecyclerView(recyclerView)

        // date LiveData 변경 감지
        todoViewModel.date.observe(this, androidx.lifecycle.Observer {
            Log.d("date", it.toString())
            todoViewModel.getAllByDate(it)
                .observe(this,{todoList ->
                    Log.d("todoList", todoList.toString())
                    if(todoList != null){
                        // Adapter 데이터 갱신
                        adapter.setTodoList(todoList)
                        adapter.notifyDataSetChanged()
                    }
                })
        })



        binding.calendarView.setOnDateChangeListener{ _, year, month, dayOfMonth ->
            val dateStr = "$year-${month+1}-$dayOfMonth"
            todoViewModel.updateDate(dateStr)
        }

        binding.todoAddBtn.setOnClickListener{
            val description = binding.todoEditText.text.toString()
            GlobalScope.launch(Dispatchers.IO) {
                val date = todoViewModel.date.value!!
                todoViewModel.insert(TodoEntity(null,description, date))
            }
        }
    }

    private fun setRecyclerView(recyclerView: RecyclerView){
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = TodoListAdapter()
        recyclerView.adapter = adapter

        //처음 시작 시 오늘 날짜를 설정해줌.
        val dateOfToday = getTodayOfDate()
        todoViewModel.updateDate(dateOfToday)
    }

    private fun getTodayOfDate(): String {
        // 캘린더뷰의 날씨 가져옴
        val dateOfTodayLong = binding.calendarView.date
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(dateOfTodayLong)
    }

}