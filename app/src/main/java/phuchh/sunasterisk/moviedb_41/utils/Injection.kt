package phuchh.sunasterisk.moviedb_41.utils

import android.content.Context

object Injection {
    fun provideMovieRepository(context: Context): MovieRepository {
        return MovieRepository.getInstance(MovieRemoteDataSource.getInstance(context), MovieLocalDataSource())
    }
}
