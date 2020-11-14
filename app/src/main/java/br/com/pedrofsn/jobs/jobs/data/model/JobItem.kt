package br.com.pedrofsn.jobs.jobs.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JobItem(
    val id: Long,
    val title: String,
    val place: String,
    val date: String,
    val description: String
) : Parcelable