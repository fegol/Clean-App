package com.example.myapplication.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.domain.entity.ListElement
import com.example.myapplication.details.DetailsScreenRoute
import com.example.myapplication.main.vm.MainState
import com.example.myapplication.main.vm.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (val st = state) {
            is MainState.Content -> {
                ContentState(
                    navController = navController,
                    list = st.list
                )
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
fun ContentState(navController: NavController, list: List<ListElement>) {
    Column(modifier = Modifier.fillMaxSize()) {
        list.forEach { element ->
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate(DetailsScreenRoute(element.id))
                    }
            ) {
                AsyncImage(
                    modifier = Modifier.size(136.dp),
                    model = element.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = element.title)
            }
        }
    }
}