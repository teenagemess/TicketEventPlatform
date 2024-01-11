package com.example.konsercb

import android.app.usage.UsageEvents.Event
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.konsercb.navigasi.EventApp
import com.example.konsercb.theme.KonsercbTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val images = listOf(
            "https://w0.peakpx.com/wallpaper/324/121/HD-wallpaper-new-2023-movie-john-wick-4-poster-movie.jpg",
            "https://w0.peakpx.com/wallpaper/694/975/HD-wallpaper-oppenheimer-movie-2023.jpg",
            "https://w0.peakpx.com/wallpaper/640/109/HD-wallpaper-transformers-rise-of-the-beasts-movie-poster-2023.jpg",
            "https://w0.peakpx.com/wallpaper/542/826/HD-wallpaper-optimus-prime-dragon-movie-poster-transformers-transformers-5-transformers-last-knight.jpg"
        )
        setContent {
            KonsercbTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EventApp()
                }
            }
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
    KonsercbTheme {
        Greeting("Android")
    }
}