package firmanpoke.com.apps.ui.presentation.login

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

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = DatabaseHelper(application)

    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult


    fun postLogin(username :String, password:String) = viewModelScope.launch {
        _loginResult.value = dbHelper.loginUser(username, password)
    }
}