package firmanpoke.com.apps.ui.presentation.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import firmanpoke.com.apps.R
import firmanpoke.com.apps.databinding.FragmentProfileBinding
import firmanpoke.com.apps.domain.model.User
import firmanpoke.com.apps.helper.showToast
import firmanpoke.com.apps.helper.startNewActivity
import firmanpoke.com.apps.ui.presentation.login.LoginActivity
import firmanpoke.com.apps.ui.presentation.login.LoginViewModel
import firmanpoke.com.apps.ui.presentation.main.MainActivity

class ProfileFragment : Fragment() {
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setUpViewModel()
        initOnClick()
        return binding.root
    }

    private fun initOnClick() {
        binding.btnLogout.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog(   ) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Confirmation")
            .setMessage("Are you sure want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                profileViewModel.logOut()
                requireActivity().startNewActivity<LoginActivity>()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun setUpViewModel() {
        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)
        profileViewModel.result.observe(requireActivity()) { user ->
            if (user != null) {
                setUpUI(user)
            }
        }
    }

    private fun setUpUI(user: User){
        binding.apply {
            txtUsername.text =  user.username
            txtName.text = user.name
            txtEmail.text =  user.email
        }
    }
}