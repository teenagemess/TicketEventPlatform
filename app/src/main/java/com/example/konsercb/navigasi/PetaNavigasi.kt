package com.example.konsercb.navigasi


import DataPersonScreen
import HomeScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.konsercb.ui.DestinasiEntry
import com.example.konsercb.ui.DetailsDestination
import com.example.konsercb.ui.DetailsScreen
import com.example.konsercb.ui.EntryEventScreen
import com.example.konsercb.ui.ItemEditDestination
import com.example.konsercb.ui.ItemEditScreen
import com.example.konsercb.R
import com.example.roomsiswa.ui.DestinasiEntryPerson
import com.example.roomsiswa.ui.EntryPersonScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    val whitearrow = painterResource(R.drawable.white_arrow)
    CenterAlignedTopAppBar(title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(android.graphics.Color.parseColor("#1f1f95")),
            titleContentColor = androidx.compose.ui.graphics.Color.White
        ),
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Image(painter = whitearrow, contentDescription = "")
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    )
    {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navController = navController,  // Pass the NavController to HomeScreen
                onDetailClick = { eventId ->
                    navController.navigate("${DetailsDestination.route}/$eventId")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntryEventScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.eventIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(
                navigateBack = { navController.popBackStack() },
                navigateToPerson = { navController.navigate(DestinasiEntryPerson.route)},
                navController = navController
            )
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(DestinasiEntryPerson.route) {
            EntryPersonScreen(navigateBack = { navController.popBackStack() }, navController = navController)

        }
        composable(DestinasiDataPerson.route){
            DataPersonScreen(navigateToItemEntry = { /*TODO*/ })
        }
    }
}
