package hu.bme.aut.android.receptes_konyv.data.converters

import android.renderscript.RenderScript.Priority
import androidx.room.TypeConverter
import hu.bme.aut.android.receptes_konyv.domain.model.RecipeType

object RecipeTypeConverter{

    @TypeConverter
    fun RecipeType.asString():String=this.name

    @TypeConverter
    fun String.asRecipeType(): RecipeType {
        return when(this){
            RecipeType.Egyeb.name->RecipeType.Egyeb
            RecipeType.Foetel.name->RecipeType.Foetel
            RecipeType.Leves.name->RecipeType.Leves
            RecipeType.Desszert.name->RecipeType.Desszert
            else ->RecipeType.Egyeb
        }
    }

}