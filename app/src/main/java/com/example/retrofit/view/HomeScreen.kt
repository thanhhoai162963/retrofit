package com.example.retrofit.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.viewmodel.DemoViewModel

@Composable
fun HomeScreen(demoViewModel: DemoViewModel = viewModel()){

    val demoState = demoViewModel.abc.collectAsState().value

    Button(modifier = Modifier.fillMaxWidth(), onClick = {
        demoViewModel.callAPI()
    }) {
        Text(text = demoState.toString())// 100
    }
}