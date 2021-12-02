package com.mutia.kasirapp.presenter

import com.mutia.kasirapp.model.ResponseGetPenjualan
import com.mutia.kasirapp.model.ResponseGetProduk
import com.mutia.kasirapp.model.ResponseRiwayatDetail

interface RiwayatView {
    fun onSuccess(msg : String, data: List<ResponseGetPenjualan>?)
    fun onDetailSuccess(msg : String, data: List<ResponseRiwayatDetail>?, totBayar: String)
    fun onError(msg : String)
}