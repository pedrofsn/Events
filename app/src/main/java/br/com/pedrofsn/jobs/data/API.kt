package br.com.pedrofsn.jobs.data

import br.com.pedrofsn.jobs.data.payload.ResponseList
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("anuncios")
    suspend fun receiveList(@Query("page") page: Int): ResponseList?
}