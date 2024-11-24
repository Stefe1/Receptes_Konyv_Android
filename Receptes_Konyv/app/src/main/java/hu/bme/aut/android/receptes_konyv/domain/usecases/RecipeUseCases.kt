package hu.bme.aut.android.receptes_konyv.domain.usecases

import hu.bme.aut.android.receptes_konyv.data.datasource.RecipeRepository

class RecipeUseCases (val repository: RecipeRepository,
    val deleteRecipeUseCase: DeleteRecipeUseCase,
    val deleteAllRecipesUseCase: DeleteAllRecipesUseCase,
    val loadRecipeUseCase: LoadRecipeUseCase,
    val loadRecipesUseCase: LoadRecipesUseCase,
    val saveRecipeUseCase: SaveRecipeUseCase,
    val updateRecipeUseCase: UpdateRecipeUseCase
)