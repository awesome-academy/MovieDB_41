package phuchh.sunasterisk.moviedb_41.data.source.remote

import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.model.response.GenreResponse
import phuchh.sunasterisk.moviedb_41.data.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET("genre/movie/list")
    fun getGenres(): Call<GenreResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getPlayingMovies(): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopMovies(): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getComingMovies(): Call<MovieResponse>

    @GET("trending/movie/day")
    fun getTrendingMoviesByDay(): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: String): Call<Movie>
}
