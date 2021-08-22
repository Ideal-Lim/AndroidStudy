package com.example.cardviewtest

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardviewtest.databinding.CardLayoutBinding


class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val cardDataList : ArrayList<CardData> = arrayListOf(
        CardData("one","Item one", R.drawable.beach),
        CardData("two","Item two", R.drawable.wave),
        CardData("three", "Item three", R.drawable.young_woman),
        CardData("four", "Item four", R.drawable.young_woman),
        CardData("five", "Item five", R.drawable.beach)
    )


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val itemTitle: TextView = binding.itemTitle
        val itemImage: ImageView = binding.itemImage
        val itemDetail: TextView = binding.itemDetail

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = cardDataList[position].title
        holder.itemDetail.text = cardDataList[position].detail
        holder.itemImage.setImageResource(cardDataList[position].image)
        holder.itemView.setOnClickListener{
//          Toast.makeText(it.context,"클릭", Toast.LENGTH_SHORT).show()
            val data = cardDataList[position]

            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("data", data)
            }
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }

}