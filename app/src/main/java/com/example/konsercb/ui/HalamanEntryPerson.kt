package com.example.roomsiswa.ui

import DetailPerson
import EntryPersonViewModel
import UIStatePerson
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konsercb.R
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import com.example.konsercb.navigasi.EventTopAppBar
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

object DestinasiEntryPerson : DestinasiNavigasi {
    override val route = "entry_person"
    override val titleRes = R.string.entry_person
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPersonScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryPersonViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            EventTopAppBar(
                title = stringResource(DestinasiEntryPerson.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerpadding ->
        EntryPersonBody(
            uiStatePerson = viewModel.uiStatePerson,
            onPersonValueChange = viewModel::updateUiState,
            onDataSaveClick = {
                coroutineScope.launch {
                    with(MainScope()) {
                        viewModel.savePerson()
                        navigateBack()
                    }
                }
            },
            modifier = Modifier
                .padding(innerpadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryPersonBody(
    uiStatePerson: UIStatePerson,
    onPersonValueChange: (DetailPerson) -> Unit,
    onDataSaveClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        FormInputPerson(
            detailPerson = uiStatePerson.detailPerson,
            onValueChange = onPersonValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onDataSaveClick,
            enabled = uiStatePerson.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPerson(
    detailPerson: DetailPerson,
    modifier: Modifier = Modifier,
    onValueChange: (DetailPerson) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(value = detailPerson.namaperson,
            onValueChange = {onValueChange(detailPerson.copy(namaperson = it))},
            label = { Text(stringResource(id = R.string.namaperson))},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(value = detailPerson.emailperson,
            onValueChange = {onValueChange(detailPerson.copy(emailperson = it))},
            label = { Text(stringResource(id = R.string.emailperson))},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(value = detailPerson.identitas,
            onValueChange = {onValueChange(detailPerson.copy(identitas = it))},
            label = { Text(stringResource(id = R.string.identitas))},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(value = detailPerson.nohp,
            onValueChange = {onValueChange(detailPerson.copy(nohp = it))},
            label = { Text(stringResource(id = R.string.nohp))},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled){
            Text(stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}