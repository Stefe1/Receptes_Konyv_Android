package hu.bme.aut.android.receptes_konyv.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.receptes_konyv.data.datasource.RecipeRepository
import hu.bme.aut.android.receptes_konyv.domain.usecases.DeleteAllRecipesUseCase
import hu.bme.aut.android.receptes_konyv.domain.usecases.DeleteRecipeUseCase
import hu.bme.aut.android.receptes_konyv.domain.usecases.LoadRecipeUseCase
import hu.bme.aut.android.receptes_konyv.domain.usecases.LoadRecipesUseCase
import hu.bme.aut.android.receptes_konyv.domain.usecases.RecipeUseCases
import hu.bme.aut.android.receptes_konyv.domain.usecases.SaveRecipeUseCase
import hu.bme.aut.android.receptes_konyv.domain.usecases.UpdateRecipeUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeUseCasesModule {

    @Provides
    @Singleton
    fun providesDeleteAllRecipesUseCase(repository: RecipeRepository): DeleteAllRecipesUseCase=DeleteAllRecipesUseCase(repository)

    @Provides
    @Singleton
    fun providesDeleteRecipeUseCase(repository: RecipeRepository): DeleteRecipeUseCase=DeleteRecipeUseCase(repository)

    @Provides
    @Singleton
    fun providesLoadRecipesUseCase(repository: RecipeRepository): LoadRecipesUseCase =LoadRecipesUseCase(repository)

    @Provides
    @Singleton
    fun providesLoadRecipeUseCase(repository: RecipeRepository):LoadRecipeUseCase= LoadRecipeUseCase(repository)

    @Provides
    @Singleton
    fun providesSaveRecipeUseCase(repository: RecipeRepository): SaveRecipeUseCase= SaveRecipeUseCase(repository)

    @Provides
    @Singleton
    fun providesUpdateRecipeUseCase(repository: RecipeRepository): UpdateRecipeUseCase=UpdateRecipeUseCase(repository)

    @Provides
    @Singleton
    fun providesRecipeUseCases(repository: RecipeRepository,
                               deleteRecipeUseCase: DeleteRecipeUseCase,
                               deleteAllRecipesUseCase: DeleteAllRecipesUseCase,
                               loadRecipeUseCase: LoadRecipeUseCase,
                               loadRecipesUseCase: LoadRecipesUseCase,
                               saveRecipeUseCase: SaveRecipeUseCase,
                               updateRecipeUseCase: UpdateRecipeUseCase): RecipeUseCases
            =RecipeUseCases(repository,
        deleteRecipeUseCase,
        deleteAllRecipesUseCase,
        loadRecipeUseCase,
        loadRecipesUseCase,
        saveRecipeUseCase,
        updateRecipeUseCase)

}