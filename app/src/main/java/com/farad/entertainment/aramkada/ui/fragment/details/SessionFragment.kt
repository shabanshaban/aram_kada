package com.farad.entertainment.aramkada.ui.fragment.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.farad.entertainment.aramkada.base.BottomNavigationFragment
import com.farad.entertainment.aramkada.databinding.FragmentSessionBinding
import com.farad.entertainment.aramkada.ui.fragment.details.adapter.SessionAdapter
import com.farad.entertainment.aramkada.ui.vm.HomeViewModel
import com.farad.entertainment.aramkada.utils.EventObserver
import com.farad.entertainment.aramkada.utils.checkStateScrollL
import com.farad.entertainment.aramkada.utils.gone
import com.farad.entertainment.aramkada.utils.loadImage
import com.farad.entertainment.aramkada.utils.visible
import com.farad.entertainment.aramkada.utils.visibleOrGone
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class SessionFragment : BottomNavigationFragment<FragmentSessionBinding>() {
    private val homeViewModel by viewModel<HomeViewModel>()
    private val arg by navArgs<SessionFragmentArgs>()

    private val sessionAdapter = SessionAdapter()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSessionBinding
        get() = FragmentSessionBinding::inflate

    override fun setup() {
        listener()
        getData()
        initRecyclerview()
    }

    private fun listener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            val alpha = 1.0f - abs(verticalOffset / binding.appBarLayout.totalScrollRange.toFloat())
            binding.imageItem.alpha = alpha
        }

    }

    private fun initRecyclerview() {
        binding.recyclerviewDetail.adapter = sessionAdapter
        binding.recyclerviewDetail.checkStateScrollL {
            if (sessionAdapter.currentList.size > 10)
                getMainActivity()?.showBottomNavigation(it)
        }
    }

    private fun getData() {
        arg.item.apply {

            lifecycleScope.launch {
                homeViewModel.getSessionListFlow(arg.item.id.toLong())
                    .onStart {
                        binding.progressBar.visible (false)
                    }
                    .onCompletion {
                        binding.progressBar.gone (true)
                    }
                    .collect{
                        sessionAdapter.submitList(it)
                }
            }

            binding.imageItem.loadImage(image2)
            binding.tvTitle.text = title
        }
    }

    override fun initObserveViewModel() {
        super.initObserveViewModel()

        homeViewModel.sessionListLiveData.observe(this, EventObserver {
            sessionAdapter.submitList(it)
        })
        homeViewModel.progressLiveData.observe(this, EventObserver {
            binding.progressBar.visibleOrGone(it, true)

        })

    }
}