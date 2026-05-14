package com.example.enmanuel_gomez_ap2_p1.data.repository

import com.example.enmanuel_gomez_ap2_p1.data.local.dao.ChequeDao
import com.example.enmanuel_gomez_ap2_p1.data.local.entity.ChequeEntity
import com.example.enmanuel_gomez_ap2_p1.domain.model.Cheque
import com.example.enmanuel_gomez_ap2_p1.domain.repository.ChequeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChequeRepositoryImpl @Inject constructor(
    private val dao: ChequeDao
) : ChequeRepository {

    override fun getCheques(): Flow<List<Cheque>> =
        dao.getAll().map { list -> list.map { it.toModel() } }

    override suspend fun getChequeById(id: Int): Cheque? =
        dao.getById(id)?.toModel()

    override suspend fun save(cheque: Cheque) =
        dao.save(cheque.toEntity())

    override suspend fun delete(cheque: Cheque) =
        dao.delete(cheque.toEntity())
}

private fun ChequeEntity.toModel() = Cheque(
    chequeId = chequeId,
    numero = numero,
    beneficiario = beneficiario,
    monto = monto
)

private fun Cheque.toEntity() = ChequeEntity(
    chequeId = chequeId ?: 0,
    numero = numero,
    beneficiario = beneficiario,
    monto = monto
)
