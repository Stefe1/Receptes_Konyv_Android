package hu.bme.aut.android.receptes_konyv.domain.usecases

import hu.bme.aut.android.receptes_konyv.data.datasource.RecipeRepository

class DeleteRecipeUseCase (private val repository: RecipeRepository) {
    suspend operator fun invoke(id: Int){
        repository.deleteRecipe(id)
    }
}