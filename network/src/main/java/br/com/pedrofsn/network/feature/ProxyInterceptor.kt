package br.com.pedrofsn.network.feature

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

private const val HEADER_ACCEPT = "Accept"
private const val HEADER_OS_VERSION_NAME = "version_name"
private const val HEADER_OS_NAME = "os_name"

private const val HEADER_ACCEPT_VALUE = "application/json"
private const val HEADER_OS_NAME_VALUE = "ANDROID"

class ProxyInterceptor(val osVersionName: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val httpUrlBuilder = originalHttpUrl.newBuilder()
        val httpUrl = httpUrlBuilder.build()

        val requestBuilder = original
            .newBuilder()
            .url(httpUrl)

        requestBuilder.addHeader(HEADER_ACCEPT, HEADER_ACCEPT_VALUE)
        requestBuilder.addHeader(HEADER_OS_VERSION_NAME, osVersionName)
        requestBuilder.addHeader(HEADER_OS_NAME, HEADER_OS_NAME_VALUE)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}