package br.com.ufg.www.events.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JobType(
        val id: Long,
        val description: String
) : Parcelable