package bnc.tech.myapplication.ui.fragments.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import bnc.tech.myapplication.R
import bnc.tech.myapplication.network.dto.Models
import kotlinx.android.synthetic.main.item_photo.view.*

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bind(movie: Models.MovieResponse?) {
            movie?.apply {
                val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
                itemView.apply {
                    Glide.with(imgPreview.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(imgPreview)
                    txtUserName.text = title
                }
            }
    }

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.item_photo, parent, false)

            return MovieViewHolder(view)
        }
    }

}