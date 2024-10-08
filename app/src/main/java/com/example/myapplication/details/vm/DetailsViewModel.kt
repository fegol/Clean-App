package com.example.myapplication.details.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.myapplication.details.DetailsScreenRoute
import com.example.myapplication.main.vm.MainState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            val title = when (val currentState = _state.value) {
                is DetailsState.Content -> {
                    currentState.title
                }

                is DetailsState.Error -> {
                    currentState.title
                }

                DetailsState.Loading -> {
                    "Ошибка"
                }
            }
            _state.emit(DetailsState.Error(title, throwable.message ?: "Ошибочка"))
        }
    }

    val state: StateFlow<DetailsState>
        get() = _state

    init {
        Timber.e(savedStateHandle.toRoute<DetailsScreenRoute>().toString())
    }

}