package com.example.submisiondicoding.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submisiondicoding.data.MortyRepository
import com.example.submisiondicoding.model.OrderReward
import com.example.submisiondicoding.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MortyRepository
) : ViewModel() {
    // set data
    private val _uiState: MutableStateFlow<UiState<List<OrderReward>>> = MutableStateFlow(UiState.Loading)

    // get data
    val uiState: StateFlow<UiState<List<OrderReward>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            // catch untuk mengambil data and menangani error
            repository.getAllRewards()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}