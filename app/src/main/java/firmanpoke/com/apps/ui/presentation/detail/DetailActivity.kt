package firmanpoke.com.apps.ui.presentation.detail

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import firmanpoke.com.apps.databinding.ActivityDetailBinding
import firmanpoke.com.apps.helper.Constant
import firmanpoke.com.apps.helper.gone
import firmanpoke.com.apps.helper.showToast
import firmanpoke.com.apps.helper.visible
import firmanpoke.com.apps.ui.presentation.base.BaseActivity
import firmanpoke.com.apps.ui.state.UIState

class DetailActivity : BaseActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModel()
        getData()
    }

    private fun initViewModel() {
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.detailPokemon.observe(this) { state ->
            when (state) {
                is UIState.OnLoading -> {
                    binding.progressBar.visible()
                }

                is UIState.OnSuccess<*> -> {
                    binding.progressBar.gone()
                    val result = state.data as DetailModelResponse
                    showPokemonData(result)

                }
                is UIState.OnError ->{
                    binding.progressBar.gone()
                    val msg = state.message
                    msg.showToast(this)
                }
                else -> {
                    binding.progressBar.gone()
                }
            }
        }
    }

    private fun getData() {
        val name = intent.getStringExtra(Constant.Pokemon.NAME)
        detailViewModel.getDetail(name ?: "")
    }

    private fun showPokemonData(pokemon: DetailModelResponse) {
        binding.apply {
            tvPokemonName.text = pokemon.name
            tvPokemonName.visible()
            tvAbilitiesTitle.visible()
            llAbilities.visible()
            llAbilities.removeAllViews()
            for (ability in pokemon.abilities) {
                val textView = TextView(this@DetailActivity).apply {
                    text = "- ${ability.ability?.name}"
                    textSize = 16f
                }
                llAbilities.addView(textView)
            }
        }
    }

}