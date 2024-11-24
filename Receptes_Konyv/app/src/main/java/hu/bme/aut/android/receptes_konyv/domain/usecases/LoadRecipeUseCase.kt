package hu.bme.aut.android.receptes_konyv.domain.usecases

import hu.bme.aut.android.receptes_konyv.data.datasource.RecipeRepository
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.model.asRecipe
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadRecipeUseCase (private val repository: RecipeRepository) {

    suspend operator fun invoke(id:Int): Result<Recipe> {

        return try {
            Result.success(repository.getRecipebyId(id).first().asRecipe())
        }
        catch (e:IOException){
            Result.failure(e)
        }
    }

}
