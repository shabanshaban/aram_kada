package com.farad.entertainment.aramkada.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.farad.entertainment.aramkada.app.BaseApp
import com.farad.entertainment.aramkada.base.navigation.base.BaseNavigator
import com.farad.entertainment.aramkada.data.maneger.navigate.NavigationManager
import com.farad.entertainment.aramkada.utils.hideKeyboard


abstract class BaseFragment<VB : ViewBinding> : Fragment(), FragmentCreateView,
    FragmentNavigate {

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _binding: VB? = null

    protected val binding: VB
        get() {
            return _binding!!
        }




    private fun getNavigator() = activity as? BaseNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        beforeCreateView()

        return if (_binding != null) _binding!!.root else null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBeforeSetup()
        initObserveViewModel()


            setup()
    }


    override fun initBeforeSetup() {
        //nothing
    }

    abstract fun setup()

    override fun beforeCreateView() {
    }


    override fun initObserveViewModel() {

    }

    override fun navigate(
        navDirections: NavDirections,
        pageAnimation: NavigationManager.PageAnimation
    ) {
        view?.hideKeyboard()
        getNavigator()?.navigate(navDirections, pageAnimation)
    }


    override fun navigate(id: Int, pageAnimation: NavigationManager.PageAnimation) {
        view?.hideKeyboard()
        getNavigator()?.navigate(id, pageAnimation)
    }

    override fun navigate(id: Int, bundle: Bundle, pageAnimation: NavigationManager.PageAnimation) {
        view?.hideKeyboard()
        getNavigator()?.navigate(id, bundle, pageAnimation)
    }

    fun popBackStack() {
        view?.hideKeyboard()
        runCatching {
            findNavController().popBackStack()
        }
    }


    fun getBaseActivity(): BaseActivity<*>? {
        return activity as? BaseActivity<*>
    }


    fun getCoreApp(): BaseApp {
        return getBaseActivity()?.application as BaseApp
    }


    override fun onDestroyView() {
        try {

            if (isNullView().not()) {
                super.onDestroyView()
                _binding = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isNullView(): Boolean = _binding == null

}


interface FragmentCreateView {
    fun beforeCreateView()
    fun initObserveViewModel()
    fun initBeforeSetup()
}

interface FragmentNavigate {
    fun navigate(
        navDirections: NavDirections,
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.DEFAULT
    )

    fun navigate(
        @IdRes id: Int,
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.DEFAULT
    )

    fun navigate(
        @IdRes id: Int,
        bundle: Bundle,
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.DEFAULT
    )

}
