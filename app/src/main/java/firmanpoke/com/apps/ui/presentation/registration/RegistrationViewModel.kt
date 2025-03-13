package firmanpoke.com.apps.ui.presentation.registration

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import firmanpoke.com.apps.R
import firmanpoke.com.apps.data.local.DatabaseHelper
import firmanpoke.com.apps.data.remote.RetrofitInstance
import firmanpoke.com.apps.domain.model.User
import firmanpoke.com.apps.ui.state.UIState
import kotlinx.coroutines.launch

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = DatabaseHelper(application)

    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> = _registerResult


    fun register(username: String, password: String, name: String, email: String) {
        viewModelScope.launch {
            _registerResult.value = dbHelper.registerUser(username, password, name, email)
        }
    }
}