package com.example.enmanuel_gomez_ap2_p1.domain.repository

import com.example.enmanuel_gomez_ap2_p1.domain.model.Cheque
import kotlinx.coroutines.flow.Flow

interface ChequeRepository {
    fun getCheques(): Flow<List<Cheque>>
    suspend fun getChequeById(id: Int): Cheque?
    suspend fun save(cheque: Cheque)
    suspend fun delete(cheque: Cheque)
}
