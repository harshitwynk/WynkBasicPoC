package com.example.harshitjain.wynkbasicpoc.ui

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.harshitjain.wynkbasicpoc.R
import com.example.harshitjain.wynkbasicpoc.db.Item


import com.example.harshitjain.wynkbasicpoc.ui.GridFragment.OnListFragmentInteractionListener
import com.example.harshitjain.wynkbasicpoc.ui.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.item_grid.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class GridAdapter(private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var mValues: List<Item>? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Item
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    fun setData(items: List<Item>?) {
        mValues = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mValues ?: return
        val item = mValues?.get(position)
        holder.mTvTitle.text = item?.title
        holder.mTvIndex.text = position.toString()
        Glide.with(holder.mIvImage.context).load(mValues?.get(position)?.smallImage).into(holder.mIvImage)
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTvTitle: TextView = mView.tv_title
        val mTvIndex: TextView = mView.tv_index
        val mIvImage: AppCompatImageView = mView.iv_image
    }
}
