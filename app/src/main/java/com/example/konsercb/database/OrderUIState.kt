package com.example.konsercb.database


data class OrderUIState(
    val jumlah: Int = 0,
    val rasa: String = "",
    val harga: String = "",
    val namaperson : String = "",
    val emailperson : String = "",
    val identitas : String = "",
    val nohp : String = "",
)