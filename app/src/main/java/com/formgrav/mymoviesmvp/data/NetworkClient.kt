package com.formgrav.mymoviesmvp.data

import com.formgrav.mymoviesmvp.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}