package com.mutia.kasirapp.config


import com.mutia.kasirapp.model.ResponseGetPenjualan
import com.mutia.kasirapp.model.ResponseGetProduk
import com.mutia.kasirapp.model.ResponseProduk
import com.mutia.kasirapp.model.ResponseRiwayatDetail
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/produk")
    fun getDataProduk():Call<List<ResponseGetProduk>>

    @Headers("x-key: bd0b3ae6651538fac2515baafc9326c5")
    @FormUrlEncoded
    @POST("api/penjualan/save")
    fun insertPembelian(@Field("id_produk[]")id_produk:String,
                        @Field("qty[]")jumlah:String): Call<ResponseProduk>

    @Headers("x-key: bd0b3ae6651538fac2515baafc9326c5")
    @GET("api/penjualan")
    fun getRiwayat():Call<List<ResponseGetPenjualan>>

    @Headers("x-key: bd0b3ae6651538fac2515baafc9326c5")
    @GET("api/penjualan/detail")
    fun getDetailRiwayat(@Query("id")id:String):Call<List<ResponseRiwayatDetail>>
}

