package phuchh.sunasterisk.moviedb_41.data.source.remote

import android.content.Context
import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.model.response.GenreResponse
import phuchh.sunasterisk.moviedb_41.data.model.response.MovieResponse
import phuchh.sunasterisk.moviedb_41.data.source.MovieDataSource
import retrofit2.Call

class MovieRemoteDataSource private constructor(private val apiRequest: ApiRequest) : MovieDataSource.Remote {
    companion object {
        private var movieRemoteDataSource: MovieRemoteDataSource? = null

        fun getInstance(context: Context): MovieRemoteDataSource {
            if (movieRemoteDataSource == null) {
                movieRemoteDataSource = MovieRemoteDataSource(NetworkService.getInstance(context))
            }
            return movieRemoteDataSource!!
        }
    }

    override fun getGenres(): Call<GenreResponse> {
        return apiRequest.getGenres()
    }

    override fun getPopularMovies(): Call<MovieResponse> {
        return apiRequest.getPopularMovies()
    }

    override fun getComingMovies(): Call<MovieResponse> {
        return apiRequest.getComingMovies()
    }

    override fun getPlayingMovies(): Call<MovieResponse> {
        return apiRequest.getComingMovies()
    }

    override fun getTopMovies(): Call<MovieResponse> {
        return apiRequest.getTopMovies()
    }

    override fun getMoviesTrendingByDay(): Call<MovieResponse> {
        return apiRequest.getTrendingMoviesByDay()
    }

    override fun getMovieDetails(movieId: Int): Call<Movie> {
        return apiRequest.getMovieDetails(movieId.toString())
    }
}
