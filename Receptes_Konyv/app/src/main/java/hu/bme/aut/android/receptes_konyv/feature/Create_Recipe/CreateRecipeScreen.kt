package hu.bme.aut.android.receptes_konyv.feature.Create_Recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.receptes_konyv.R
import hu.bme.aut.android.receptes_konyv.ui.common.RecipeEditor
import hu.bme.aut.android.receptes_konyv.ui.model.TypeUI
import hu.bme.aut.android.receptes_konyv.ui.model.UiText
import hu.bme.aut.android.receptes_konyv.ui.theme.DarkBlue
import hu.bme.aut.android.receptes_konyv.ui.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreen(onNavigationBack: ()->Unit,viewModel: CreateRecipeViewModel= hiltViewModel() ){
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context=LocalContext.current
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
        floatingActionButton = {
            LargeFloatingActionButton(onClick = { viewModel.onEvent(CreateRecipeEvent.saveRecipe) }, containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary) {
                Icon(Icons.Default.Save,"")

            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(containerColor = MaterialTheme.colorScheme.primary, actionIconContentColor = MaterialTheme.colorScheme.onPrimary, navigationIconContentColor = MaterialTheme.colorScheme.onPrimary, scrolledContainerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.onPrimary),
                title = { Text(text = stringResource(R.string.Top_App_Bar_Cim)) },
                navigationIcon = { IconButton(onClick = onNavigationBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                } }
            )
        }
    )
    { innerPadding->
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround){
            RecipeEditor(
                titleValue = state.recipe.title,
                descriptionValue = state.recipe.description,
                ingredientsValue = state.recipe.ingredients,
                typeUI = state.recipe.type,
                onTitleChange = {viewModel.onEvent(CreateRecipeEvent.changeTitleEvent(it))},
                onDescriptionChange = {viewModel.onEvent(CreateRecipeEvent.changeDescriptionEvent(it))},
                onIngredientsChange = {viewModel.onEvent(CreateRecipeEvent.changeIngredientsEvent(it))},
                onTypeChange = {viewModel.onEvent(CreateRecipeEvent.changeTypeEvent(it))}
            )
        }
    }
}