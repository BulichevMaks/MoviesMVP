package com.formgrav.mymoviesmvp.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.formgrav.mymoviesmvp.R
import com.formgrav.mymoviesmvp.domain.api.MoviesInteractor
import com.formgrav.mymoviesmvp.domain.models.Movie
import com.formgrav.mymoviesmvp.ui.movies.models.MoviesState
import com.formgrav.mymoviesmvp.util.Creator


class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.render(
                MoviesState.Loading
            )
            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }
                        when {
                            errorMessage != null -> {
                                view.render(
                                    MoviesState.Error(
                                        errorMessage = context.getString(R.string.something_went_wrong),
                                    )
                                )
                                view.showToast(errorMessage)
                            }
                            movies.isEmpty() -> {
                                view.render(
                                    MoviesState.Empty(
                                        message = context.getString(R.string.nothing_found),
                                    )
                                )
                            }
                            else -> {
                                view.render(
                                    MoviesState.Content(
                                        movies = movies,
                                    )
                                )
                            }
                        }
                    }
                }
            })
        }
    }
}