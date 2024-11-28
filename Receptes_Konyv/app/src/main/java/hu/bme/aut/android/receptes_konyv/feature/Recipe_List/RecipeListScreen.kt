package hu.bme.aut.android.receptes_konyv.feature.Recipe_List

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.receptes_konyv.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen (ItemClicked:(Int)->Unit,CreateClicked:()->Unit,viewModel: RecipeListViewModel= hiltViewModel()){
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    Scaffold (floatingActionButton = { FloatingActionButton(onClick =  CreateClicked) {
        Icon(Icons.Default.Add, contentDescription = "")
    }},
        topBar = { TopAppBar( title = { Text(text = "ReceptesKönyv") }) }
        ) { innerPadding->
        if(state.recipes.isEmpty()){
            EmptyList(modifier = Modifier.padding(innerPadding))
        }
    }
}
@Composable
fun EmptyList(modifier: Modifier){
    Column (modifier = modifier
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Icon(imageVector = Icons.Default.SentimentVeryDissatisfied, contentDescription = "",
            Modifier
                .weight(0.5f, true)
                .fillMaxSize(0.5f))
        Text(text = stringResource(id = R.string.Ures_lista),modifier=Modifier.weight(0.5f))
    }
}