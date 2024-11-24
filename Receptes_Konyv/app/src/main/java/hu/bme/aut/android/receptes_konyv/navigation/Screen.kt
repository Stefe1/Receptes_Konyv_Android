package hu.bme.aut.android.receptes_konyv.navigation

sealed class Screen (val route: String) {
    object Recipes: Screen("recipes")
    object  Create:Screen("create")
    object View:Screen("view/{id}"){
        fun passId(id: Int)="view/$id"
    }
}