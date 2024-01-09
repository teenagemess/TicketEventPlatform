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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.konsercb.R
import com.example.konsercb.database.Person
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konsercb.model.HomeViewModel
import com.example.konsercb.navigasi.EventTopAppBar

object DestinasiDataPerson : DestinasiNavigasi {
    override val route = "data"
    override val titleRes = R.string.titleapp
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPersonScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (Int) -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            EventTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.entry_person)
                )
            }
        }
    ) { innerpadding ->
        val uiStatePerson by viewModel.personUiState.collectAsState()
        BodyDataPerson(
            itemPerson = uiStatePerson.listPerson,
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            onPersonClick = onDetailClick
        )
    }
}

@Composable
fun BodyDataPerson(
    itemPerson: List<Person>,
    modifier: Modifier = Modifier,
    onPersonClick: (Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemPerson.isEmpty()) {
            Text(
                text = stringResource(id = R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListDataPerson(
                itemPerson = itemPerson,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onPersonClick(it.id) }
            )
        }
    }
}

@Composable
fun ListDataPerson(
    itemPerson: List<Person>,
    modifier: Modifier = Modifier,
    onItemClick: (Person) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = itemPerson, key = { it.id }) { person ->
            DataPerson(
                person = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataPerson(
    person: Person,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = person.namaperson,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                Text(
                    text = person.emailperson,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = person.identitas,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = person.nohp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
