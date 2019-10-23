package com.jomo.gohst.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "ghost")
@TypeConverters(DateConverter::class)
data class Ghost(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tag: String,
    val Night: Date,
    val description: String
)