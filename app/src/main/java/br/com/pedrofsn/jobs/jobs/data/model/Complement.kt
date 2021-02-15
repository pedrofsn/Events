package br.com.pedrofsn.jobs.jobs.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Complement(
    val id: Long,
    val text: String
) : Parcelable