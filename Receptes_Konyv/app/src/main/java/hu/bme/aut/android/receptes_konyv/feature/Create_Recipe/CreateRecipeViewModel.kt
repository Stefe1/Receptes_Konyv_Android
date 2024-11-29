package hu.bme.aut.android.receptes_konyv.feature.Create_Recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.usecases.RecipeUseCases
import hu.bme.aut.android.receptes_konyv.ui.model.RecipeUI
import hu.bme.aut.android.receptes_konyv.ui.model.TypeUI
import hu.bme.aut.android.receptes_konyv.ui.model.asRecipe
import hu.bme.aut.android.receptes_konyv.ui.model.toUiText
import hu.bme.aut.android.receptes_konyv.ui.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CreateRecipeEvent{
    data class changeTitleEvent(val newTitle:String ):CreateRecipeEvent()
    data class changeDescriptionEvent(val newDescription:String ):CreateRecipeEvent()
    data class changeIngredientsEvent(val newIngredients:String ):CreateRecipeEvent()
    data class changeTypeEvent(val newType:TypeUI):CreateRecipeEvent()
    object saveRecipe: CreateRecipeEvent()
}

data class CreateRecipeState(
    val recipe:RecipeUI=RecipeUI()
)


@HiltViewModel
class CreateRecipeViewModel @Inject constructor(private val recipeUseCases: RecipeUseCases) :ViewModel(){

    private val _state= MutableStateFlow(CreateRecipeState())

    val state=_state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event:CreateRecipeEvent){
        when(event){
            is CreateRecipeEvent.changeTitleEvent->{
                _state.update { it.copy(recipe = it.recipe.copy(title = event.newTitle)) }
            }
            is CreateRecipeEvent.changeDescriptionEvent->{
                _state.update { it.copy(recipe = it.recipe.copy(description = event.newDescription)) }
            }
            is CreateRecipeEvent.changeIngredientsEvent->{
                _state.update { it.copy(recipe = it.recipe.copy(ingredients = event.newIngredients)) }
            }
            is CreateRecipeEvent.changeTypeEvent->{
                _state.update { it.copy(recipe = it.recipe.copy(type = event.newType)) }
            }
            is CreateRecipeEvent.saveRecipe->{
                Save()
            }
        }
    }
    fun Save(){
        viewModelScope.launch {
            try {
                recipeUseCases.saveRecipeUseCase(state.value.recipe.asRecipe())
                _uiEvent.send(UiEvent.Success)
            }
            catch (e: Error){
                _uiEvent.send(UiEvent.Failure(e.toUiText()))
            }
        }
    }

}