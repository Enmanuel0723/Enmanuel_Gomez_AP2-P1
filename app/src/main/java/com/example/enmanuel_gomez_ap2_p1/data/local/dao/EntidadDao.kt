package com.example.enmanuel_gomez_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.enmanuel_gomez_ap2_p1.data.local.entity.ChequeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChequeDao {

    @Upsert
    suspend fun save(cheque: ChequeEntity)

    @Delete
    suspend fun delete(cheque: ChequeEntity)

    @Query("SELECT * FROM Cheques ORDER BY chequeId DESC")
    fun getAll(): Flow<List<ChequeEntity>>

    @Query("SELECT * FROM Cheques WHERE chequeId = :id")
    suspend fun getById(id: Int): ChequeEntity?
}
