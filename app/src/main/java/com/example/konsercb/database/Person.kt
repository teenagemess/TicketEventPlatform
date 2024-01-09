package com.example.konsercb.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblPerson")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val namaperson : String,
    val emailperson : String,
    val identitas : String,
    val nohp : String,
)
