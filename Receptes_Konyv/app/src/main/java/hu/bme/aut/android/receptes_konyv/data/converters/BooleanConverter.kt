package hu.bme.aut.android.receptes_konyv.data.converters

import androidx.room.TypeConverter

object BooleanConverter {

    @TypeConverter
    fun Boolean.asInt(): Int = if(this) 1 else 0

    fun Int.asBoolean(): Boolean= if(this==1) true else false

}