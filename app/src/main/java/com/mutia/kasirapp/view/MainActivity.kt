package com.mutia.kasirapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mutia.kasirapp.R
import com.mutia.kasirapp.adapter.ProdukAdapter
import com.mutia.kasirapp.config.NetworkModule
import com.mutia.kasirapp.model.ResponseGetProduk
import com.mutia.kasirapp.model.ResponseProduk
import com.mutia.kasirapp.presenter.ProdukPresenter
import com.mutia.kasirapp.presenter.ProdukView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ProdukView{

    private var presenter : ProdukPresenter? = null
    var dataArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ProdukPresenter(this)
        presenter!!.getPorduk()

        riwayat.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }
    }

    override fun onSuccess(msg: String, produk: List<ResponseGetProduk>?) {
        val produkAdapter = ProdukAdapter(produk, object : ProdukAdapter.OnClickListener {
            override fun jumlah(jml: String, data: ArrayList<String>) {
                totalHarga.text = jml.toInt().convertRupiah()

                btnTotal.setOnClickListener {
                    if (jml.toInt() > 0) {
                        startActivity(Intent(this@MainActivity, PreviewActivity::class.java)
                            .putExtra("DATA", data))
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Isi Jumlah Beli Produk",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        })
        rvProduk.adapter = produkAdapter
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        Log.d("TAG", "onError: $msg")
    }


    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
}