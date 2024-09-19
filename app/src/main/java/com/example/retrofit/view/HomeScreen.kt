package com.example.retrofit.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.viewmodel.DemoViewModel

@Composable
fun HomeScreen(demoViewModel: DemoViewModel = viewModel()){

    val demoState = demoViewModel.abc.collectAsState().value
    var count by remember { mutableIntStateOf(0) }
    var count1 by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = count) {//0
        demoViewModel.callAPI()
    }

    DisposableEffect(true) {
        count++
        onDispose {
            count = 0
            // don dep o day
        }
    }



    DerivedOfState()

    /*Box(Modifier.fillMaxSize()) {
        Button(modifier = Modifier.fillMaxWidth().align(Alignment.Center), onClick = {
            scope.launch {
                demoViewModel.callAPI()
            }
        }) {
            Text(text = demoState.toString())// 100
        }
    }*/

}

@Composable
fun DerivedOfState() {
    val stateLazyColumn = rememberLazyListState()
    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        LazyColumn(state = stateLazyColumn, content = {
            items(100) {
                Text(text = "thanh")
            }
        })
        val showButton by remember {
            derivedStateOf {
                stateLazyColumn.firstVisibleItemIndex > 0
            }
        }

        Text(text = "hoai$showButton")
        if (showButton){
            Button(modifier = Modifier.size(50.dp).padding(start =  30.dp), onClick = {

            }) {
                Text(text = "demo")

            }
        }
    }
}
