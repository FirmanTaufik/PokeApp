package firmanpoke.com.apps.ui.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import firmanpoke.com.apps.data.preferences.UserPreferences
import firmanpoke.com.apps.data.local.DatabaseHelper
import firmanpoke.com.apps.domain.model.User
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences =  UserPreferences(application)
    private val dbHelper = DatabaseHelper(application)

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> get() = _isUserLoggedIn


    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult


    init {
        _isUserLoggedIn.value = userPreferences.getUsername() != null

    }
    fun postLogin(username :String, password:String) = viewModelScope.launch {
        _loginResult.value = dbHelper.loginUser(username, password)
    }

    fun saveUsername(username: String) {
        userPreferences.saveUsername(username)
    }



}