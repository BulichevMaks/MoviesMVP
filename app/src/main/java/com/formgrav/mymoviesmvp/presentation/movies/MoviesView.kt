package com.formgrav.mymoviesmvp.presentation.movies

import com.formgrav.mymoviesmvp.domain.models.Movie
import com.formgrav.mymoviesmvp.ui.movies.models.MoviesState

interface MoviesView {
    fun render(state: MoviesState)

    fun showToast(additionalMessage: String)
}