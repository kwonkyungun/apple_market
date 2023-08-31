package com.example.apple_market

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apple_market.databinding.MainItemBinding
import java.text.DecimalFormat

class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    // 인터페이스는 Main의 183번째 줄 OnClick의 함수를 호출하기 위해 만들어줌
    interface ItemClick {
        fun onClick(view : View, position : Int)
    }
    interface ItemLongClick {
        fun LongCick(view : View, position : Int)
//        notifyDataSetChanged()
    }

    var itemLongClick: ItemLongClick? = null
    var itemClick : ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener{
            itemLongClick?.LongCick(it,position)
            return@setOnLongClickListener true
        }

        holder.iconImageView.setImageResource(mItems[position].aIcon)
        holder.title.text = mItems[position].aTitle
        holder.Address.text = mItems[position].aAddress

        val test = DecimalFormat("#,###")
        holder.price.text = test.format(mItems[position].aPrice)+"원"
        holder.chat.text = mItems[position].aChat.toString()

        holder.like.text = mItems[position].aLike.toString()

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {


        val iconImageView = binding.iconItem
        val title = binding.title
        val Address = binding.addressContent
        val price = binding.price
        val chat = binding.chatText
        val like = binding.likeText



    }
}