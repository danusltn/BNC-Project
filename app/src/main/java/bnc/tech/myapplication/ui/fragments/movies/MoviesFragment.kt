package bnc.tech.myapplication.ui.fragments.movies

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import bnc.tech.myapplication.R
import bnc.tech.myapplication.network.dto.Models
import bnc.tech.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo_list.*
import timber.log.Timber

class MoviesFragment : BaseFragment<PagedList<Models.MovieResponse>, MoviesViewModel>(),
    SearchView.OnQueryTextListener {

    override fun handleState(state: PagedList<Models.MovieResponse>) {
        render(state)
    }

    private val clickListener: ClickListener = this::onPhotoClicked

    private fun onPhotoClicked(movie: Models.MovieResponse) {
        view?.let {
            findNavController(it).navigate(
               MoviesFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(
                    movie.imageUrl,
                    movie.title,
                    ""
                )
            )
        }
    }

    private val photoListAdapter = PhotoAdapter(clickListener)


    override fun getLayout(): Int {
        return R.layout.fragment_photo_list
    }

    override fun onCreateCompleted() {
        initRecyclerView()
        createViewModel(MoviesViewModel::class.java)
        //set default value for searchView
        viewModel.setFilter(getString(R.string.search_filter_default_value))

    }


    private fun render(pagedMovieList: PagedList<Models.MovieResponse>) {
        photoListAdapter.submitList(pagedMovieList)
        Timber.d("pagedPhotoList : %s", pagedMovieList)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            queryHint = getString(R.string.search_view_hint)
            setQuery(
                if (viewModel.cachedFilter.isEmpty()) getString(R.string.search_filter_default_value) else viewModel.cachedFilter,
                true
            )
            isSubmitButtonEnabled = true
        }.setOnQueryTextListener(this)
        menu.findItem(R.id.logout).setOnMenuItemClickListener {
            Timber.d("test logout ")
            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show();
            true }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return view?.findNavController()?.let {
            NavigationUI.onNavDestinationSelected(item, it) || super.onOptionsItemSelected(item)
        } ?: false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { newText ->
            Timber.d("query : %s", newText)
            if (newText.trim().replace(" ", "").length >= 3 || newText.isEmpty()) {
                viewModel.cachedFilter = newText
                viewModel.setFilter(newText)
                viewModel.createLiveData()
                startObserving()

            }
        }
        return true
    }


}