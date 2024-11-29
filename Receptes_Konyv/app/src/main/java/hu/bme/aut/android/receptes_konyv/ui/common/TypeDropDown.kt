package hu.bme.aut.android.receptes_konyv.ui.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.receptes_konyv.ui.model.TypeUI
import kotlin.math.log

@Composable
fun TypeDropDown(types:List<TypeUI>,selected:TypeUI,modifier: Modifier=Modifier, onTypeSelected: (TypeUI)->Unit){
    var expanded by remember { mutableStateOf(false) }

    Surface (modifier= Modifier
        .width(TextFieldDefaults.MinWidth)) {
        Row(modifier= Modifier
            .padding(10.dp)
            .clickable { expanded = true }, horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = selected.icon, contentDescription ="", tint = selected.color )
            Text(text = selected.title)
            Spacer(modifier = Modifier.width(120.dp))
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded=false }, modifier = Modifier
                .width(TextFieldDefaults.MinWidth)) {
                types.forEach { type->
                    Log.v("a","1")
                    DropdownMenuItem(text = { Text(text = type.title) },
                        onClick = { expanded=false
                            onTypeSelected(type)
                        }, leadingIcon = { Icon(imageVector = type.icon, contentDescription = "", tint = type.color)})

                }
            }

        }

    }
}



@Preview
@Composable
fun TypeDropDownPreview(){
    var selected:TypeUI by remember {
        mutableStateOf(TypeUI.Desszert)
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        TypeDropDown(types = listOf(TypeUI.None,TypeUI.Foetel,TypeUI.Leves,TypeUI.Desszert), selected = selected) {
            selected=it
        }
    }
}