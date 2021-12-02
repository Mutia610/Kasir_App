package com.mutia.kasirapp.presenter

import com.mutia.kasirapp.model.ResponseGetProduk

interface PreviewView {
    fun onSuccess(msg : String,idProduk: String, jumlah: String)
    fun onError(msg : String)
}