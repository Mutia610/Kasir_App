package com.mutia.kasirapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponsePreview (
    val id: Int? = null,
    val nama: String? = null,
    val harga: String? = null,
    val jumlah: String? = null,
    val totalHarga: String? = null
):Parcelable