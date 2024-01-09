package com.example.konsercb.model


import EntryPersonViewModel
import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.konsercb.AplikasiKonser

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKonser().container.repositoriEvent)
        }
        initializer {
            EntryEventViewModel(aplikasiKonser().container.repositoriEvent)
        }
        initializer {
            EntryPersonViewModel(aplikasiKonser().container.repositoriPerson)
        }
        initializer {
            DetailEventViewModel(createSavedStateHandle(), aplikasiKonser().container.repositoriEvent)
        }
        initializer {
            EditEventViewModel(createSavedStateHandle(), aplikasiKonser().container.repositoriEvent)
        }
    }
}

/**
 * Extension function for creating [AplikasiKonser] instance from [CreationExtras].
 */
fun CreationExtras.aplikasiKonser(): AplikasiKonser =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKonser)
