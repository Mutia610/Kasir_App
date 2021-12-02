package com.mutia.kasirapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseGetPenjualan(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("harga")
    val harga: String? = null,

    @field:SerializedName("created_at")
    val created_at: String? = null,

    @field:SerializedName("created_by")
    val created_by: String? = null,

    @field:SerializedName("updated_at")
    val updated_at: String? = null,

    @field:SerializedName("updated_by")
    val updated_by: String? = null,

    @field:SerializedName("deleted_at")
    val deleted_at: String? = null,

    @field:SerializedName("deleted_by")
    val deleted_by: String? = null


) : Parcelable