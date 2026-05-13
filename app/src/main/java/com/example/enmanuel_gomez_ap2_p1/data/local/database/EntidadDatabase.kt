package com.example.enmanuel_gomez_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.enmanuel_gomez_ap2_p1.data.local.dao.EntidadDao
import com.example.enmanuel_gomez_ap2_p1.data.local.entity.EntidadEntity

@Database(
    entities = [EntidadEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EntidadDatabase : RoomDatabase() {
    abstract fun entidadDao(): EntidadDao
}
