package firmanpoke.com.apps.ui.presentation.detail

data class DetailModelResponse(
    val id: Int,
    val name: String,
    val abilities: List<AbilityWrapper>,

){
    data class AbilityWrapper(val ability: Ability)
    data class Ability(val name: String)
}
