package com.example.roomsiswa.ui

import DetailPerson
import EntryPersonViewModel
import UIStatePerson
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.konsercb.R
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import com.example.konsercb.navigasi.EventTopAppBar
import com.example.konsercb.ui.DestinasiEntry
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

object DestinasiEntryPerson : DestinasiNavigasi {
    override val route = "entry_person"
    override val titleRes = R.string.data_diri
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanPelanggan(
    onSubmitButtonClicked: (String, String, String, String) -> Unit,
    onCancelButtonClicked: () -> Unit,
    onConfirmButtonClicked: (Int) -> Unit,
    navigateBack: () -> Unit,
    onSelectionChanged: (String) -> Unit,
    pilihanRasa: List<String>
) {
    var namaperson by remember { mutableStateOf("") }
    var nohp by remember { mutableStateOf("") }
    var emailperson by remember { mutableStateOf("") }
    var identitas by remember { mutableStateOf("") }

    var rasaYgDipilih by rememberSaveable {
        mutableStateOf("")
    }
    var textJmlBeli by remember {
        mutableStateOf("")
    }

    val event = painterResource(R.drawable.person)

    Scaffold(topBar = {
        EventTopAppBar(
            title = stringResource(DestinasiEntryPerson.titleRes),
            canNavigateBack = true,
            navigateUp = navigateBack
        )
    }
    ) { innerPadding ->
        LazyColumn() {
            item {
                Column(modifier = Modifier.padding(innerPadding)) {
                    Column(
                        modifier = Modifier.padding(25.dp),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = event, contentDescription = "")
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(
                                text = "Data Diri",
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(0.dp))
                        Divider(color = Color.LightGray)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            fontSize = 16.sp,
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("Nama Lengkap")
                                }
                                withStyle(SpanStyle(color = Color.Red)) {
                                    append(" *")
                                }
                            }
                        )
                        OutlinedTextField(
                            value = namaperson,
                            onValueChange = { namaperson = it },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            singleLine = true

                        )
                        Text(
                            fontSize = 16.sp,
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("No. Telepon")
                                }
                                withStyle(SpanStyle(color = Color.Red)) {
                                    append(" *")
                                }
                            }
                        )
                        OutlinedTextField(
                            value = nohp,
                            onValueChange = { nohp = it },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            singleLine = true
                        )
                        Text(
                            fontSize = 16.sp,
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("Email")
                                }
                                withStyle(SpanStyle(color = Color.Red)) {
                                    append(" *")
                                }
                            }
                        )
                        Text(text = "e-Tiket akan dikirmkan ke email ini", color = Color.LightGray)
                        OutlinedTextField(
                            value = emailperson,
                            onValueChange = { emailperson = it },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            singleLine = true
                        )
                        Text(
                            fontSize = 16.sp,
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("No KTP")
                                }
                                withStyle(SpanStyle(color = Color.Red)) {
                                    append(" *")
                                }
                            }
                        )
                        OutlinedTextField(
                            value = identitas,
                            onValueChange = { identitas = it },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            singleLine = true
                        )
                        //RadioButton
                        pilihanRasa.forEach { item ->
                            Row(
                                modifier = Modifier.selectable(
                                    selected = rasaYgDipilih == item,
                                    onClick = {
                                        rasaYgDipilih = item
                                        onSelectionChanged(item)
                                    }
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(selected = rasaYgDipilih == item, onClick = {
                                    rasaYgDipilih = item
                                    onSelectionChanged(item)
                                }
                                )
                                Text(item)
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            //Jumlah Order
                            OutlinedTextField(
                                value = textJmlBeli,
                                onValueChange = {
                                    textJmlBeli = it
                                },
                                singleLine = true,
                                shape = MaterialTheme.shapes.large,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.width(150.dp),
                                label = { Text(text = "Jumlah Order") }
                            )
                            //Jumlah Order End
                            Spacer(modifier = Modifier.width(20.dp))
                            //ConfirmButton
                            Button(
                                modifier = Modifier
                                    .height(55.dp)
                                    .weight(1f),
                                enabled = textJmlBeli.isNotEmpty(),
                                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95"))),
                                shape = MaterialTheme.shapes.large,
                                onClick = { onConfirmButtonClicked(textJmlBeli.toInt()) })
                            {
                                Text(stringResource(R.string.confirm), color = Color.White)
                            }
                            //ConfirmButtonEnd
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95"))),
                                shape = MaterialTheme.shapes.small,
                                onClick = {
                                    if (namaperson.isNotEmpty() && nohp.isNotEmpty() && emailperson.isNotEmpty() && identitas.isNotEmpty()) {
                                        onSubmitButtonClicked(
                                            namaperson,
                                            nohp,
                                            emailperson,
                                            identitas
                                        )
                                    }
                                }
                            ) {
                                Text(text = "Next")
                            }
                        }
                    }
                }
            }
        }


    }

}