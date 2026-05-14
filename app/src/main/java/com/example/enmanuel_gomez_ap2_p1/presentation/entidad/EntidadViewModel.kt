package com.example.enmanuel_gomez_ap2_p1.presentation.entidad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enmanuel_gomez_ap2_p1.domain.model.Cheque
import com.example.enmanuel_gomez_ap2_p1.domain.usecases.DeleteChequeUseCase
import com.example.enmanuel_gomez_ap2_p1.domain.usecases.GetChequesUseCase
import com.example.enmanuel_gomez_ap2_p1.domain.usecases.SaveChequeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChequeViewModel @Inject constructor(
    private val getChequesUseCase: GetChequesUseCase,
    private val saveChequeUseCase: SaveChequeUseCase,
    private val deleteChequeUseCase: DeleteChequeUseCase
) : ViewModel() {


    var filtroNumero by mutableStateOf("")
    var filtroBeneficiario by mutableStateOf("")

    val chequesFiltrados = combine(
        getChequesUseCase(),
        snapshotFlow { filtroNumero },
        snapshotFlow { filtroBeneficiario }
    ) { cheques, numero, beneficiario ->
        cheques.filter { cheque ->
            (numero.isBlank() || cheque.numero.toString().contains(numero.trim())) &&
            (beneficiario.isBlank() || cheque.beneficiario.contains(beneficiario.trim(), ignoreCase = true))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    var chequeId by mutableStateOf<Int?>(null)
        private set
    var numero by mutableStateOf("")
    var beneficiario by mutableStateOf("")
    var monto by mutableStateOf("")


    var numeroError by mutableStateOf<String?>(null)
        private set
    var beneficiarioError by mutableStateOf<String?>(null)
        private set
    var montoError by mutableStateOf<String?>(null)
        private set

    fun loadCheque(id: Int?) {
        chequeId = null
        numero = ""
        beneficiario = ""
        monto = ""
        numeroError = null
        beneficiarioError = null
        montoError = null

        if (id == null) return

        viewModelScope.launch {
            getChequesUseCase.getById(id)?.let { cheque ->
                chequeId = cheque.chequeId
                numero = cheque.numero.toString()
                beneficiario = cheque.beneficiario
                monto = cheque.monto.toString()
            }
        }
    }

    fun save(onSuccess: () -> Unit) {
        if (!validate()) return
        viewModelScope.launch {
            saveChequeUseCase(
                Cheque(
                    chequeId = chequeId,
                    numero = numero.trim().toInt(),
                    beneficiario = beneficiario.trim(),
                    monto = monto.trim().toDouble()
                )
            )
            onSuccess()
        }
    }

    fun delete(cheque: Cheque) {
        viewModelScope.launch { deleteChequeUseCase(cheque) }
    }

    private fun validate(): Boolean {
        var valid = true

        when {
            numero.trim().isEmpty() -> { numeroError = "El número es requerido"; valid = false }
            numero.trim().toIntOrNull() == null -> { numeroError = "Debe ser un número entero"; valid = false }
            else -> numeroError = null
        }

        when {
            beneficiario.trim().isEmpty() -> { beneficiarioError = "El beneficiario es requerido"; valid = false }
            else -> beneficiarioError = null
        }

        val montoDouble = monto.trim().toDoubleOrNull()
        when {
            monto.trim().isEmpty() -> { montoError = "El monto es requerido"; valid = false }
            montoDouble == null -> { montoError = "Debe ser un número válido"; valid = false }
            montoDouble <= 0 -> { montoError = "El monto debe ser mayor a 0"; valid = false }
            else -> montoError = null
        }

        return valid
    }
}
