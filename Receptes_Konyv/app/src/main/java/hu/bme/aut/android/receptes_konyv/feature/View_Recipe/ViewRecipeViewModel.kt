package hu.bme.aut.android.receptes_konyv.feature.View_Recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.usecases.RecipeUseCases
import hu.bme.aut.android.receptes_konyv.feature.Create_Recipe.CreateRecipeEvent
import hu.bme.aut.android.receptes_konyv.ui.model.RecipeUI
import hu.bme.aut.android.receptes_konyv.ui.model.TypeUI
import hu.bme.aut.android.receptes_konyv.ui.model.asRecipe
import hu.bme.aut.android.receptes_konyv.ui.model.asRecipeUI
import hu.bme.aut.android.receptes_konyv.ui.model.toUiText
import hu.bme.aut.android.receptes_konyv.ui.util.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



data class ViewRecipeState(
    val recipe: RecipeUI?=null,
    val loading:Boolean=false,
    val editing:Boolean=false,
    val error: Throwable?=null
)
sealed class ViewRecipeEvent{
    object editing:ViewRecipeEvent()
    object stoppedEditing:ViewRecipeEvent()
    data class changeTitleEvent(val newTitle:String ): ViewRecipeEvent()
    data class changeDescriptionEvent(val newDescription:String ): ViewRecipeEvent()
    data class changeIngredientsEvent(val newIngredients:String ): ViewRecipeEvent()
    data class changeTypeEvent(val newType: TypeUI): ViewRecipeEvent()
    object updateRecipe: ViewRecipeEvent()
    object deleteRecipe:ViewRecipeEvent()
}

@HiltViewModel
class ViewRecipeViewModel @Inject constructor(private val savedState: SavedStateHandle, private val useCases: RecipeUseCases):ViewModel(){

    private val _state= MutableStateFlow(ViewRecipeState())
    val state: StateFlow<ViewRecipeState> = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        LoadRecipe()
    }


    fun onEvent(event:ViewRecipeEvent){
        when (event){
            ViewRecipeEvent.editing->{
                _state.update { it.copy(editing = true) }
            }
            ViewRecipeEvent.stoppedEditing->{
                _state.update { it.copy(editing = false) }
            }
            is ViewRecipeEvent.changeTitleEvent->{
                val newTitle=event.newTitle
                _state.update { it.copy(recipe = it.recipe?.copy(title = newTitle)) }
            }
            is ViewRecipeEvent.changeTypeEvent->{
                val newType=event.newType
                _state.update { it.copy(recipe = it.recipe?.copy(type = newType)) }
            }
            is ViewRecipeEvent.changeDescriptionEvent->{
                val newDescription=event.newDescription
                _state.update { it.copy(recipe = it.recipe?.copy(description = newDescription)) }
            }
            is ViewRecipeEvent.changeIngredientsEvent->{
                val newIngredients=event.newIngredients
                _state.update { it.copy(recipe = it.recipe?.copy(ingredients = newIngredients)) }
            }
            ViewRecipeEvent.updateRecipe->{
                UpdateRecipe()
            }
            ViewRecipeEvent.deleteRecipe->{
                DeleteRecipe()
            }
        }
    }

    private fun LoadRecipe(){
        val recipeId= checkNotNull<Int>(savedState["id"])
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            try {
                val recipe= useCases.loadRecipeUseCase(recipeId)
                CoroutineScope(coroutineContext).launch(Dispatchers.IO){

                    _state.update { it.copy(loading=false,recipe=recipe.getOrThrow().asRecipeUI()) }
                }
            }
            catch (e: Error){
                _uiEvent.send(UiEvent.Failure(e.toUiText()))
            }
        }
    }

    private fun UpdateRecipe(){
        viewModelScope.launch (Dispatchers.IO){
            _state.update { it.copy(recipe = it.recipe?.copy(edited = true)) }
            try {
                useCases.updateRecipeUseCase(
                    _state.value.recipe?.asRecipe()!!
                )
                _uiEvent.send(UiEvent.Success)
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.Failure(e.toUiText()))
            }
        }
    }

    private fun DeleteRecipe(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                useCases.deleteRecipeUseCase(state.value.recipe!!.id)
                _uiEvent.send(UiEvent.Success)
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.Failure(e.toUiText()))
            }
        }
    }

}