package br.com.pedrofsn.jobs.network.infra

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class ProxyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val httpUrlBuilder = originalHttpUrl.newBuilder()
        val httpUrl = httpUrlBuilder.build()

        val requestBuilder = original
            .newBuilder()
            .url(httpUrl)

        val token = "@pedrofsn123"

        requestBuilder.addHeader("Accept", "application/json")
        requestBuilder.addHeader("osversion", "1.0.0")
        requestBuilder.addHeader("osname", "android")
        requestBuilder.addHeader("Authorization", "Bearer $token")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}