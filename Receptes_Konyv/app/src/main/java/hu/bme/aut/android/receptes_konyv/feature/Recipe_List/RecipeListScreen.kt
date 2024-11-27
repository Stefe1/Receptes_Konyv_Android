package hu.bme.aut.android.receptes_konyv.feature.Recipe_List

import android.provider.MediaStore.Images
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.receptes_konyv.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun RecipeListScreen (ItemClicked:(Int)->Unit,CreateClicked:()->Unit,viewModel: RecipeListViewModel= hiltViewModel()){
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    Scaffold (floatingActionButton = { FloatingActionButton(onClick =  CreateClicked) {
        Icon(Icons.Default.Add, contentDescription = "")
    }}) { innerPadding->
        if(state.recipes.isEmpty()){
            Column (modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                Text(text = stringResource(id = R.string.Ures_lista))
            }
        }
    }
}