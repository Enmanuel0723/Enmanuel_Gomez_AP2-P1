package com.example.enmanuel_gomez_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cheques")
data class ChequeEntity(
    @PrimaryKey(autoGenerate = true)
    val chequeId: Int = 0,
    val numero: Int = 0,
    val beneficiario: String = "",
    val monto: Double = 0.0
)
