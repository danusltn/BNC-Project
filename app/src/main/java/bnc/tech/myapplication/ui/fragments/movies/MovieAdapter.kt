package bnc.tech.myapplication.ui.fragments.movies

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import bnc.tech.myapplication.network.dto.Models
import timber.log.Timber

typealias ClickListener = (Models.MovieResponse) -> Unit

class PhotoAdapter(private val clickListener: ClickListener) :
    PagedListAdapter<Models.MovieResponse, MovieViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Timber.d("Binding view holder at position $position")
        val photo = getItem(position)

        with(holder) {
            bind(photo)
            photo?.let {
                itemView.setOnClickListener {
                    clickListener(photo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder.from(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Models.MovieResponse>() {
            override fun areItemsTheSame(oldItem: Models.MovieResponse, newItem: Models.MovieResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Models.MovieResponse, newItem: Models.MovieResponse): Boolean =
                oldItem == newItem
        }
    }
}