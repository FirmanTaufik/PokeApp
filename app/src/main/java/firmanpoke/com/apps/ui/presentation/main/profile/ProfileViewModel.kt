package firmanpoke.com.apps.ui.presentation.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import firmanpoke.com.apps.data.preferences.UserPreferences
import firmanpoke.com.apps.data.local.DatabaseHelper
import firmanpoke.com.apps.domain.model.User
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)
    private val dbHelper = DatabaseHelper(application)

    private val _result = MutableLiveData<User?>()
    val result: LiveData<User?> = _result

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        _result.value = dbHelper.getUser(userPreferences.getUsername()?:"")
    }

    fun logOut(){
        userPreferences.clearUserData()
    }

}