package br.com.pedrofsn.jobs.domain.network

abstract class CustomAPIConnection<T> : AbstractAPIConnection<T>(
    baseURL = "http://192.168.100.29:9876/",
    showLogs = true,
    interceptor = ProxyInterceptor()
)