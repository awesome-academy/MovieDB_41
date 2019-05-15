package phuchh.sunasterisk.moviedb_41.utils

import android.content.Context
import phuchh.sunasterisk.moviedb_41.data.repository.MovieRepository
import phuchh.sunasterisk.moviedb_41.data.source.local.MovieLocalDataSource
import phuchh.sunasterisk.moviedb_41.data.source.remote.MovieRemoteDataSource

object Injection {
    fun provideMovieRepository(context: Context): MovieRepository {
        return MovieRepository.getInstance(MovieRemoteDataSource.getInstance(context), MovieLocalDataSource())
    }
}
