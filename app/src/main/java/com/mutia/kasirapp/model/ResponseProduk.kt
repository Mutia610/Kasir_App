package com.mutia.kasirapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseProduk(

	val code: Int? = null,
	val msg: String? = null
) : Parcelable


