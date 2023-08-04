package com.bestswlkh0310.sui.ui.join

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.sui.data.AuthRepository
import com.bestswlkh0310.sui.data.RetrofitClient
import com.bestswlkh0310.sui.data.RetrofitService
import com.bestswlkh0310.sui.data.req.JoinRequest
import com.bestswlkh0310.sui.data.req.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginState(
    val id: String = "",
    val pw: String = "",
    val name: String = ""
)

class JoinViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val s = MutableStateFlow(false)
    val ss = s.asStateFlow()

    fun updateId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun updatePw(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun join() {
        viewModelScope.launch {
            try {
                RetrofitClient.retrofit.join(
                    with(_state.value) {
                        JoinRequest(
                            id, name, pw
                        )
                    }
                )
                s.value = true
            } catch (e: Exception) {
                s.value = true
            }

        }
    }

}