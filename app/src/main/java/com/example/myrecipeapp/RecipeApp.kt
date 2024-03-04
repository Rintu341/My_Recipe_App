package com.example.myrecipeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeApp(navController : NavHostController,items:List<Screen>) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Scaffold(
        /*
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.ArrowBack, contentDescription = null) },
//                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
         */
        topBar = { 
           // Add logic of Top Bar
                TopAppBar(
                    title = { Text(stringResource(id = R.string.Recipe)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            if (navController.currentDestination?.route != Screen.RecipeScreen.route) {
                                navController.navigateUp()
                            }
                        }){
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
//                    modifier = Modifier.background(Color.Blue)
                )
        }
    ) {innerPadding ->
        NavHost(navController = navController, startDestination = Screen.RecipeScreen.route,
            Modifier.padding(innerPadding)) {
            composable(route = Screen.RecipeScreen.route) {
                RecipeScreen(viewState = viewState, navigateToDetailsScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "Cat",
                        it
                    )// store current backStack entry into saveStateHandle to key value pair
                    navController.navigate(Screen.DetailsScreen.route)
                })
            }
            composable(route = Screen.DetailsScreen.route) {
                val category =
                    navController.previousBackStackEntry?.savedStateHandle?. // retrieve category from saveStateHandle
                    get<Category>("Cat") ?: Category("", "", "", "") //get category by key "Cat"

                DetailsScreen(category = category)
            }
        }
    }
}

