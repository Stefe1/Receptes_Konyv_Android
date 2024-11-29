package hu.bme.aut.android.receptes_konyv.feature.Recipe_List

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen (ItemClicked:(Int)->Unit,CreateClicked:()->Unit,viewModel: RecipeListViewModel= hiltViewModel()){
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    val list = state.recipes
    val shape= RoundedCornerShape(5.dp)

    
    Scaffold (floatingActionButton = { LargeFloatingActionButton(onClick =  CreateClicked) {
        Icon(Icons.Default.Add, contentDescription = "")
    }
    },
        topBar = { TopAppBar( colors = TopAppBarColors(containerColor = Color(DarkBlue.value), actionIconContentColor = Color.White, navigationIconContentColor = Color.White, scrolledContainerColor = Color.White, titleContentColor = Color.White),title = { Text(text = "ReceptesKönyv") }) }
        ) { innerPadding->
        if(state.recipes.isEmpty()){
            EmptyList(modifier = Modifier.padding(innerPadding))
        }
        else{
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(innerPadding)){

                LazyColumn(modifier = Modifier.padding(top = 5.dp)) {

                    items(list){recipe->
                        ListItem(
                            headlineContent =
                            {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = recipe.type.icon,
                                        contentDescription = "",
                                        tint = recipe.type.color,
                                        modifier = Modifier.size(25.dp)
                                    )
                                    Text(text = recipe.title)
                                }
                            },
                            modifier = Modifier
                                .clip(shape)
                                .clickable { ItemClicked(list.indexOf(recipe)) },
                            supportingContent = {
                                Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Létre hozva: ${LocalDate.now()}",
                                        fontSize = 16.sp
                                    )
                                }
                            },

                            )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
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
/*Column (modifier = Modifier
                            .fillMaxWidth()
                            .height(TextFieldDefaults.MinHeight)
                            .clip(shape)
                            .background(Color.White), verticalArrangement = Arrangement.spacedBy(10.dp)){

                        }*/
