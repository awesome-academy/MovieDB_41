package phuchh.sunasterisk.moviedb_41.utils

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import phuchh.sunasterisk.moviedb_41.ui.home.HomeViewModel
import phuchh.sunasterisk.moviedb_41.ui.main.MainViewModel

class ViewModelFactory private constructor(
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideMovieRepository(application.applicationContext)
                )
                    .also { INSTANCE = it }
            }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel()
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(movieRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}
