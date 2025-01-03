package hu.bme.aut.android.receptes_konyv.data.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

object LocalDateConverter {

    @TypeConverter
    fun LocalDate.asString(): String = this.toString()

    @TypeConverter
    fun String.asLocalDateTime(): LocalDate = this.toLocalDate()

}