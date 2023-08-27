package com.example.apple_market

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apple_market.databinding.ItemBinding
import java.text.DecimalFormat

class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }//후발대 과제 추가 요망

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].aIcon)
        holder.title.text = mItems[position].aTitle
        holder.Address.text = mItems[position].aAddress
        holder.price.text = mItems[position].aPrice.toString()
        holder.chat.text = mItems[position].aChat.toString()
        holder.like.text = mItems[position].aLike.toString()

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {


        val iconImageView = binding.iconItem
        val title = binding.title
        val Address = binding.addressContent
        val price = binding.price
        val chat = binding.chatText
        val like = binding.likeText



    }
}