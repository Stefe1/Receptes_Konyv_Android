package hu.bme.aut.android.receptes_konyv.ui.model

import android.icu.text.CaseMap.Title
import hu.bme.aut.android.receptes_konyv.domain.model.Recipe
import hu.bme.aut.android.receptes_konyv.domain.model.RecipeType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import java.time.LocalDateTime

data class RecipeUI(
    val id:Int=0,
    val title: String ="",
    val description: String="",
    val ingredients: String="",
    val type: TypeUI=TypeUI.Egyeb,
    val date: String = LocalDate(
        LocalDateTime.now().year,
        LocalDateTime.now().monthValue,
        LocalDateTime.now().dayOfMonth
    ).toString()
)

fun Recipe.asRecipeUI(): RecipeUI=RecipeUI(
    id=id,
    title=title,
    description=description,
    ingredients = ingredients,
    type = type.asTypeUI(),
    date = date.toString()
    )

fun RecipeUI.asRecipe():Recipe=Recipe(
    id=id,
    title=title,
    description=description,
    ingredients=ingredients,
    type = type.asRecipeType(),
    date = date.toLocalDate()
    )
