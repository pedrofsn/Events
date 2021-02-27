package br.com.pedrofsn.jobs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Complement(
    val id: Long,
    val text: String
) : Parcelable