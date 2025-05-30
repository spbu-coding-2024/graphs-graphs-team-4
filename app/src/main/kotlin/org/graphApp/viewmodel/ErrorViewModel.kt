package org.graphApp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class ErrorViewModel {
    private val _isError = mutableStateOf(false)

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    private val _errorId = mutableStateOf(0)
    val errorId: State<Int> = _errorId

    fun showError(message: String) {
        _errorMessage.value = message
        _errorId.value += 1
        _isError.value = true
    }
}
