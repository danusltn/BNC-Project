package bnc.tech.myapplication.ui.fragments.movieDetail

import androidx.core.net.toUri
import androidx.paging.PagedList
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import bnc.tech.myapplication.R
import bnc.tech.myapplication.domain.entity.MovieModel
import bnc.tech.myapplication.network.dto.Models
import bnc.tech.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo.*
import timber.log.Timber

class MovieDetailFragment : BaseFragment<PagedList<Models.MovieDetailResponse>, MovieDetailViewModel>() {

    override fun getLayout(): Int = R.layout.fragment_photo


    override fun onCreateCompleted() {
        setHasOptionsMenu(true)
        createViewModel(MovieDetailViewModel::class.java)

        viewModel.cachedFilter = id.toString()
    }

    override fun onResume() {
        super.onResume()
        bindBundle()
    }

    private fun bindBundle() {
        arguments?.apply {
            MovieDetailFragmentArgs.fromBundle(this).apply {
                val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
                imgLargePhoto.setImageURI(imgUri)
                context?.let {
                    Glide.with(it)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(imgLargePhoto)
                }

                txtTags.text = tags
                txtUserName.text = userName

            }
        }
    }

    override fun handleState(state: PagedList<Models.MovieDetailResponse>) {
        val imgUri = state
    }

}