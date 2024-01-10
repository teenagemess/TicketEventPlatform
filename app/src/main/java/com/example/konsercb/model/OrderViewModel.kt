package com.example.konsercb.model

import androidx.lifecycle.ViewModel
import com.example.konsercb.database.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setContact(namaperson: String, nohp: String, email: String, identitas: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                namaperson = namaperson,
                nohp = nohp,
                emailperson = email,
                identitas = identitas
            )

        }
    }
}