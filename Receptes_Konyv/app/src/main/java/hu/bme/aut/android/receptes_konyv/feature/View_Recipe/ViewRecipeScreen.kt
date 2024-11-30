package hu.bme.aut.android.receptes_konyv.feature.View_Recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.receptes_konyv.R
import hu.bme.aut.android.receptes_konyv.ui.common.RecipeEditor
import hu.bme.aut.android.receptes_konyv.ui.model.RecipeUI
import hu.bme.aut.android.receptes_konyv.ui.theme.DarkBlue
import hu.bme.aut.android.receptes_konyv.ui.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecipeScreen(
    onNavigationBack: ()->Unit,
    viewModel: ViewRecipeViewModel= hiltViewModel()

){
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context= LocalContext.current
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Success -> {
                    onNavigationBack()
                }

                is UiEvent.Failure -> {
                    scope.launch {
                        hostState.showSnackbar(uiEvent.message.asString(context))
                    }
                }
            }
        }
    }


    Scaffold(
        topBar = { TopAppBar( colors = TopAppBarColors(containerColor = Color(DarkBlue.value), actionIconContentColor = Color.White, navigationIconContentColor = Color.White, scrolledContainerColor = Color.White, titleContentColor = Color.White),
            title =
            {
                if(!state.editing)
                    Text(text = state.recipe?.title.toString())
                else
                    Text(text = stringResource(id = R.string.Szerkesztes))
            },
            actions = {
                if(!state.editing){
                    IconButton(onClick = {viewModel.onEvent(ViewRecipeEvent.editing)}) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                    }
                }

                IconButton(onClick = {viewModel.onEvent(ViewRecipeEvent.deleteRecipe)}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }

            },
            navigationIcon = {
                if(!state.editing){
                    IconButton(onClick = onNavigationBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
                else{
                    IconButton(onClick = { viewModel.onEvent(ViewRecipeEvent.stoppedEditing) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            }

        ) },
        floatingActionButton = {
            if(state.editing){
                LargeFloatingActionButton(onClick = {viewModel.onEvent(ViewRecipeEvent.updateRecipe)}) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "")
                }
            }
            else{
                LargeFloatingActionButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Checklist, contentDescription = "")
                }
            }
            
            
        }


    )
    { innerPadding->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()){
            val recipe= state.recipe?: RecipeUI()
            RecipeEditor(
                enabled = state.editing,
                titleValue = recipe.title,
                descriptionValue = recipe.description,
                ingredientsValue = recipe.ingredients,
                typeUI = recipe.type,
                onTitleChange = {viewModel.onEvent(ViewRecipeEvent.changeTitleEvent(it))},
                onDescriptionChange = {viewModel.onEvent(ViewRecipeEvent.changeDescriptionEvent(it))},
                onIngredientsChange ={viewModel.onEvent(ViewRecipeEvent.changeIngredientsEvent(it))},
                onTypeChange = {viewModel.onEvent(ViewRecipeEvent.changeTypeEvent(it))}
            )

        }

    }


}