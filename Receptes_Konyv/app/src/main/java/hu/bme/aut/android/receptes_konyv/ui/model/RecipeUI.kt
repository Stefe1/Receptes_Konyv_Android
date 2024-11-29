package hu.bme.aut.android.receptes_konyv.ui.model

import android.icu.text.CaseMap.Title
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.model.RecipeType

data class RecipeUI(
    val id:Int=0,
    val title: String ="",
    val description: String="",
    val ingredients: String="",
    val type: TypeUI=TypeUI.Egyeb

)

fun Recipe.asRecipeUI(): RecipeUI=RecipeUI(
    id=id,
    title=title,
    description=description,
    ingredients = ingredients,
    type = type.asTypeUI())

fun RecipeUI.asRecipe():Recipe=Recipe(
    id=id,
    title=title,
    description=description,
    ingredients=ingredients,
    type = type.asRecipeType()
    )
