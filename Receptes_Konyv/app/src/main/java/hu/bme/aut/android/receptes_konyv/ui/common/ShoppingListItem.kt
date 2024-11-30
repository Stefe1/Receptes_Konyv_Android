package hu.bme.aut.android.receptes_konyv.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(Ingredient:String, modifier: Modifier=Modifier){
    var checked by remember { mutableStateOf(false) }
    Surface(modifier= modifier
        .height(TextFieldDefaults.MinHeight)
        .clip(RoundedCornerShape(5.dp))) {
        Row(modifier= modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = {checked= !checked })
            Text(text = Ingredient, color=MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Preview
@Composable
fun previewShoppingListItem(){
    Box(modifier = Modifier.fillMaxSize(), ){
        ShoppingListItem(Ingredient = "1 kg Tészte", modifier=Modifier.fillMaxWidth())
    }
}