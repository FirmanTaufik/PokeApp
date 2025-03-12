package firmanpoke.com.apps.ui.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import firmanpoke.com.apps.R
import firmanpoke.com.apps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var adapter : ItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setUpAdapter()
        return binding.root
    }

    private fun setUpAdapter() {
        adapter = ItemAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
    }
}