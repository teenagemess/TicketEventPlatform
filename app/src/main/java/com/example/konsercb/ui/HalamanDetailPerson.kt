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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.konsercb.R
import com.example.konsercb.database.Person
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konsercb.database.Event
import com.example.konsercb.database.OrderUIState
import com.example.konsercb.model.HomeViewModel
import com.example.konsercb.model.ItemDetailsUiState
import com.example.konsercb.model.toEvent
import com.example.konsercb.navigasi.EventTopAppBar

object DestinasiDataPerson : DestinasiNavigasi {
    override val route = "data"
    override val titleRes = R.string.titleapp
}

@Composable
fun HalamanDua(
    orderUIState: OrderUIState,
    modifier: Modifier = Modifier,
){
    val items = listOf(
        Pair("Nama Pelanggan", orderUIState.namaperson),
        Pair("Nomor Telepon", orderUIState.nohp),
        Pair("Email", orderUIState.emailperson),
        Pair("No KTP", orderUIState.identitas),
        Pair("Jumlah", orderUIState.jumlah.toString()),
        Pair("Rasa", orderUIState.rasa),
        Pair("Harga", orderUIState.harga)
    )

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ){
            items.forEach { item ->
                Column {
                    Text(item.first.uppercase())
                    Text(text = item.second, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            FormatLabelHarga(
                subtotal = orderUIState.harga,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
        }
    }
}

@Composable
fun FormatLabelHarga(subtotal: String, modifier: Modifier = Modifier){
    Text(
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}