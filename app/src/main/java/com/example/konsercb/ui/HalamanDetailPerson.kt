import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.konsercb.R
import com.example.konsercb.navigasi.DestinasiNavigasi
import com.example.konsercb.database.OrderUIState


object DestinasiDataPerson : DestinasiNavigasi {
    override val route = "data"
    override val titleRes = R.string.titleapp
}

@Composable
fun HalamanDua(
    orderUIState: OrderUIState,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {
    val items = listOf(
        Pair("Nama: ", orderUIState.namaperson),
        Pair("Nomor Telepon: ", orderUIState.nohp),
        Pair("Email: ", orderUIState.emailperson),
        Pair("No. KTP: ", orderUIState.identitas),
        Pair("Jumlah: ", orderUIState.jumlah.toString()),
        Pair("Ticket: ", orderUIState.rasa),
        Pair("Harga: ", orderUIState.harga)
    )

    val gambar = painterResource(id = R.drawable.gambartiket)
    val qr = painterResource(id = R.drawable.qrcode)

    Scaffold {innerPadding ->
        Column(modifier.padding(innerPadding)) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = gambar,
                            contentDescription = "",
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        items.forEach { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(item.first, fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 17.sp)
                                Text(text = item.second, color = Color.Black, fontSize = 18.sp)
                            }
                        }
                    }
                }
                Column(
                    modifier
                        .padding(end = 30.dp)
                        .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End) {
                    Image(painter = qr, contentDescription = "")
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    FormatLabelHarga(
                        subtotal = orderUIState.harga,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(8.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(bottom = 0.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .height(80.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = navigateToHome,
                            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95"))),
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier
                                .height(48.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Text("Send E-Ticket To Email")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun FormatLabelHarga(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        color = Color.White
    )
}