package com.farad.entertainment.aramkada.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.farad.entertainment.aramkada.base.BottomNavigationFragment
import com.farad.entertainment.aramkada.data.model.CategoryItemModel
import com.farad.entertainment.aramkada.data.model.CategoryModel
import com.farad.entertainment.aramkada.databinding.FragmentHomeBinding
import com.farad.entertainment.aramkada.ui.fragment.home.adapter.CategoryAdapter
import com.farad.entertainment.aramkada.ui.fragment.home.adapter.CategoryItemAdapter
import com.farad.entertainment.aramkada.ui.vm.HomeViewModel
import com.farad.entertainment.aramkada.utils.EventObserver
import com.farad.entertainment.aramkada.utils.checkStateScrollL
import com.farad.entertainment.aramkada.utils.invisible
import com.farad.entertainment.aramkada.utils.visible
import com.farad.entertainment.aramkada.utils.visibleOrGone
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BottomNavigationFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModel<HomeViewModel>()

    private val listCategory = ArrayList<CategoryModel>()
    private val listCategoryItemModel = ArrayList<CategoryItemModel>()

    private val categoryAdapter = CategoryAdapter()
    private val categoryItemAdapter = CategoryItemAdapter()


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setup() {
        getData()
        initRecyclerview()
    }

    private fun getData() {
        if (listCategory.isEmpty())
            homeViewModel.fetchCombinedCategoryData()
    }

    private fun initRecyclerview() {

        binding.recyclerviewCategory.adapter = categoryAdapter.apply {

            setOnItemClickListener {
                if (isNullView().not()) {
                    binding.progressBar.post {

                        binding.progressBar.visible(true)
                    }
                }
                lifecycleScope.launch {

                    homeViewModel.fetchCombinedCategoryData(it.id.toLong())
                }
            }
        }
        binding.recyclerviewItem.adapter = categoryItemAdapter.apply {
            setOnItemClickListener {
                val navigate = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                navigate(navigate)
            }
        }

        binding.recyclerviewItem.apply {
            checkStateScrollL {
                getMainActivity()?.showBottomNavigation(it)
            }

        }


    }

    override fun initObserveViewModel() {
        lifecycleScope.launch {

            try {
                homeViewModel.combinedCategoryData
                    .onStart {


                        if (isNullView().not()) {
                            binding.progressBar.post {

                                binding.progressBar.visible(true)
                            }
                        }
                    }
                    .onCompletion {

                        if (isNullView().not()) {
                            binding.progressBar.invisible(true)
                        }
                    }
                    .collect { combinedList ->
                        binding.progressBar.invisible(true)
                        listCategory.clear()
                        listCategoryItemModel.clear()
                        listCategory.addAll(combinedList.first)

                        listCategoryItemModel.addAll(combinedList.second)


                        categoryAdapter.submitList(listCategory)
                        categoryItemAdapter.submitList(listCategoryItemModel)
                    }
            } catch (e: Exception) {
                e.fillInStackTrace()
            }

        }

        homeViewModel.combinePeriodLiveData.observe(this) { pair ->

            //   listCategory.clear()
            //   listCategoryItemModel.clear()

            pair.first?.let { category ->
                listCategory.addAll(category)
            }
            pair.second?.let { item ->
                listCategoryItemModel.addAll(item)
            }
            categoryAdapter.submitList(listCategory)
            categoryItemAdapter.submitList(listCategoryItemModel)
        }


        homeViewModel.progressLiveData.observe(this, EventObserver {

            binding.progressBar.visibleOrGone(it, true)
        })

    }
}