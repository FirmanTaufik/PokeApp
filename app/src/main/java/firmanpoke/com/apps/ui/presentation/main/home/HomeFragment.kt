package firmanpoke.com.apps.ui.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import firmanpoke.com.apps.R
import firmanpoke.com.apps.databinding.FragmentHomeBinding
import firmanpoke.com.apps.helper.gone
import firmanpoke.com.apps.helper.showToast
import firmanpoke.com.apps.helper.visible
import firmanpoke.com.apps.ui.state.UIState

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var adapter : ItemAdapter
    private lateinit var homeViewModel: HomeViewModel
    private var items: ArrayList<HomeModelResponse.Pokemons> = arrayListOf()
    private lateinit var linearLayoutManager : LinearLayoutManager
    private var isScrolling = false
    private var currentItems = 0
    private var totalItems:Int = 0
    private var scrollOutItems:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        initSearchView()
        initViewModel()
        initRecycleView()
        getData()
        return binding.root
    }

    private fun initSearchView() {
        binding.searchView.apply {
            isIconified = false // Expand SearchView
            requestFocus()
            setOnSearchClickListener {
                "cek".showToast(requireActivity())
            }
        }

    }

    private fun initRecycleView() {
        adapter = ItemAdapter(items)
        linearLayoutManager = LinearLayoutManager(requireActivity())

        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = linearLayoutManager.childCount
                totalItems = linearLayoutManager.itemCount
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition()
                if (isScrolling && currentItems + scrollOutItems == totalItems) {
                    isScrolling = false
                    homeViewModel.getListPokemon()
                }
            }
        })
    }


    private fun getData() {
        homeViewModel.listPokemon.observe(requireActivity()) {
                state ->
            when (state) {
                is UIState.OnLoading -> {
                     binding.progressBar.visible()
                }
                is UIState.OnSuccess<*> -> {
                    binding.progressBar.gone()
                    val result = state.data as HomeModelResponse
                    items.addAll(result.results)
                    adapter.notifyDataSetChanged()
                }
                else -> {
                    binding.progressBar.gone()
                }
            }
        }
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        homeViewModel.getListPokemon()
    }


}