package hu.bme.aut.android.receptes_konyv.feature.Recipe_List

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.receptes_konyv.R
import hu.bme.aut.android.receptes_konyv.ui.common.RecipeEditor
import hu.bme.aut.android.receptes_konyv.ui.common.TypeDropDown
import hu.bme.aut.android.receptes_konyv.ui.model.TypeUI
import hu.bme.aut.android.receptes_konyv.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen (ItemClicked:(Int)->Unit,CreateClicked:()->Unit,viewModel: RecipeListViewModel= hiltViewModel()){
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    val list = state.recipes

    
    Scaffold (floatingActionButton = { FloatingActionButton(onClick =  CreateClicked) {
        Icon(Icons.Default.Add, contentDescription = "")
    }},
        topBar = { TopAppBar( colors = TopAppBarColors(containerColor = Color(DarkBlue.value), actionIconContentColor = Color.White, navigationIconContentColor = Color.White, scrolledContainerColor = Color.White, titleContentColor = Color.White),title = { Text(text = "ReceptesKönyv") }) }
        ) { innerPadding->
        if(state.recipes.isEmpty()){
            EmptyList(modifier = Modifier.padding(innerPadding))
        }
        else{

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
        Text(text = stringResource(id = R.string.Ures_lista),modifier=Modifier.weight(0.5f), fontSize = 20.sp)
    }
}

