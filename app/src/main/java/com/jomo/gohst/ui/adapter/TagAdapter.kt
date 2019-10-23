package com.jomo.gohst.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jomo.gohst.R
import com.jomo.gohst.data.model.Tag
import com.jomo.gohst.databinding.TagLineItemsBinding

class TagAdapter(private val mContext: Context, private val tagList: List<Tag>):
    RecyclerView.Adapter<TagAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.tag_line_items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tagItem = tagList[position]
        holder.mBinding.data = tagItem
        holder.mBinding.executePendingBindings()
    }


    inner class ViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mBinding: TagLineItemsBinding = DataBindingUtil.bind(itemView)!!
    }
}
