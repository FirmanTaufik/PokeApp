package firmanpoke.com.apps.ui.presentation.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import firmanpoke.com.apps.R
import firmanpoke.com.apps.ui.presentation.base.BaseActivity
import firmanpoke.com.apps.databinding.ActivityLoginBinding
import firmanpoke.com.apps.helper.Constant
import firmanpoke.com.apps.helper.showToast
import firmanpoke.com.apps.helper.startNewActivity
import firmanpoke.com.apps.ui.presentation.main.MainActivity
import firmanpoke.com.apps.ui.presentation.registration.RegistrationActivity

class LoginActivity : BaseActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var userViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModel()
        iniOnClick()
    }

    private fun iniOnClick() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (inputNotEmpty()) {
                    loginUser()
                }
            }
            txtRegister.setOnClickListener { startNewActivity<RegistrationActivity>() }
        }
    }

    private fun inputNotEmpty(): Boolean {
        if (binding.edtUsername.text.isNullOrEmpty()) {
            "username is still empty".showToast(this)
            return false
        }
        if (binding.edtPassword.text.isNullOrEmpty()) {
            "password is still empty".showToast(this)
            return false
        }
        return true
    }


    private fun loginUser(){
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()
        userViewModel.postLogin(username, password)
        userViewModel.loginResult.observe(this) { user ->
            if (user != null) {
                "Success Login as ${user.name}"
                startNewActivity<MainActivity>()
            } else {
                "Username or password is wrong".showToast(this)
            }
        }
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }
}