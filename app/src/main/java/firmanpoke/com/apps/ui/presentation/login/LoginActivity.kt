package firmanpoke.com.apps.ui.presentation.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import firmanpoke.com.apps.R
import firmanpoke.com.apps.base.BaseActivity
import firmanpoke.com.apps.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun setView() = binding.main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}