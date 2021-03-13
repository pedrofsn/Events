package br.com.pedrofsn.jobs.domain.network

import br.com.pedrofsn.jobs.BuildConfig
import br.com.pedrofsn.jobs.data.API
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val TIMEOUT_CONNECT_IN_MINUTES: Long = 1
private const val TIMEOUT_READ_IN_MINUTES: Long = 1

object APIConnection {

    private val moshi by lazy { MoshiConverterFactory.create() }

    val service: API by lazy {
        val okHttppBuilder = OkHttpClient().newBuilder()
        okHttppBuilder.connectTimeout(TIMEOUT_CONNECT_IN_MINUTES, TimeUnit.MINUTES)
        okHttppBuilder.readTimeout(TIMEOUT_READ_IN_MINUTES, TimeUnit.MINUTES)
        okHttppBuilder.addInterceptor(ProxyInterceptor())

        val logging = HttpLoggingInterceptor()
        logging.level = when {
            // TODO true tá harcoded
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        okHttppBuilder.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .addConverterFactory(moshi)
            .client(okHttppBuilder.build())
            .build()

        return@lazy retrofit.create<API>()
    }
}