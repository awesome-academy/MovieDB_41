package phuchh.sunasterisk.moviedb_41.data.model.response

import com.google.gson.annotations.SerializedName
import phuchh.sunasterisk.moviedb_41.data.model.Movie

class MovieResponse {
    @SerializedName("page")
    val page: Int = 0

    @SerializedName("total_results")
    val totalResults: Int = 0

    @SerializedName("total_pages")
    val totalPage: Int = 0

    @SerializedName("results")
    val results: List<Movie>? = null
}
