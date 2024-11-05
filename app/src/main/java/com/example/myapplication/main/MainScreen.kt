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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import coil.compose.AsyncImage
import com.example.domain.entity.ListElementEntity
import com.example.myapplication.details.DetailsScreenRoute
import com.example.myapplication.main.vm.MainState
import com.example.myapplication.main.vm.MainViewModel
import com.example.myapplication.ui.view.Like
import com.example.myapplication.workers.MyWorker
import org.koin.androidx.compose.koinViewModel
import java.util.concurrent.TimeUnit

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (val st = state) {
            is MainState.Content -> {
                ContentState(
                    navController = navController,
                    list = st.list
                ) { element, like ->
                    viewModel.like(element, like)
                }
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
fun ContentState(
    navController: NavController,
    list: List<ListElementEntity>,
    onLike: (ListElementEntity, Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        list.forEach { element ->
            val ctx = LocalContext.current
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(DetailsScreenRoute(element.id))
                        WorkManager
                            .getInstance(ctx)
                            .enqueueUniquePeriodicWork(
                                "some_name",
                                ExistingPeriodicWorkPolicy.KEEP,
                                PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.HOURS).build()
                            )
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
                val like = remember { mutableStateOf(element.like) }
                Like(modifier = Modifier, like = like)
                val isFirstLike = remember { mutableStateOf(true) }
                LaunchedEffect(like.value) {
                    if (isFirstLike.value) {
                        isFirstLike.value = false
                    } else {
                        onLike.invoke(element, like.value)
                    }
                }
            }
        }
    }
}