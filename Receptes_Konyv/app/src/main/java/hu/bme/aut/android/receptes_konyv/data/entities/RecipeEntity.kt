package hu.bme.aut.android.receptes_konyv.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.android.receptes_konyv.domain.model.RecipeType

@Entity(tableName = "Recipe_table")
data class RecipeEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title:String,
    val description: String,
    val ingredients:String,
    val type: RecipeType
)