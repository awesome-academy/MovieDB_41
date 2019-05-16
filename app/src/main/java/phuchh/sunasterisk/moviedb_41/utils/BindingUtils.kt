package phuchh.sunasterisk.moviedb_41.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import phuchh.sunasterisk.moviedb_41.R

object BindingUtils {
    @JvmStatic
    @BindingAdapter("bindImage")
    fun bindImage(imageView: ImageView, image_path: String) {
        GlideApp.with(imageView)
            .load(StringUtils.getImage(image_path))
            .error(R.drawable.no_image)
            .into(imageView)
    }
}
