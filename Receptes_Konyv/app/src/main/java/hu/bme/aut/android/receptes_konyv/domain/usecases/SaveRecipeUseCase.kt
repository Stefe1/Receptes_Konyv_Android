package hu.bme.aut.android.receptes_konyv.domain.usecases

import hu.bme.aut.android.receptes_konyv.data.datasource.RecipeRepository
import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.model.asRecipeEntity

class SaveRecipeUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe){
        repository.insertRecipe(recipe.asRecipeEntity())
    }
}