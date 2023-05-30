package com.formgrav.mymoviesmvp.util

import android.app.Activity
import android.content.Context
import com.formgrav.mymoviesmvp.data.MoviesRepositoryImpl
import com.formgrav.mymoviesmvp.data.network.RetrofitNetworkClient
import com.formgrav.mymoviesmvp.domain.api.MoviesInteractor
import com.formgrav.mymoviesmvp.domain.api.MoviesRepository
import com.formgrav.mymoviesmvp.domain.impl.MoviesInteractorImpl
import com.formgrav.mymoviesmvp.presentation.MoviesSearchController
import com.formgrav.mymoviesmvp.presentation.PosterController
import com.formgrav.mymoviesmvp.ui.movies.MoviesAdapter


object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}