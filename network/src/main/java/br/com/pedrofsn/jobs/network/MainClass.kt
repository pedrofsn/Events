package br.com.pedrofsn.jobs.network

import br.com.pedrofsn.jobs.network.impl.Interactor
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainClass : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Unconfined

    fun main() {
        val interactor = Interactor(null)

        launch {
            val teste = interactor.receiveList()

            println("Trouxe via requisição na API: $teste")
        }
    }
}

fun main() {
    MainClass().main()
}