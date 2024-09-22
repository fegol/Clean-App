package com.example.myapplication.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ListRepositoryImpl
import com.example.domain.usecase.ListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val useCase = ListUseCase(ListRepositoryImpl())

    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _state.emit(MainState.Error(throwable.message ?: "Ошибочка"))
        }
    }

    val state: StateFlow<MainState>
        get() = _state


    init {
        viewModelScope.launch(context = exceptionHandler) {
            val result = useCase.execute(Unit)
            _state.emit(MainState.Content(result))
        }
    }
}