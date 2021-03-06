package phuchh.sunasterisk.moviedb_41.data.source

import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.model.response.GenreResponse
import phuchh.sunasterisk.moviedb_41.data.model.response.MovieResponse
import retrofit2.Call

interface MovieDataSource {
    interface Local {
        fun getAllFavorite(): List<Movie>

        fun addFavorite(movie: Movie): Boolean

        fun deleteFavorite(movie: Movie): Boolean

        fun isFavorite(movieId: Int): Boolean
    }

    interface Remote {
        fun getGenres(): Call<GenreResponse>

        fun getPopularMovies(): Call<MovieResponse>

        fun getPlayingMovies(): Call<MovieResponse>

        fun getTopMovies(): Call<MovieResponse>

        fun getComingMovies(): Call<MovieResponse>

        fun getMoviesTrendingByDay(): Call<MovieResponse>

        fun getMovieDetails(movieId: Int): Call<Movie>
    }
}
