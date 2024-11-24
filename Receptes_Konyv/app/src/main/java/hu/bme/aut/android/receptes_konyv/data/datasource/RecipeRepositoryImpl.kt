package hu.bme.aut.android.receptes_konyv.data.datasource

import hu.bme.aut.android.receptes_konyv.data.dao.RecipeDao
import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(val dao: RecipeDao):RecipeRepository {
    override suspend fun insertRecipe(recipe: RecipeEntity) {
        dao.insertRecipe(recipe)
    }

    override fun getAllRecipes(): Flow<List<RecipeEntity>> =dao.getAllRecipes()

    override fun getRecipebyId(id: Int): Flow<RecipeEntity> =dao.getRecipebyId(id)

    override suspend fun updateRecipe(recipe: RecipeEntity) {
        dao.updateRecipe(recipe)
    }

    override suspend fun deleteRecipe(id: Int) {
        dao.deleteRecipe(id)
    }

    override suspend fun deleteAllRecipes(){
        dao.deleteAllRecipes()
    }
}