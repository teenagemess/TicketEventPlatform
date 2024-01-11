package com.example.konsercb.login

import DestinasiHome
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.konsercb.R
import com.example.konsercb.navigasi.DestinasiNavigasi

object DestinasiLogin : DestinasiNavigasi {
    override val route = "login"
    override val titleRes = R.string.titleapp
}

@Composable
fun HalamanLogin(
    onLoginButtonClicked: (String, String) -> Unit,
    onCancelButtonClicked: () -> Unit = {},
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val logo = painterResource(R.drawable.logo)
    val email_ic = painterResource(R.drawable.ic_mail)
    val lock_ic = painterResource(R.drawable.ic_lock)
    Scaffold(modifier = Modifier.padding(35.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .height(42.dp)
                        .width(40.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(Color(android.graphics.Color.parseColor("#1f1f95")))) {
                    Image(painter = logo, contentDescription = "", modifier = Modifier.height(30.dp))
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Empatix", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#1f1f95")))
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Masuk", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Email", fontSize = 18.sp)
            OutlinedTextField(
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                value = username,
                onValueChange = { username = it },
                label = { Text("Masukkan email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )
            Text(text = "Password", fontSize = 18.sp)
            OutlinedTextField(
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                value = password,
                onValueChange = { password = it },
                label = { Text("Masukkan Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
                ) {
                Text(text = "Lupa Password", fontSize = 18.sp, color = Color(android.graphics.Color.parseColor("#1f1f95")))
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { onLoginButtonClicked(username, password)},
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95"))),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
            ) {
                Text("Masuk Sebagai Admin")
            }
                Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = onCancelButtonClicked,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
            ) {
                Text("Cancel", color = Color(android.graphics.Color.parseColor("#1f1f95")))
            }
        }


    }
}