package com.example.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PeopleCache(
    @PrimaryKey
    val idRom: Int,
    val gender: String,
    val hairColor: String,
    val height: Double,
    val homeworld: String,
    val id: Int,
    val image: String,
    val mass: Int,
    val name: String,
    val skinColor: String,
)