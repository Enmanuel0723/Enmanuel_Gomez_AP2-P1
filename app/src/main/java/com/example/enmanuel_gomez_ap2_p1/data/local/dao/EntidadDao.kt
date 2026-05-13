package com.example.enmanuel_gomez_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.enmanuel_gomez_ap2_p1.data.local.entity.EntidadEntity

@Dao
interface EntidadDao {

    @Upsert
    suspend fun save(entidad: EntidadEntity)
}
