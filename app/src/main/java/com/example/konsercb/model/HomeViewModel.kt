package com.example.konsercb.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.konsercb.database.Event
import com.example.konsercb.database.Person
import com.example.konsercb.repositori.RepositoriEvent
import com.example.konsercb.repositori.RepositoriPerson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositoriSiswa: RepositoriEvent, private val repositoriPerson: RepositoriPerson) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriSiswa.getAllEventStream().filterNotNull()
        .map { HomeUiState(listEvent = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )


    data class HomeUiState(
        val listEvent: List<Event> = listOf()
    )

    val personUiState: StateFlow<PersonUiState> = repositoriPerson.getAllPersonStream().filterNotNull()
        .map { PersonUiState(listPerson = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = PersonUiState()
        )


    data class PersonUiState(
        val listPerson: List<Person> = listOf()
    )
}