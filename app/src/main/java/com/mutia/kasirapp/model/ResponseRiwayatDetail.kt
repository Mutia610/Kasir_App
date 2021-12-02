package com.mutia.kasirapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseRiwayatDetail(
    val id: Int? = null,
    val nama: String? = null,
    val harga: String? = null,
    val qty: Int? = null
) : Parcelable