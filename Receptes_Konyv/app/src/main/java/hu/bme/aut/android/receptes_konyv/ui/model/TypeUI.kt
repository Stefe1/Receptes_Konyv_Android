package hu.bme.aut.android.receptes_konyv.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import hu.bme.aut.android.receptes_konyv.domain.model.RecipeType
import hu.bme.aut.android.receptes_konyv.ui.theme.LightBrown

sealed class TypeUI(
    val title: String,
    val color: Color,
    val icon:ImageVector
){
    object None: TypeUI(title = "None",
        color = Color.Gray,
        icon = Icons.Default.NotInterested
        )
    object Foetel: TypeUI(
        title = "Foetel",
        color = Color(LightBrown.value),
        icon = Icons.Default.LunchDining
    )
    object Leves: TypeUI(
        title = "Leves",
        color = Color.Yellow,
        icon = Icons.Default.SoupKitchen
    )
    object Desszert: TypeUI(
        title = "Desszert",
        color = Color.Magenta,
        icon = Icons.Default.Icecream
    )
}
fun TypeUI.asRecipeType(): RecipeType{
    return when(this){
        is TypeUI.None-> RecipeType.None
        is TypeUI.Foetel-> RecipeType.Foetel
        is TypeUI.Leves-> RecipeType.Leves
        is TypeUI.Desszert-> RecipeType.Desszert

    }

}

fun RecipeType.asTypeUI(): TypeUI{
    return when(this){
        RecipeType.None -> TypeUI.None
        RecipeType.Foetel->TypeUI.Foetel
        RecipeType.Leves->TypeUI.Leves
        RecipeType.Desszert->TypeUI.Desszert
    }
}
