package phuchh.sunasterisk.moviedb_41.data.model.response

import com.google.gson.annotations.SerializedName
import phuchh.sunasterisk.moviedb_41.data.model.Cast
import phuchh.sunasterisk.moviedb_41.data.model.Crew

class CreditResponse {
    @SerializedName("id")
    val movieId: Int = 0

    @SerializedName("cast")
    val cast: List<Cast>? = null

    @SerializedName("crew")
    val crew: List<Crew>? = null
}
