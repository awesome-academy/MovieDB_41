package phuchh.sunasterisk.moviedb_41.ui.home

import android.arch.lifecycle.LiveData
import phuchh.sunasterisk.moviedb_41.base.BaseViewModel
import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.model.response.ApiResponse
import phuchh.sunasterisk.moviedb_41.data.repository.MovieRepository

class HomeViewModel(repository: MovieRepository) : BaseViewModel() {
    val popularMovies: LiveData<ApiResponse<List<Movie>>> = repository.getPopularMovies()
    val playingMovies: LiveData<ApiResponse<List<Movie>>> = repository.getPlayingMovies()
    val topMovies: LiveData<ApiResponse<List<Movie>>> = repository.getTopMovies()
    val upComingMovies: LiveData<ApiResponse<List<Movie>>> = repository.getUpComingMovies()
    val latestMovies: LiveData<ApiResponse<List<Movie>>> = repository.getMoviesTrendingByDay()
}
