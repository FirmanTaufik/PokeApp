package firmanpoke.com.apps.ui.presentation.detail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import firmanpoke.com.apps.R
import firmanpoke.com.apps.data.remote.RetrofitInstance
import firmanpoke.com.apps.ui.state.UIState
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private val retrofitInstance = RetrofitInstance(application)
    var detailPokemon: MutableLiveData<UIState> = MutableLiveData()

    fun getDetail(name :String) = viewModelScope.launch {
        detailPokemon.value = UIState.OnLoading
        try {
            val result = retrofitInstance.api.getDetail(name)
            if (result.isSuccessful) {
                detailPokemon.value = UIState.OnSuccess(result.body())
            } else
                detailPokemon.value =
                    UIState.OnError(context.getString(R.string.something_wrong_try_again))
        } catch (e: Exception) {
            detailPokemon.value =
                UIState.OnError(e.message ?: context.getString(R.string.something_wrong_try_again))
        }
    }


}