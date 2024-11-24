package hu.bme.aut.android.receptes_konyv.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.receptes_konyv.feature.Recipe_List.RecipeListScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NavGraph( navController: NavHostController = rememberNavController(),){
    NavHost(navController = navController, startDestination = Screen.Recipes.route) {
        composable(Screen.Recipes.route){
            RecipeListScreen(ItemClicked = {navController.navigate(Screen.View.passId(it))}, CreateClicked = {navController.navigate(Screen.Create.route)})
        }
        composable(Screen.View.route){
            /*ToDo*/
        }
        composable(Screen.Create.route){
            /*ToDo*/
        }
    }
}