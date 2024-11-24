package hu.bme.aut.android.receptes_konyv.domain.model

enum class RecipeType {
    Foetel,
    Leves,
    Desszert;
    companion object{
        val RecipeTypes= listOf(Foetel,Leves,Desszert)
    }
}