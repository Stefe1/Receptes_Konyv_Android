package hu.bme.aut.android.receptes_konyv.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.bme.aut.android.receptes_konyv.feature.Create_Recipe.CreateRecipeScreen
import hu.bme.aut.android.receptes_konyv.feature.Recipe_List.RecipeListScreen
import hu.bme.aut.android.receptes_konyv.feature.View_Recipe.ViewRecipeScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NavGraph( navController: NavHostController = rememberNavController(),){
    NavHost(navController = navController, startDestination = Screen.Recipes.route) {
        composable(Screen.Recipes.route){
            RecipeListScreen(ItemClicked = {navController.navigate(Screen.View.passId(it))}, CreateClicked = {navController.navigate(Screen.Create.route)})
        }
        composable(Screen.View.route, arguments = 
        listOf(
            navArgument("id"){
                type=NavType.IntType
            }
        
        )){
            ViewRecipeScreen(onNavigationBack = {
                navController.popBackStack(
                    route = Screen.Recipes.route,
                    inclusive = true

                )
                navController.navigate(Screen.Recipes.route)

            })
        }
        composable(Screen.Create.route){
            CreateRecipeScreen(onNavigationBack = {

                navController.popBackStack(
                    route = Screen.Recipes.route,
                    inclusive = true

                )
                navController.navigate(Screen.Recipes.route)
            })
        }
    }
}