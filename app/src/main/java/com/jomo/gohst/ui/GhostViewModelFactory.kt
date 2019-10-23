package com.jomo.gohst.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GhostViewModelFactory @Inject constructor(
    private val ghostViewModel: GhostViewModel
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GhostViewModel::class.java!!)) {
            return ghostViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}