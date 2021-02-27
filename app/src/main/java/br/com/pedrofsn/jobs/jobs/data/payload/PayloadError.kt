package br.com.pedrofsn.jobs.jobs.data.payload

import br.com.pedrofsn.jobs.jobs.data.model.ErrorAPI
import extract
import java.io.Serializable

open class PayloadError(
    val erro: Boolean? = false,
    val msg: String? = "",
    private val msg_dev: String? = "",
    val acao: Int? = -1,
    val id: String? = ""
) : Serializable {
    fun toModel() = ErrorAPI(
        erro = extract safe erro,
        msg = extract safe msg,
        msg_dev = extract safe msg_dev,
        action = extract safe acao,
        id = extract safe id
    )
}