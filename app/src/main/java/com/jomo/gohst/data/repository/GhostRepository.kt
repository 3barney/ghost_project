package com.jomo.gohst.data.repository

import android.util.Log
import com.jomo.gohst.data.model.Ghost
import com.jomo.gohst.data.source.GhostDao
import io.reactivex.Observable
import javax.inject.Inject

class GhostRepository @Inject constructor(
    val ghostDao: GhostDao
) {

    fun insertGhost(ghost: Ghost) {
        return ghostDao.insertGhost(ghost)
    }

    fun getGhosts(): Observable<List<Ghost>> {
        return ghostDao.queryGhosts()
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }
}