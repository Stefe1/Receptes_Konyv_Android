package hu.bme.aut.android.receptes_konyv.feature.Recipe_List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.usecases.RecipeUseCases
import hu.bme.aut.android.receptes_konyv.ui.model.RecipeUI
import hu.bme.aut.android.receptes_konyv.ui.model.asRecipeUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecipeListState(
    val isLoading:Boolean =false,
    val error: Throwable?=null,
    val isError: Boolean= error!=null,
    val recipes: List<RecipeUI> = emptyList()
)



@HiltViewModel
class RecipeListViewModel @Inject constructor(private val useCases: RecipeUseCases) :ViewModel(){
    private val _state= MutableStateFlow(RecipeListState())
    val state=_state.asStateFlow()
    init {
        loadRecipes()
    }

    fun loadRecipes(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                CoroutineScope(coroutineContext).launch(Dispatchers.IO){
                    val recipes=useCases.loadRecipesUseCase().getOrThrow().map { it.asRecipeUI() }
                    _state.update { it.copy(isLoading = false, recipes = recipes) }
                }
            }
            catch (e:Exception){
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }

}