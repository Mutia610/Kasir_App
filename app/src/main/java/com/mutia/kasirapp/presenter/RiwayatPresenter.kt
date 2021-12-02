package com.mutia.kasirapp.presenter

import com.mutia.kasirapp.config.NetworkModule
import com.mutia.kasirapp.model.ResponseGetPenjualan
import com.mutia.kasirapp.model.ResponseRiwayatDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPresenter(val view: RiwayatView) {

    fun getRiwayat(){
        val getData = NetworkModule.service().getRiwayat()
        getData.enqueue(object : Callback<List<ResponseGetPenjualan>>{
            override fun onResponse(
                call: Call<List<ResponseGetPenjualan>>,
                response: Response<List<ResponseGetPenjualan>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    view.onSuccess("Data Berhasil Ditampilkan", data)
                }else{
                    view.onError(response.message())
                }
            }

            override fun onFailure(call: Call<List<ResponseGetPenjualan>>, t: Throwable) {
                view.onError(t.localizedMessage)
            }

        })
    }

    fun getRiwayatDetail(id: String){
        val detail = NetworkModule.service().getDetailRiwayat(id)
        detail.enqueue(object : Callback<List<ResponseRiwayatDetail>>{
            override fun onResponse(
                call: Call<List<ResponseRiwayatDetail>>,
                response: Response<List<ResponseRiwayatDetail>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    val byk = data?.size!! - 1
                    var bayar = 0

                    for (i in 0..byk){
                        bayar += data[i].harga?.toInt()!! * data[i].qty!!.toInt()
                    }
                    view.onDetailSuccess("Data Didapatkan", data, bayar.toString())
                }else{
                    view.onError(response.message())
                }
            }

            override fun onFailure(call: Call<List<ResponseRiwayatDetail>>, t: Throwable) {
                view.onError(t.localizedMessage)
            }

        })
    }
}