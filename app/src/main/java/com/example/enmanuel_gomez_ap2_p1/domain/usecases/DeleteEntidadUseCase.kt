package com.example.enmanuel_gomez_ap2_p1.domain.usecases

import com.example.enmanuel_gomez_ap2_p1.domain.model.Cheque
import com.example.enmanuel_gomez_ap2_p1.domain.repository.ChequeRepository
import javax.inject.Inject

class DeleteChequeUseCase @Inject constructor(
    private val repository: ChequeRepository
) {
    suspend operator fun invoke(cheque: Cheque) = repository.delete(cheque)
}
