package com.example.konsercb.splashscreen

import DestinasiHome
import android.annotation.SuppressLint
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.konsercb.R
import com.example.konsercb.navigasi.DestinasiNavigasi
import kotlinx.coroutines.delay


object SplashS : DestinasiNavigasi {
    override val route = "splash"
    override val titleRes = R.string.titleapp
}
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = NavHostController(this)
            SplashScreenDah(navController)
        }
    }
}

@Composable
fun SplashScreenDah(navController: NavController) {
    val logo = painterResource(id = R.drawable.logo)
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(DestinasiHome.route)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#1f1f95"))),
    ) {
        Image(painter = logo, contentDescription = "", modifier = Modifier.width(100.dp).height(60.dp))
    }
}