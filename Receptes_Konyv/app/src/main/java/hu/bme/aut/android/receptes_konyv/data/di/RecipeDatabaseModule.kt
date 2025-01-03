package hu.bme.aut.android.receptes_konyv.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.receptes_konyv.data.RecipeDatabase
import hu.bme.aut.android.receptes_konyv.data.dao.RecipeDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeDatabaseModule {

    @Provides
    @Singleton
    fun providesRecipeDatabase(@ApplicationContext context: Context): RecipeDatabase= Room.databaseBuilder(context,RecipeDatabase::class.java,"recipe_db").
    fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesRecipeDao(db :RecipeDatabase ): RecipeDao=db.dao
}