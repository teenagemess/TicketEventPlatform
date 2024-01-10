package com.example.konsercb.ui

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.konsercb.R
import com.example.konsercb.model.EntryEventViewModel
import com.example.konsercb.model.PenyediaViewModel
import com.example.konsercb.navigasi.DestinasiNavigasi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konsercb.model.DetailEvent
import com.example.konsercb.model.UIStateEvent
import com.example.konsercb.navigasi.EventTopAppBar
import kotlinx.coroutines.launch
import java.util.Calendar


object DestinasiEntry : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = R.string.buatevent
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryEventScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryEventViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            EventTopAppBar(
                title = stringResource(DestinasiEntry.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerpadding ->
        EntryEventBody(
            uiStateEvent = viewModel.uiStateEvent,
            onEventValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveEvent()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerpadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntryEventBody(
    uiStateEvent: UIStateEvent,
    onEventValueChange: (DetailEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        FormInputEvent(
            detailEvent = uiStateEvent.detailEvent,
            onValueChange = onEventValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#1f1f95"))),
            onClick = onSaveClick,
            enabled = uiStateEvent.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth().height(45.dp)
        ) {
            Text(stringResource(id = R.string.btn_submit), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormInputEvent(
    detailEvent: DetailEvent,
    onValueChange: (DetailEvent) -> Unit = {},
    enabled: Boolean = true,
    modifier: Modifier
){

    val event = painterResource(R.drawable.ic_event)


    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Image(painter = event, contentDescription = "")
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Data Event", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
            Spacer(modifier = Modifier.height(0.dp))
        Divider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(5.dp))
        Text(
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Tanggal Event")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append(" *")
                }
            }
        )
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = detailEvent.tanggalevent,
                onValueChange = { onValueChange(detailEvent.copy(tanggalevent = it)) },
                modifier = Modifier
                    .height(50.dp)
                    .width(265.dp),
                enabled = false,
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
            )
            Spacer(modifier = Modifier.width(10.dp))
            DatePicker { selectedDate ->
                onValueChange(detailEvent.copy(tanggalevent = selectedDate))
            }
        }
        Text(
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Nama Event")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append(" *")
                }
            }
        )
        OutlinedTextField(value = detailEvent.namaevent,
            onValueChange = {onValueChange(detailEvent.copy(namaevent = it))},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Text(
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Penyelenggara Event")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append(" *")
                }
            }
        )
        OutlinedTextField(value = detailEvent.penyelenggaraevent,
            onValueChange = {onValueChange(detailEvent.copy(penyelenggaraevent = it))},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Text(
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Alamat Event")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append(" *")
                }
            }
        )
        OutlinedTextField(value = detailEvent.alamatevent,
            onValueChange = {onValueChange(detailEvent.copy(alamatevent = it))},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Text(
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Waktu Event")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append(" *")
                }
            }
        )
        OutlinedTextField(value = detailEvent.waktuevent,
            onValueChange = {onValueChange(detailEvent.copy(waktuevent = it))},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Text(
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Deskripsi Event")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append(" *")
                }
            }
        )
        OutlinedTextField(value = detailEvent.deskripsievent,
            onValueChange = {onValueChange(detailEvent.copy(deskripsievent = it))},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )

        // Custom Date Picker Button

        if (enabled){
            Text(stringResource(id = R.string.required_field),
                color = Color.Red,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateButton(onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = { showDialog = true },
        modifier = Modifier.size(8.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_date),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }

    LaunchedEffect(showDialog) {
        if (showDialog) {
            val datePickerDialog = DatePickerDialog(
                context,
                { _, year, month, day ->
                    val selectedDate = "$day/$month/$year"
                    onDateSelected(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(onDateSelected: (String) -> Unit) {
    val context = LocalContext.current

    val dategray = painterResource(R.drawable.date_gray)

    val calendar = remember { Calendar.getInstance() }
    val date = remember { mutableStateOf("") }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                date.value = "$day/$month/$year"
                onDateSelected(date.value)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    Button(
        onClick = { datePickerDialog.show() },
        modifier = Modifier.height(50.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#e9ecef")))

    ) {
       Image(painter = dategray, contentDescription = "", modifier = Modifier.fillMaxSize())
    }
}