package br.com.pedrofsn.network.payload

import br.com.pedrofsn.network.domain.Payload
import br.com.pedrofsn.network.model.ErrorAPI
import br.com.pedrofsn.network.model.ErrorHandled
import extract
import java.io.Serializable

open class PayloadError(
    val erro: Boolean? = false,
    val msg: String? = "",
    private val msg_dev: String? = "",
    val acao: Int? = -1,
    val id: String? = ""
) : Payload<ErrorAPI>, Serializable {
    override fun toModel() = ErrorAPI( // todo precisa deste model?
        erro = extract safe erro,
        msg = extract safe msg,
        msg_dev = extract safe msg_dev,
        action = extract safe acao,
        id = extract safe id
    )

    fun toModel(networkError: Int) = ErrorHandled(
        message = extract safe msg,
        actionAPI = extract safe acao,
        statusCode = networkError,
        id = extract safe id
    )
}