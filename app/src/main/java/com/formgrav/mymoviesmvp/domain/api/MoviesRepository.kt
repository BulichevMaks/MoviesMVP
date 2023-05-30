package com.formgrav.mymoviesmvp.domain.api

import com.formgrav.mymoviesmvp.domain.models.Movie
import com.formgrav.mymoviesmvp.util.Resource


interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}