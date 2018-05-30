package br.com.ufg.www.events.extensions

import java.security.MessageDigest


fun String.toUpperCaseAndApplySHA256(): String {
    try {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(this.toUpperCase().toByteArray(Charsets.UTF_8))
        val stringProcessed = StringBuffer()

        for (i in hash.indices) {
            val hex = Integer.toHexString(0xff and hash[i].toInt())
            if (hex.length == 1) stringProcessed.append('0')
            stringProcessed.append(hex)
        }

        return stringProcessed.toString()
    } catch (ex: Exception) {
        throw RuntimeException(ex)
    }

}