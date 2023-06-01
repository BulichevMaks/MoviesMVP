package com.formgrav.mymoviesmvp.util

import android.content.Context
import com.formgrav.mymoviesmvp.data.MoviesRepositoryImpl
import com.formgrav.mymoviesmvp.data.network.RetrofitNetworkClient
import com.formgrav.mymoviesmvp.domain.api.MoviesInteractor
import com.formgrav.mymoviesmvp.domain.api.MoviesRepository
import com.formgrav.mymoviesmvp.domain.impl.MoviesInteractorImpl
import com.formgrav.mymoviesmvp.presentation.movies.MoviesSearchPresenter
import com.formgrav.mymoviesmvp.presentation.poster.PosterPresenter
import com.formgrav.mymoviesmvp.presentation.movies.MoviesView
import com.formgrav.mymoviesmvp.presentation.poster.PosterView


object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(view = moviesView, context = context)
    }

    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(view = posterView, imageUrl = imageUrl)
    }
}