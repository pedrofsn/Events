package br.com.pedrofsn.jobs.network.infra

abstract class CustomAPIConnection<T> : AbstractAPIConnection<T>(
    baseURL = "http://192.168.100.29:9876/",
    interceptor = ProxyInterceptor()
)