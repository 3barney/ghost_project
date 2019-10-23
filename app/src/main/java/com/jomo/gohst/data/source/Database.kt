package com.jomo.gohst.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jomo.gohst.data.model.Ghost

@Database(entities = [Ghost::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun ghostDao(): GhostDao
}