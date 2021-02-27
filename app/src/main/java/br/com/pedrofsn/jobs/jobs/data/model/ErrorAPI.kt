package br.com.pedrofsn.jobs.jobs.data.model

import java.io.Serializable


/**
 * Created by pedrofsn on 22/02/18.
 */

class ErrorAPI(
    private val erro: Boolean = false,
    val msg: String = "",
    private val msg_dev: String = "",
    val action: Int = -1,
    val id: String = ""
) : Serializable {

    fun isOk() = isError().not()

    fun isError(): Boolean {
        println(msg_dev)
        return erro
    }
}