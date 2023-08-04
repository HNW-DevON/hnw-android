package com.bestswlkh0310.sui.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.sui.data.RetrofitClient
import com.bestswlkh0310.sui.data.res.CompResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    val d = MutableStateFlow<List<CompResponse>>(arrayListOf())

    private val _stat = MutableStateFlow<List<CompResponse>>(arrayListOf())
    val stat = _stat.asStateFlow()


    private val _clicked = MutableStateFlow<CompResponse?>(
        CompResponse(
            "기다려주세요",
            "기다려주세요",
            "기다려주세요",
            "기다려주세요",
            "기다려주세요",
            "기다려주세요",
            "기다려주세요",
        )
    )
    val clicked = _clicked.asStateFlow()

    fun updateSearch(it: String) {
        _state.value = it
        get()
    }

    fun init() {
        viewModelScope.launch {
            d.value = RetrofitClient.retrofit.ge()
        }
    }

    private fun get() {
        val e = d.value.filter { it.name.contains(_state.value) }.sortedBy { a -> a.name }
        val b = e.slice(0 until (if (e.size >= 10) 10 else e.size))
        _stat.value = b
    }

    fun getComp(name: String) {
        viewModelScope.launch {
            _clicked.value = RetrofitClient.retrofit.get(name)
        }
    }
}