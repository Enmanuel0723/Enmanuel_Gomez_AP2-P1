package com.example.enmanuel_gomez_ap2_p1.domain.usecases

import com.example.enmanuel_gomez_ap2_p1.domain.repository.ChequeRepository
import javax.inject.Inject

class GetChequesUseCase @Inject constructor(
    private val repository: ChequeRepository
) {
    operator fun invoke() = repository.getCheques()
    suspend fun getById(id: Int) = repository.getChequeById(id)
}
