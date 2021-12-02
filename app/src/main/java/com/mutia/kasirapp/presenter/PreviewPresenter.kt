package com.mutia.kasirapp.presenter

import com.mutia.kasirapp.config.NetworkModule
import com.mutia.kasirapp.model.ResponseProduk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviewPresenter(val view: PreviewView) {

    fun insertPembelian(idProduk: String, jumlah: String) {
        val save = NetworkModule.service().insertPembelian(idProduk, jumlah)
        save.enqueue(object : Callback<ResponseProduk> {
            override fun onResponse(
                call: Call<ResponseProduk>,
                response: Response<ResponseProduk>
            ) {
                if (response.isSuccessful) {
                    view.onSuccess("Data Berhasil Disimpan", idProduk, jumlah)
                } else {
                    view.onError(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseProduk>, t: Throwable) {
                view.onError(t.localizedMessage)
            }
        })
    }
}