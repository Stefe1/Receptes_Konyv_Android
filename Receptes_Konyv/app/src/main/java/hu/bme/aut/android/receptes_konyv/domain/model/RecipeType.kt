package hu.bme.aut.android.receptes_konyv.domain.model

enum class RecipeType {
    None,
    Foetel,
    Leves,
    Desszert;
    companion object{
        val RecipeTypes= listOf(None,Foetel,Leves,Desszert)
    }
}