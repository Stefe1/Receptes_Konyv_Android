package hu.bme.aut.android.receptes_konyv.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM Recipe_table")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM Recipe_table WHERE id = :id")
    fun getRecipebyId(id: Int): Flow<RecipeEntity>

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM Recipe_table WHERE id = :id")
    suspend fun deleteRecipe(id: Int)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

}