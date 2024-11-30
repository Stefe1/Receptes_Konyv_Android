package hu.bme.aut.android.receptes_konyv.feature.View_Recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.receptes_konyv.R
import hu.bme.aut.android.receptes_konyv.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecipeScreen(
    onNavigationBack: ()->Unit,
    viewModel: ViewRecipeViewModel= hiltViewModel()

){
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar( colors = TopAppBarColors(containerColor = Color(DarkBlue.value), actionIconContentColor = Color.White, navigationIconContentColor = Color.White, scrolledContainerColor = Color.White, titleContentColor = Color.White),
            title =
            {
                if(state.editing)
                    Text(text = state.recipe?.title.toString())
                else
                    Text(text = stringResource(id = R.string.Szerkesztes))
            },
            actions = {
                IconButton(onClick = {viewModel.onEvent(ViewRecipeEvent.editing)}) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                }

                IconButton(onClick = {viewModel.onEvent(ViewRecipeEvent.deleteRecipe)}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }

            }

        ) }


    ) { innerPadding->
        Box(modifier = Modifier.padding(innerPadding))

    }


}