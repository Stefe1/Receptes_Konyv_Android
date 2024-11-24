package hu.bme.aut.android.receptes_konyv.data.datasource


import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun insertRecipe(recipe: RecipeEntity)

    fun getAllRecipes(): Flow<List<RecipeEntity>>

    fun getRecipebyId(id: Int): Flow<RecipeEntity>

    suspend fun updateRecipe(recipe: RecipeEntity)

    suspend fun deleteRecipe(id: Int)

    suspend fun deleteAllRecipes()
}