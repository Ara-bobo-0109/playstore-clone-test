package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.feature.addapplication.AddApplicationScreen
import com.example.feature.appcatalog.AppCatalogScreen
import com.example.feature.applicationdetail.ApplicationDetailScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val graph = AppGraph(applicationContext)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = NavRoutes.Catalog
            ) {
                composable(NavRoutes.Catalog) {
                    val vm = viewModel<com.example.feature.appcatalog.AppCatalogViewModel>(
                        factory = AppCatalogVmFactory(graph)
                    )
                    val state by vm.uiState.collectAsState()

                    AppCatalogScreen(
                        state = state,
                        onQueryChange = vm::onQueryChange,
                        onOpenApp = { id -> navController.navigate(NavRoutes.detail(id)) },
                        onAddApp = { navController.navigate(NavRoutes.Add) }
                    )
                }

                composable(NavRoutes.Add) {
                    val vm = viewModel<com.example.feature.addapplication.AddApplicationViewModel>(
                        factory = AddApplicationVmFactory(graph)
                    )
                    val state by vm.state.collectAsState()

                    AddApplicationScreen(
                        state = state,
                        onName = vm::onName,
                        onPackage = vm::onPackage,
                        onDeveloper = vm::onDeveloper,
                        onDescription = vm::onDescription,
                        onSave = vm::save,
                        onDone = { newId ->
                            navController.popBackStack()
                            navController.navigate(NavRoutes.detail(newId))
                        },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(
                    route = NavRoutes.DetailPattern,
                    arguments = listOf(navArgument("appId") { type = NavType.LongType })
                ) { entry ->
                    val appId = entry.arguments?.getLong("appId") ?: 0L
                    val vm = viewModel<com.example.feature.applicationdetail.ApplicationDetailViewModel>(
                        factory = ApplicationDetailVmFactory(graph, appId)
                    )
                    val state by vm.state.collectAsState()

                    ApplicationDetailScreen(
                        state = state,
                        onToggleInstall = vm::toggleInstall,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
