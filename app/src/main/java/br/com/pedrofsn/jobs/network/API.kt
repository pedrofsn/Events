package br.com.pedrofsn.jobs.network

import br.com.pedrofsn.jobs.jobs.data.payload.ResponseList
import retrofit2.http.GET

interface API {

    @GET("anuncios")
    suspend fun receiveList(): ResponseList
}