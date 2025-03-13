package firmanpoke.com.apps.ui.presentation.registration

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import firmanpoke.com.apps.databinding.ActivityRegistrationBinding
import firmanpoke.com.apps.helper.showToast
import firmanpoke.com.apps.ui.presentation.base.BaseActivity

class RegistrationActivity : BaseActivity() {
    private val binding by lazy { ActivityRegistrationBinding.inflate(layoutInflater) }
    private lateinit var userViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModel()
        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            txtLogin.setOnClickListener { finish() }
            btnRegister.setOnClickListener {
                if (inputNotEmpty()) {
                    postRegister()
                }
            }
        }
    }

    private fun postRegister() {
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()
        val name =binding.edtName .text.toString()
        val email = binding.edtEmail.text.toString()
        userViewModel.register(username, password, name, email)
        userViewModel.registerResult.observe(this) { isSuccess ->
            if (isSuccess) {
                "Registration Successful!".showToast(this)
                finish()
            } else {
                "Username already exists".showToast(this)            }
        }
    }

    private fun inputNotEmpty(): Boolean {
        if (binding.edtUsername.text.isNullOrEmpty()) {
            "username is still empty".showToast(this)
            return false
        }
        if (binding.edtEmail.text.isNullOrEmpty()) {
            "email is still empty".showToast(this)
            return false
        }
        if (binding.edtName.text.isNullOrEmpty()) {
            "name is still empty".showToast(this)
            return false
        }
        if (binding.edtPassword.text.isNullOrEmpty()) {
            "password is still empty".showToast(this)
            return false
        }
        return true
    }


    private fun initViewModel() {
        userViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }
}