package hu.bme.aut.android.receptes_konyv.domain.model

enum class RecipeType {
    Egyeb,
    Foetel,
    Leves,
    Desszert;
    companion object{
        val RecipeTypes= listOf(Egyeb,Foetel,Leves,Desszert)
    }
}