package hu.bme.aut.android.receptes_konyv.feature.Recipe_List

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.receptes_konyv.R
import hu.bme.aut.android.receptes_konyv.data.datastore.Settings
import hu.bme.aut.android.receptes_konyv.ui.animation.LoadingCirlce
import hu.bme.aut.android.receptes_konyv.ui.model.RecipeUI
import hu.bme.aut.android.receptes_konyv.ui.model.asRecipe
import hu.bme.aut.android.receptes_konyv.ui.model.toUiText
import hu.bme.aut.android.receptes_konyv.ui.theme.DarkBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen (ItemClicked:(Int)->Unit,CreateClicked:()->Unit,viewModel: RecipeListViewModel= hiltViewModel()){
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    val list = state.recipes
    val shape= RoundedCornerShape(5.dp)

    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val settings= Settings(context)

    val theme= settings.getAppTheme.collectAsState(initial = "light")

    val sorting= settings.getSorting.collectAsState(initial = "none")


    Scaffold (floatingActionButton = { LargeFloatingActionButton(onClick =  CreateClicked, containerColor = MaterialTheme.colorScheme.primary,) {
        Icon(Icons.Default.Add, contentDescription = "")
    }
    },
        topBar = { TopAppBar( colors = TopAppBarColors(containerColor = MaterialTheme.colorScheme.primary, actionIconContentColor = MaterialTheme.colorScheme.onPrimary, navigationIconContentColor = MaterialTheme.colorScheme.onPrimary, scrolledContainerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.onPrimary),
            title = { Text(text = stringResource(id = R.string.Top_App_Bar_Cim)) },
            actions = {

                IconButton(onClick = {expanded=true}){
                    Icon(imageVector = Icons.Default.Sort,"")
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded=false }) {
                        DropdownMenuItem(text = { Text(text = stringResource(id = R.string.Abc_sorrned), color = MaterialTheme.colorScheme.onSecondary) }, onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                settings.saveSorting("title")
                            }

                            expanded=false
                            },
                            leadingIcon = { Icon(
                                imageVector = Icons.Default.Abc,
                                contentDescription = "")},
                            trailingIcon = {
                            if(sorting.value=="title")
                                Icon(imageVector = Icons.Default.Check, contentDescription = "")
                        })
                        DropdownMenuItem(text = { Text(text = stringResource(id = R.string.Ido_sorrend), color = MaterialTheme.colorScheme.onSecondary) },
                            onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                settings.saveSorting("date")
                            }

                            expanded=false
                        }
                            , leadingIcon = { Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = ""
                        )},
                            trailingIcon = {
                            if(sorting.value=="date")
                                Icon(imageVector = Icons.Default.Check, contentDescription = "")
                        })
                        DropdownMenuItem(text = { Text(text = stringResource(id = R.string.Nincs_sorrend), color = MaterialTheme.colorScheme.onSecondary) },
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    settings.saveSorting("none")
                                }

                                expanded=false
                            },
                            leadingIcon = { Icon(
                            imageVector = Icons.Default.Block,
                            contentDescription = ""
                        )}, trailingIcon = {
                            if(sorting.value=="none")
                                Icon(imageVector = Icons.Default.Check, contentDescription = "")
                        })
                    }
                }


                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        settings.saveTheme(theme.value)
                    }
                }) {
                    if(theme.value!= "light"){
                        Icon(imageVector = Icons.Default.WbSunny, contentDescription = "")
                    }
                    else{
                        Icon(imageVector = Icons.Default.DarkMode, contentDescription = "")
                    }
                }

                IconButton(onClick = {viewModel.deleteAllRecipes()}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }
            }

        ) }
        ) { innerPadding->
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
        } else if (state.isError) {
            Text(
                text = state.error?.toUiText()?.asString(context)
                    ?: stringResource(id = R.string.error_message)
            )
        }


            else
            {
                if(state.recipes.isEmpty()){
                    EmptyList(modifier = Modifier.padding(innerPadding))
                }
                else{
                    val sortedList: List<RecipeUI> = when(sorting.value){
                        "title" -> list.sortedBy { it.title.lowercase() }
                        "date"  -> list.sortedBy { it.date }
                        else -> list
                    }
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding)){

                        LazyColumn(modifier = Modifier
                            .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize()) {

                            items(sortedList){recipe->
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
                                        .background(Color.White)
                                        .clickable {
                                            ItemClicked(
                                                state.recipes[state.recipes.indexOf(
                                                    recipe
                                                )].id
                                            )
                                        }
                                        .background(Color.Black),
                                    supportingContent = {
                                        Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                            if(!recipe.edited){
                                                Text(
                                                    text = stringResource(id = R.string.Letrehozva, "${recipe.date}"),
                                                    fontSize = 16.sp
                                                )
                                            }
                                            else{
                                                Text(
                                                    text = stringResource(id = R.string.Modositott, "${recipe.date}"),
                                                    fontSize = 16.sp
                                                )
                                            }
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
}
@Composable
fun EmptyList(modifier: Modifier){
    Column (modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Icon(imageVector = Icons.Default.SentimentVeryDissatisfied, contentDescription = "",
            Modifier
                .weight(0.5f, true)
                .fillMaxSize(0.5f))
        Text(text = stringResource(id = R.string.Ures_lista),modifier=Modifier.weight(0.5f), fontSize = 20.sp)
    }
}
