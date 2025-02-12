package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofit.model.DemoData
import com.example.retrofit.network.Demo
import com.example.retrofit.network.Network
import com.example.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //B4: tạo coroutine để lấy data từ server trả về
        CoroutineScope(Dispatchers.IO).launch {
          val request = Network.createRetrofit("https://jsonplaceholder.typicode.com/").create(Demo::class.java).getPost(10)
            if (request.isSuccessful){
                request.body()?.userId // lấy data từ server ô
            }

        }
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
        Greeting("Android")
    }
}