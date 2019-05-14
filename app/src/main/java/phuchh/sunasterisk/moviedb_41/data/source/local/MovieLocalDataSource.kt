package phuchh.sunasterisk.moviedb_41.data.source.local

import phuchh.sunasterisk.moviedb_41.data.model.Movie
import phuchh.sunasterisk.moviedb_41.data.source.MovieDataSource

class MovieLocalDataSource : MovieDataSource.Local {
    companion object {
        private var instance: MovieLocalDataSource? = null

        fun getInstance(): MovieLocalDataSource {
            if (instance == null) instance = MovieLocalDataSource()
            return instance!!
        }
    }

    override fun getAllFavorite(): List<Movie> {
        //TODO: Update local
        return ArrayList()
    }

    override fun addFavorite(movie: Movie): Boolean {
        return false
    }

    override fun deleteFavorite(movie: Movie): Boolean {
        return false
    }

    override fun isFavorite(movieId: Int): Boolean {
        return false
    }
}
