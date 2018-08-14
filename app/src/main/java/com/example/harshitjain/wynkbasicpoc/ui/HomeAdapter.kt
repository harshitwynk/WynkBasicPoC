package com.example.harshitjain.wynkbasicpoc.ui

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.harshitjain.wynkbasicpoc.R
import com.example.harshitjain.wynkbasicpoc.db.Item

class HomeAdapter(val context: Context?) : RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    private var items: List<Item>? = null

    private val inflator = LayoutInflater.from(context)

    fun setData(items: List<Item>?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = inflator.inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tvTitle.text = items?.get(position)?.title
        holder.tvSubTitle.text = items?.get(position)?.subtitle
        holder.tvIndex.text = position.toString()
        Glide.with(holder.imageView.context).load(items?.get(position)?.smallImage).into(holder.imageView)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imageView: AppCompatImageView
        lateinit var tvTitle: TextView
        lateinit var tvSubTitle: TextView
        lateinit var tvIndex: TextView

        init {
            imageView = itemView.findViewById(R.id.iv_song)
            tvTitle = itemView.findViewById(R.id.tv_item_title)
            tvSubTitle = itemView.findViewById(R.id.tv_item_subTitle)
            tvIndex = itemView.findViewById(R.id.tv_index)
        }
    }
}