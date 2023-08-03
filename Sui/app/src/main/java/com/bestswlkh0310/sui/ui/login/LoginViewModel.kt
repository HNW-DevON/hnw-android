package com.bestswlkh0310.sui.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.sui.data.AuthRepository
import com.bestswlkh0310.sui.data.req.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginState(
    val id: String = "",
    val pw: String = ""
)

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()
    fun updateId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun updatePw(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    fun login() {
        viewModelScope.launch {
            with(_state.value) {
                try {
                    AuthRepository.login(
                        LoginRequest(
                            id,
                            pw
                        )
                    )
                    _isSuccess.value = true
                } catch (e: Exception) {
                    _isSuccess.value = false
                }
            }
        }
    }
}