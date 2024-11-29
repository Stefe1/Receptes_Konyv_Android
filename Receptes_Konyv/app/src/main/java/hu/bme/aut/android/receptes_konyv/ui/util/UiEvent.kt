package hu.bme.aut.android.receptes_konyv.ui.util

import hu.bme.aut.android.receptes_konyv.ui.model.UiText

sealed class UiEvent {
    object Success: UiEvent()
    data class Failure(val message: UiText): UiEvent()
}