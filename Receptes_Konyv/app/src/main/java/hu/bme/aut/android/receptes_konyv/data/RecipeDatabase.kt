package hu.bme.aut.android.receptes_konyv.data

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.receptes_konyv.data.dao.RecipeDao
import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase :RoomDatabase(){
    abstract val dao:RecipeDao
}