package com.example.enmanuel_gomez_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.enmanuel_gomez_ap2_p1.data.local.dao.ChequeDao
import com.example.enmanuel_gomez_ap2_p1.data.local.entity.ChequeEntity

@Database(
    entities = [ChequeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChequeDatabase : RoomDatabase() {
    abstract fun chequeDao(): ChequeDao
}
