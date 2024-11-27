package hu.bme.aut.android.receptes_konyv.feature.Create_Recipe

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.receptes_konyv.domain.usecases.RecipeUseCases
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(private val recipeUseCases: RecipeUseCases) :ViewModel(){
}