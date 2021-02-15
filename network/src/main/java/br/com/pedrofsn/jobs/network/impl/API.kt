package br.com.pedrofsn.jobs.network.impl

import br.com.pedrofsn.jobs.network.data.payload.ResponseList
import retrofit2.http.GET

interface API {

    @GET("anuncios")
    suspend fun receiveList(): ResponseList
}