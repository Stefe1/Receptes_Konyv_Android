package hu.bme.aut.android.receptes_konyv.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.android.receptes_konyv.data.converters.BooleanConverter
import hu.bme.aut.android.receptes_konyv.data.converters.LocalDateConverter
import hu.bme.aut.android.receptes_konyv.data.converters.RecipeTypeConverter
import hu.bme.aut.android.receptes_konyv.data.dao.RecipeDao
import hu.bme.aut.android.receptes_konyv.data.entities.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 3, exportSchema = false)
@TypeConverters(RecipeTypeConverter::class,LocalDateConverter::class,BooleanConverter::class)
abstract class RecipeDatabase :RoomDatabase(){
    abstract val dao:RecipeDao
}