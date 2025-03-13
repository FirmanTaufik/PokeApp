package firmanpoke.com.apps.ui.presentation.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import firmanpoke.com.apps.R
import firmanpoke.com.apps.databinding.ActivityDetailBinding
import firmanpoke.com.apps.ui.presentation.base.BaseActivity

class DetailActivity : BaseActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}