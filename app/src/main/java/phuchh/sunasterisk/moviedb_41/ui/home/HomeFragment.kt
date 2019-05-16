package phuchh.sunasterisk.moviedb_41.ui.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import phuchh.sunasterisk.moviedb_41.R
import phuchh.sunasterisk.moviedb_41.adapter.AdapterCallback
import phuchh.sunasterisk.moviedb_41.adapter.DataRecyclerAdapter
import phuchh.sunasterisk.moviedb_41.adapter.SliderAdapter
import phuchh.sunasterisk.moviedb_41.base.BaseFragment
import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.model.response.ApiResponse
import phuchh.sunasterisk.moviedb_41.databinding.FragmentHomeBinding
import phuchh.sunasterisk.moviedb_41.utils.ViewModelFactory
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object {
        private const val DELAY: Long = 4000
        private const val PERIOD: Long = 6000
        private const val SIZE = 7

        fun newInstance() = HomeFragment()
    }

    private lateinit var popularAdapter: DataRecyclerAdapter<Movie>
    private lateinit var playingAdapter: DataRecyclerAdapter<Movie>
    private lateinit var topAdapter: DataRecyclerAdapter<Movie>
    private lateinit var comingAdapter: DataRecyclerAdapter<Movie>
    private lateinit var pagerLatest: ViewPager
    private lateinit var indicator: TabLayout
    private lateinit var latestMovies: List<Movie>
    private lateinit var sliderAdapter: SliderAdapter
    override lateinit var viewModel: HomeViewModel
    private val layoutRes = R.layout.item_movie

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun initComponent(viewBinding: FragmentHomeBinding, savedInstanceState: Bundle?) {
        initViewModel()
        initAdapter(viewBinding)
        pagerLatest = viewBinding.pagerLatest
        indicator = viewBinding.indicator
        sliderAdapter = SliderAdapter(context!!)
        observeViewModel()
    }

    private fun initAdapter(viewBinding: FragmentHomeBinding) {
        popularAdapter = DataRecyclerAdapter(movieClickCallback, layoutRes)
        playingAdapter = DataRecyclerAdapter(movieClickCallback, layoutRes)
        topAdapter = DataRecyclerAdapter(movieClickCallback, layoutRes)
        comingAdapter = DataRecyclerAdapter(movieClickCallback, layoutRes)

        viewBinding.recyclerPopularMovies.adapter = popularAdapter
        viewBinding.recyclerComingMovies.adapter = comingAdapter
        viewBinding.recyclerPlayingMovies.adapter = playingAdapter
        viewBinding.recyclerTopMovies.adapter = topAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application))
            .get(HomeViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.run {
            popularMovies.observe(viewLifecycleOwner, Observer {
                updateUI(it, popularAdapter)
            })
            playingMovies.observe(viewLifecycleOwner, Observer {
                updateUI(it, playingAdapter)
            })
            topMovies.observe(viewLifecycleOwner, Observer {
                updateUI(it, topAdapter)
            })
            upComingMovies.observe(viewLifecycleOwner, Observer {
                updateUI(it, comingAdapter)
            })
            latestMovies.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val error: Throwable? = it.error
                    val movies: List<Movie>? = it.result
                    if (error != null) {
                        showToast(error.message!!)
                        return@Observer
                    }
                    this@HomeFragment.latestMovies = movies!!.subList(0, SIZE)
                    sliderAdapter.setMovies(this@HomeFragment.latestMovies)
                    initSlider()
                }
            })
        }
    }

    private fun updateUI(response: ApiResponse<List<Movie>>?, adapter: DataRecyclerAdapter<Movie>) {
        if (response != null) {
            val error: Throwable? = response.error
            val movies: List<Movie>? = response.result
            if (error != null) {
                showToast(error.message!!)
                return
            }
            adapter.setData(movies!!)
        }
    }

    private val movieClickCallback = object : AdapterCallback {
        override fun onItemClick(id: Int) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            }
        }
    }

    private fun initSlider() {
        pagerLatest.adapter = sliderAdapter
        indicator.setupWithViewPager(pagerLatest)
        Timer().scheduleAtFixedRate(SliderTimer(), DELAY, PERIOD)
    }

    inner class SliderTimer : TimerTask() {
        override fun run() {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                activity!!.runOnUiThread {
                    if (pagerLatest.currentItem < latestMovies.size - 1) {
                        pagerLatest.currentItem++
                        return@runOnUiThread
                    }
                    pagerLatest.currentItem = 0
                }
            }
        }
    }
}
