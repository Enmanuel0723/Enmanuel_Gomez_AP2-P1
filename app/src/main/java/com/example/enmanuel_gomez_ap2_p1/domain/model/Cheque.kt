package com.example.enmanuel_gomez_ap2_p1.domain.model

data class Cheque(
    val chequeId: Int? = null,
    val numero: Int = 0,
    val beneficiario: String = "",
    val monto: Double = 0.0
)
