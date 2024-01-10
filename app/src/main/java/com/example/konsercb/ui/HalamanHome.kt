import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.konsercb.R
import com.example.konsercb.database.Event
import com.example.konsercb.model.HomeViewModel
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konsercb.navigasi.EventTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonColors
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.konsercb.ui.DetailsDestination


object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.titleapp
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (Int) -> Unit = {},
    navController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val logo = painterResource(R.drawable.logo)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(android.graphics.Color.parseColor("#1f1f95")),
                    titleContentColor = Color.White,
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
                            Image(painter = logo, contentDescription = "", modifier = Modifier
                                .width(30.dp)
                                .height(24.dp))
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                "Empatix",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )

                        }

                        OutlinedButton(
                            shape = MaterialTheme.shapes.medium,
                            onClick = navigateToItemEntry,
                            border = BorderStroke(1.dp, Color.White),
                            modifier = Modifier
                                .width(138.dp)
                                .height(45.dp)
                        ) {
                            Text(text = "Tambah Event", color = Color.White)
                        }
                    }

                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerpadding ->
        val uiStateEvent by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemEvent = uiStateEvent.listEvent,
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            navController = navController,
        )
    }
}
@Composable
fun BodyHome(
    itemEvent: List<Event>,
    modifier: Modifier = Modifier,
    navController: NavHostController,

    ) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        item {
            ImageCarousel(
                images = listOf(
                    R.drawable.image1,
                    R.drawable.image2,
                    R.drawable.image3,
                    R.drawable.image4,
                    R.drawable.image5,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = "Rekomendasi Event",
                fontSize = 25.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp, bottom = 22.dp)
            )
        }

        if (itemEvent.isEmpty()) {
            item {
                Text(
                    text = stringResource(id = R.string.deskripsi_no_item),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        } else {
            items(itemEvent) { event ->
                DataEvent(
                    navController = navController,
                    event = event,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(vertical = 15.dp, horizontal = 12.dp)
                )
            }
        }
    }
}


@Composable
fun DataEvent(
    event: Event,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val ticketImage = painterResource(R.drawable.gambartiket)
    val address = painterResource(R.drawable.address)
    val clock = painterResource(R.drawable.clock)
    val date = painterResource(R.drawable.date)



    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ){
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = ticketImage,
                    contentDescription = "Gambar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                    )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = event.namaevent,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black

                )
            }

            Spacer(modifier = Modifier.height(3.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(painter = address, contentDescription = "alamat")
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = event.alamatevent,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 19.sp,
                )
            }
            Spacer(modifier = Modifier.height(0.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(painter = date, contentDescription = "kalender")
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = event.tanggalevent,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 19.sp
                )
            }

            Spacer(modifier = Modifier.height(0.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(painter = clock, contentDescription = "waktu")
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = event.waktuevent,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 19.sp
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Harga Mulai",
                        color = Color.Gray
                    )
                    Text(
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#f45f09")),
                        text = "Rp 50.000",
                        fontSize = 19.sp
                    )
                }

                Button(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(50.dp)
                        .width(100.dp),
                    onClick = {
                        navController.navigate("${DetailsDestination.route}/${event.id}")
                    },
                    colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95")))
                ) {
                    Text(text = "Beli")
                }
            }
        }
    }
}

@Composable
fun ImageCarousel(images: List<Int>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .background(Color.White)
            .padding(0.dp)// Customize the background color if needed
    ) {
        itemsIndexed(images) { index, imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .padding(horizontal = 6.dp)
                    .clip(RoundedCornerShape(0.dp))
            )
        }
    }
}


