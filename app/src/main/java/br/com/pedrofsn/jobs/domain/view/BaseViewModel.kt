package br.com.pedrofsn.jobs.domain.view

import androidx.lifecycle.ViewModel
import br.com.pedrofsn.network.feature.NetworkFeedback
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    abstract val networkFeedback: NetworkFeedback
    override val coroutineContext: CoroutineContext = Dispatchers.IO
}