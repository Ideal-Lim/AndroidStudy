package com.example.cardviewtest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.recyclerviewMain

        var layoutManager = LinearLayoutManager(this).apply {
            this.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView.layoutManager = this
        }

        val customAdapter = CustomAdapter()
        recyclerView.adapter = customAdapter


        binding.goFirstBtn.setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
        }

        binding.goMidBtn.setOnClickListener {
            recyclerView.smoothScrollToPosition(customAdapter.cardDataList.size / 2)
        }

        binding.goLastBtn.setOnClickListener {
            recyclerView.smoothScrollToPosition(customAdapter.cardDataList.size - 1)
        }
    }
}