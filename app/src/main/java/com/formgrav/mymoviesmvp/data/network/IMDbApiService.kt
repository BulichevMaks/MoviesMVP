package com.formgrav.mymoviesmvp.data.network

import com.formgrav.mymoviesmvp.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_bed5nx24/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}