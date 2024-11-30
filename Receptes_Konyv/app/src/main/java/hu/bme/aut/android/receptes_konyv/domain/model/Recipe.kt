package hu.bme.aut.android.receptes_konyv.domain.model

import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity
import kotlinx.datetime.LocalDate

data class Recipe(
    val id: Int,
    val title:String,
    val description: String,
    val ingredients: String,
    val type: RecipeType,
    val date: LocalDate
    )

fun RecipeEntity.asRecipe(): Recipe =Recipe(
    id=id,
    title=title,
    description=description,
    ingredients = ingredients,
    type=type,
    date=date
    )

fun Recipe.asRecipeEntity(): RecipeEntity =RecipeEntity(
    id=id,
    title=title,
    description=description,
    ingredients=ingredients,
    type=type,
    date=date
)
