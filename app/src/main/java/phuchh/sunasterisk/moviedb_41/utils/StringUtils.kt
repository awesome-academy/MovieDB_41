package phuchh.sunasterisk.moviedb_41.utils

import retrofit2.Response

object StringUtils {
    private const val HYPHEN = " - "
    @JvmStatic
    fun getImage(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
            .append(Constants.IMAGE_SIZE_W500)
            .append(image_path)
        return builder.toString()
    }

    @JvmStatic
    fun parseError(response: Response<*>): String {
        val builder = StringBuilder()
        builder.append(response.code()).append(HYPHEN).append(response.message())
        return builder.toString()
    }
}
