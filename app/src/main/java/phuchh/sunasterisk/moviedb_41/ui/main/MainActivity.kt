package phuchh.sunasterisk.moviedb_41.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import phuchh.sunasterisk.moviedb_41.R
import phuchh.sunasterisk.moviedb_41.base.BaseActivity
import phuchh.sunasterisk.moviedb_41.databinding.ActivityMainBinding
import phuchh.sunasterisk.moviedb_41.ui.home.HomeFragment
import phuchh.sunasterisk.moviedb_41.utils.Navigation
import phuchh.sunasterisk.moviedb_41.utils.ViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_main
    override lateinit var viewModel: MainViewModel
    private var navigation: Int = Navigation.HOME

    override fun initComponent(viewBinding: ActivityMainBinding, savedInstanceState: Bundle?) {
        initViewModel()
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        showDefaultHome()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application))
            .get(MainViewModel::class.java)
    }

    private fun showDefaultHome() {
        val fragment = HomeFragment()
        replaceFragment(fragment, R.id.frameContainer, false, "")
    }

    private val onNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val fragment: Fragment
            when (item.itemId) {
                R.id.navigation_home -> {
                    if (navigation != Navigation.HOME) {
                        navigation = Navigation.HOME
                        fragment = HomeFragment.newInstance()
                        replaceFragment(fragment, R.id.frameContainer, false, "")
                    }
                    return true
                }
                R.id.navigation_genre -> {
                    //TODO:Update genre
                    return true
                }
                R.id.navigation_fav -> {
                    //TODO: Update fav
                    navigation = Navigation.FAVOURITE
                    return true
                }
            }
            return false
        }
    }
}
