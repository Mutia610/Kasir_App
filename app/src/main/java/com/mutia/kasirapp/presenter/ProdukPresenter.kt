package com.mutia.kasirapp.presenter

import android.widget.Toast
import com.mutia.kasirapp.config.NetworkModule
import com.mutia.kasirapp.model.ResponseGetProduk
import com.mutia.kasirapp.model.ResponseProduk
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukPresenter(val view: ProdukView) {

    fun getPorduk() {
        val show = NetworkModule.service().getDataProduk()
        show.enqueue(object : Callback<List<ResponseGetProduk>> {
            override fun onResponse(
                call: Call<List<ResponseGetProduk>>,
                response: Response<List<ResponseGetProduk>>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()
                    view.onSuccess("Berhasil Get Data Produk", item)
                } else {
                    view.onError("Gagal Get Data Produk")
                }
            }

            override fun onFailure(call: Call<List<ResponseGetProduk>>, t: Throwable) {
                view.onError(t.localizedMessage)
            }

        })
    }
}
