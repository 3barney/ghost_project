package com.jomo.gohst.ui.handlers

import android.content.Context
import android.util.Log
import com.jomo.gohst.data.model.Tag
import com.jomo.gohst.ui.GhostViewModel

class TagHandler(
    mContext: Context,
    tagItem: Tag,
    ghostViewModel: GhostViewModel
) {

    private val context = mContext
    private val tagItem = tagItem
    private val ghostViewModel = ghostViewModel

    fun onTagItemClicked() {
        ghostViewModel.filterGhosts(tagItem.tagName)
        Log.e("TGGGGG", ghostViewModel.toString())
    }
}