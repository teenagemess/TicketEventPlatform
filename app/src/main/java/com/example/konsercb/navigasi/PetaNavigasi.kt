package com.example.konsercb.navigasi


import DataPersonScreen
import DestinasiDataPerson
import HomeScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
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
                onDetailClick = {
                    navController.navigate("${DetailsDestination.route}/$it")
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
                navigateToEditItem = {
                    navController.navigate("${ItemEditDestination.route}/$it")
                },
                navigateBack = { navController.popBackStack() },
                navigateToPerson = { navController.navigate(DestinasiEntryPerson.route) },
                navigateToDataPerson = { navController.navigate(DestinasiDataPerson.route) }
            )
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg){
                type = NavType.IntType
            })
        ){
            ItemEditScreen(navigateBack = { navController.popBackStack() }, onNavigateUp = { navController.navigateUp() })
        }
        composable(DestinasiEntryPerson.route) {
            EntryPersonScreen(navigateBack = { navController.popBackStack() })

        }
        composable(DestinasiDataPerson.route){
            DataPersonScreen(navigateToItemEntry = { /*TODO*/ })
        }
    }
}
