package com.example.cardviewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cardviewtest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getSerializableExtra("data") as CardData

        val binding = ActivityDetailBinding.inflate(layoutInflater).apply {
            this.detailImageView.setImageResource(data.image)
            this.detailTitle.text = data.title
            this.detailDetail.text = data.detail
        }
        setContentView(binding.root)

    }
}