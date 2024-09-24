package com.example.myapplication.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.domain.entity.ListElement
import com.example.myapplication.main.vm.MainState
import com.example.myapplication.main.vm.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (val st = state) {
            is MainState.Content -> {
                ContentState(list = st.list)
            }

            is MainState.Error -> {
                ErrorState(st.message)
            }

            MainState.Loading -> {
                Text(text = "Загрузка...")
            }
        }
    }
}

@Composable
fun ErrorState(message: String) {
    Text(modifier = Modifier.fillMaxWidth(), text = message)
}

@Composable
fun ContentState(list: List<ListElement>) {
    Column(modifier = Modifier.fillMaxSize()) {
        list.forEach { element ->
            Text(text = element.title)
        }
    }
}