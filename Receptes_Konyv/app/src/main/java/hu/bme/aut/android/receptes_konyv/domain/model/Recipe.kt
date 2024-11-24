package hu.bme.aut.android.receptes_konyv.domain.model

import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity

data class Recipe(
    val id: Int,
    val title:String,
    val description: String,
    val type: RecipeType
    )

fun RecipeEntity.asRecipe(): Recipe =Recipe(
    id=id,
    title=title,
    description=description,
    type=type
    )

fun Recipe.asRecipeEntity(): RecipeEntity =RecipeEntity(
    id=id,
    title=title,
    description=description,
    type=type
)
