package com.example.enmanuel_gomez_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Borrame")
data class BorrameEntity(
    @PrimaryKey
    val borrameId: Int? = null
)
