package com.jomo.gohst.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomo.gohst.data.model.Ghost
import io.reactivex.Single

@Dao
interface GhostDao {

    @Query("SELECT * FROM ghost")
    fun queryGhosts(): Single<List<Ghost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGhost(ghost: Ghost)

    // TODO: Select by tag or by night

}
