package phuchh.sunasterisk.moviedb_41.data.model.response

import com.google.gson.annotations.SerializedName
import phuchh.sunasterisk.moviedb_41.data.model.Genre

class GenreResponse {
    @SerializedName("genres")
    val genres: List<Genre>? = null
}
