package com.jomo.gohst.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jomo.gohst.R
import com.jomo.gohst.data.model.Ghost
import com.jomo.gohst.databinding.DreamLayoutBinding

class DreamsAdapter(private val mContext: Context, private val ghostList: List<Ghost>):
    RecyclerView.Adapter<DreamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.dream_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ghostList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ghost = ghostList[position]
        holder.mBinding.data = ghost
        holder.mBinding.executePendingBindings()
    }


    inner class ViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mBinding: DreamLayoutBinding = DataBindingUtil.bind(itemView)!!
    }
}
