package com.mutia.kasirapp.presenter

import com.mutia.kasirapp.model.ResponseGetProduk

interface ProdukView {
    fun onSuccess(msg : String,produk : List<ResponseGetProduk>?)
    fun onError(msg : String)
}