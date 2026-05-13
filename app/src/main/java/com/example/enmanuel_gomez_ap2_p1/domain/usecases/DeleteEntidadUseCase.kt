package com.example.enmanuel_gomez_ap2_p1.domain.usecases

import com.example.enmanuel_gomez_ap2_p1.domain.repository.EntidadRepository
import javax.inject.Inject

class DeleteEntidadUseCase @Inject constructor(
    private val repository: EntidadRepository
) {

}
