package firmanpoke.com.apps.ui.presentation.main.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import firmanpoke.com.apps.R
import firmanpoke.com.apps.data.remote.RetrofitInstance
import firmanpoke.com.apps.ui.state.UIState
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context = application.applicationContext
    private val retrofitInstance = RetrofitInstance(application)
    private val limit = 10
    private var offset = 0
    var listPokemon: MutableLiveData<UIState> = MutableLiveData()

    fun getListPokemon() = viewModelScope.launch {
        listPokemon.value = UIState.OnLoading
        try {
            offset++
            val result = retrofitInstance.api.getListPokemon(limit, offset)
            if (result.isSuccessful) {
                if (result.body()?.results?.isNotEmpty() == true)
                    listPokemon.value = UIState.OnSuccess(result.body())
                else
                    listPokemon.value = UIState.OnError(context.getString(R.string.no_more_data))
            } else
                listPokemon.value =
                    UIState.OnError(context.getString(R.string.something_wrong_try_again))
        } catch (e: Exception) {
            listPokemon.value =
                UIState.OnError(e.message ?: context.getString(R.string.something_wrong_try_again))
        }
    }
}