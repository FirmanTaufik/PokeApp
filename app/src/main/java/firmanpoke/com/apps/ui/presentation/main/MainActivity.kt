package firmanpoke.com.apps.ui.presentation.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import firmanpoke.com.apps.R
import firmanpoke.com.apps.ui.presentation.base.BaseActivity
import firmanpoke.com.apps.databinding.ActivityMainBinding
import firmanpoke.com.apps.helper.showToast
import firmanpoke.com.apps.ui.presentation.main.home.HomeFragment
import firmanpoke.com.apps.ui.presentation.main.profile.ProfileFragment
import kotlin.system.exitProcess

class MainActivity : BaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var doubleBackToExitPressedOnce = false

    override fun setView() = binding.main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        val fragments = arrayListOf(HomeFragment(), ProfileFragment())
        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.home)
                1 -> getString(R.string.profile)
                else -> ""
            }
        }.attach()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
            exitProcess(0)
        }
        this.doubleBackToExitPressedOnce = true
        "Press back again to exit".showToast(this)
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}