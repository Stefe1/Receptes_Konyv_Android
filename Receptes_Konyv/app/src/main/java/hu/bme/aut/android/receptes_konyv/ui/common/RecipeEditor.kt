package hu.bme.aut.android.receptes_konyv.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.receptes_konyv.R
import hu.bme.aut.android.receptes_konyv.ui.model.RecipeUI
import hu.bme.aut.android.receptes_konyv.ui.model.TypeUI


@Composable
fun RecipeEditor(titleValue: String,
                 descriptionValue:String,
                 ingredientsValue:String,
                 typeUI: TypeUI,
                 onTitleChange:(String)->Unit,
                 onDescriptionChange:(String)->Unit,
                 onIngredientsChange:(String)->Unit,
                 onTypeChange:(TypeUI)->Unit

){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround
        ) {
            NormalTextField(value = titleValue, label = stringResource(R.string.Cim), onValueChange = onTitleChange, modifier = Modifier.fillMaxWidth(0.95f).padding(top = 10.dp)) {}
            Spacer(modifier = Modifier.height(15.dp))
            TypeDropDown(selected = typeUI, modifier = Modifier.fillMaxWidth(0.95f), onTypeSelected = onTypeChange)
            Spacer(modifier = Modifier.height(15.dp))
            NormalTextField(value = ingredientsValue, label = stringResource(id = R.string.Hozzavalok), onValueChange = onIngredientsChange, singleLine = false, modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.2f)) {}

            Spacer(modifier = Modifier.height(15.dp))
            NormalTextField(value = descriptionValue, label = stringResource(id = R.string.Leiras), onValueChange = onDescriptionChange, singleLine = false,modifier = Modifier.fillMaxHeight().fillMaxWidth(0.95f).padding(bottom = 10.dp)) {}



    }
}
@Preview(showBackground = true)
@Composable
fun RecipeEditorPreview(){
    Box(modifier = Modifier.fillMaxSize()){
        RecipeEditor("","","",TypeUI.Egyeb,{},{},{},{})
    }

}