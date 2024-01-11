package com.example.konsercb.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.konsercb.R
import com.example.konsercb.model.DetailEventViewModel
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.konsercb.database.Event
import com.example.konsercb.model.ItemDetailsUiState
import com.example.konsercb.model.toEvent
import com.example.konsercb.navigasi.EventTopAppBar
import kotlinx.coroutines.launch

object DetailsDestinationAdmin : DestinasiNavigasi {
    override val route = "item_detailsAdmin"
    override val titleRes = R.string.detail_event
    const val eventIdArg = "itemId"
    val routeWithArgs = "$route/{$eventIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenAdmin(
    navigateBack: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DetailEventViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Column() {
        Scaffold(
            topBar = {
                EventTopAppBar(
                    title = stringResource(id = DetailsDestination.titleRes),
                    canNavigateBack = true,
                    navigateUp = navigateBack
                )
            },
            modifier = modifier
        ) { innerPadding ->
            ItemDetailsBodyAdmin(
                itemDetailsUiState = uiState.value,
                onDelete = {
                    coroutineScope.launch {
                        viewModel.deleteItem()
                        navigateBack
                    }
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                navController = navController,
            )
        }

    }
}

@Composable
private fun ItemDetailsBodyAdmin(
    itemDetailsUiState: ItemDetailsUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable {
            mutableStateOf(false)
        }
        TicketBannerAdmin(
            event = itemDetailsUiState.detailEvent.toEvent(),
            modifier = Modifier
        )
        ItemDetailsAdmin(
            event = itemDetailsUiState.detailEvent.toEvent(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(id = R.string.delete),
                color = Color(android.graphics.Color.parseColor("#1f1f95"))
            )
        }
        Button(
            onClick = {
                // Navigate to the Edit screen when the button is clicked
                navController.navigate("${ItemEditDestination.route}/${itemDetailsUiState.detailEvent.id}")
            },
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95"))),
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Event",)
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun TicketBannerAdmin(
    event: Event,
    modifier: Modifier
) {
    val ticketImage = painterResource(R.drawable.gambartiket)
    val instagram = painterResource(R.drawable.instagram)
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),


        ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = ticketImage,
                    contentDescription = "Gambar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(
                    modifier = Modifier
                        .width(87.dp)
                        .height(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(android.graphics.Color.parseColor("#efecf2"))),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Musik",
                        color = Color(android.graphics.Color.parseColor("#1f1f95")),
                        fontWeight = FontWeight.Bold,

                        )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = instagram, contentDescription = "")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Instagram")
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = event.namaevent,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = "Rp. 50.000",
                color = Color(android.graphics.Color.parseColor("#f45f09")),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}

@Composable
fun ItemDetailsAdmin(
    event: Event, modifier: Modifier = Modifier
) {
    val ticketImage = painterResource(R.drawable.gambartiketbulat)
    val date = painterResource(R.drawable.date)
    val address = painterResource(R.drawable.address)
    val clock = painterResource(R.drawable.clock)


    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {

            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = ticketImage,
                    contentDescription = "tiket",
                    modifier = Modifier
                        .width(65.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Column {
                    Text(
                        text = "Penyelenggara",
                        color = Color.Gray
                    )
                    Text(
                        text = event.penyelenggaraevent,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            Row {
                Image(painter = date, contentDescription = "")
                Spacer(modifier = Modifier.width(17.dp))
                Column {
                    Text(
                        text = "Tanggal",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = event.tanggalevent,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(1.dp))
            Row {
                Image(painter = clock, contentDescription = "")
                Spacer(modifier = Modifier.width(17.dp))
                Column {
                    Text(
                        text = "Waktu",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = event.waktuevent,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(1.dp))
            Row {
                Image(painter = address, contentDescription = "")
                Spacer(modifier = Modifier.width(17.dp))
                Column {
                    Text(
                        text = "Lokasi",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = event.alamatevent,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    Column(
        modifier = Modifier
            .width(115.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(android.graphics.Color.parseColor("#1f1f95"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Deskripsi",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Deskripsi Event",
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    )


    Text(
        text = event.deskripsievent,
        fontSize = 16.sp
    )

    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Syarat dan Ketentuan",
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    )
    Text(
        text = "Syarat dan Ketentuan Event :",
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        fontSize = 17.sp
    )

    Text(
        text = "- Dilarang masuk bagi penderita penyakit Jantung, Epilepsi, Asma, dan Penyakit Lainnya yang membahayakan diri sendiri dan orang lain\n\n" +
                "- Dilarang masuk bagi Balita, Ibu Hamil, dan Manula,\n\n" +
                "- Dilarang membawa Senjata tajam, barang terlarang, dan benda berbahaya lainnya,\n\n" +
                "- Demi keamanan dan kenyamanan bersama, pengunjung wajib memenuhi peraturan dan tata tertib yang berlaku.\n\n",
        fontSize = 16.sp
    )

    Text(
        text = "Syarat dan Ketentuan Event :",
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        fontSize = 17.sp
    )

    Text(
        text = "- Tiket yang sudah dibeli tidak dapat ditukar kembali,\n\n" +
                "- Pastikan tiket yang di beli sesuai dengan Hari dan Jam yang diinginkan,\n\n" +
                "-  Apabila Ticket di redeem diluar jam yang sudah dipesan, crew dari wahana berhak untuk mengarahkan pembeli menyesuaikan dengan kondisi saat itu (akan diutamakan yang memesan ticket pada jam yang sesuai terlebih dahulu)\n",
        fontSize = 16.sp
    )
}


@Composable
private fun ItemDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*Do nothing*/ },
        title = { Text(stringResource(id = R.string.attention)) },
        text = { Text(stringResource(id = R.string.delete)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        }
    )
}