package firmanpoke.com.apps.ui.presentation.main.home

data class HomeModelResponse(
    val count : Int,
    val results : List<Pokemons>
) {
    data class Pokemons(
        val name : String,
        val url :String
    )
}
