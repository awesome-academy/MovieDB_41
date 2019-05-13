package phuchh.sunasterisk.moviedb_41.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import phuchh.sunasterisk.moviedb_41.R
import phuchh.sunasterisk.moviedb_41.base.BaseFragment
import phuchh.sunasterisk.moviedb_41.databinding.FragmentHomeBinding
import phuchh.sunasterisk.moviedb_41.utils.ViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override lateinit var viewModel: HomeViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_home
    override fun initComponent(viewBinding: FragmentHomeBinding, savedInstanceState: Bundle?) {
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application))
            .get(HomeViewModel::class.java)
    }
}
