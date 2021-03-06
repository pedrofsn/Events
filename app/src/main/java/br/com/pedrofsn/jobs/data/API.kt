package br.com.pedrofsn.jobs.data

import br.com.pedrofsn.jobs.data.payload.ResponseList
import retrofit2.http.GET

interface API {

    @GET("anuncios")
    suspend fun receiveList(): ResponseList?
}