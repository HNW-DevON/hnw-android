package com.bestswlkh0310.sui.ui.theme.ui.onboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

data class LoginState(
    val id: String = "",
    val pw: String = ""
)

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
    fun updateId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun updatePw(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }
}