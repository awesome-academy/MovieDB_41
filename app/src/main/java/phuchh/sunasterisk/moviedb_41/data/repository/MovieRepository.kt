package phuchh.sunasterisk.moviedb_41.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import phuchh.sunasterisk.moviedb_41.data.model.Genre
import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.model.response.ApiResponse
import phuchh.sunasterisk.moviedb_41.data.model.response.GenreResponse
import phuchh.sunasterisk.moviedb_41.data.model.response.MovieResponse
import phuchh.sunasterisk.moviedb_41.data.source.local.MovieLocalDataSource
import phuchh.sunasterisk.moviedb_41.data.source.remote.MovieRemoteDataSource
import phuchh.sunasterisk.moviedb_41.utils.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository private constructor(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) {

    companion object {
        private var instance: MovieRepository? = null
        fun getInstance(
            remote: MovieRemoteDataSource,
            local: MovieLocalDataSource
        ): MovieRepository {
            if (instance == null) {
                instance =
                    MovieRepository(local, remote)
            }
            return instance!!
        }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<Movie>>> = fetchMovies(remote.getPopularMovies())

    fun getPlayingMovies(): LiveData<ApiResponse<List<Movie>>> = fetchMovies(remote.getPlayingMovies())

    fun getTopMovies(): LiveData<ApiResponse<List<Movie>>> = fetchMovies(remote.getTopMovies())

    fun getUpComingMovies(): LiveData<ApiResponse<List<Movie>>> = fetchMovies(remote.getComingMovies())

    fun getMoviesTrendingByDay(): LiveData<ApiResponse<List<Movie>>> = fetchMovies(remote.getMoviesTrendingByDay())

    fun getMovieDetails(id: Int): LiveData<ApiResponse<Movie>> {
        val data = MutableLiveData<ApiResponse<Movie>>()
        remote.getMovieDetails(id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (!response.isSuccessful) {
                    val error = Throwable(StringUtils.parseError(response))
                    data.postValue(ApiResponse(error))
                    return
                }
                data.postValue(ApiResponse(response.body()))
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                data.postValue(ApiResponse(t))
            }
        })
        return data
    }

    fun getGenres(): LiveData<ApiResponse<List<Genre>>> {
        val data = MutableLiveData<ApiResponse<List<Genre>>>()
        remote.getGenres().enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (!response.isSuccessful) {
                    val error = Throwable(StringUtils.parseError(response))
                    data.postValue(ApiResponse(error))
                    return
                }
                data.postValue(ApiResponse(response.body()!!.genres))
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                data.postValue(ApiResponse(t))
            }
        })
        return data
    }

    private fun fetchMovies(call: Call<MovieResponse>): LiveData<ApiResponse<List<Movie>>> {
        val data = MutableLiveData<ApiResponse<List<Movie>>>()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (!response.isSuccessful) {
                    val error = Throwable(StringUtils.parseError(response))
                    data.postValue(ApiResponse(error))
                    return
                }
                data.postValue(ApiResponse(response.body()!!.results))
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                data.postValue(ApiResponse(t))
            }
        })
        return data
    }
}
