package com.formgrav.mymoviesmvp.data

import com.formgrav.mymoviesmvp.data.dto.MoviesSearchRequest
import com.formgrav.mymoviesmvp.data.dto.MoviesSearchResponse
import com.formgrav.mymoviesmvp.domain.api.MoviesRepository
import com.formgrav.mymoviesmvp.domain.models.Movie
import com.formgrav.mymoviesmvp.util.Resource


class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                if ((response as MoviesSearchResponse).results.isNotEmpty()) {
                    return Resource.Success((response).results.map {
                        Movie(it.id, it.resultType, it.image, it.title, it.description)
                    })
                } else {
                    return Resource.Error("Ни чего не найдено")
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}