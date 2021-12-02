package com.mutia.kasirapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mutia.kasirapp.R
import com.mutia.kasirapp.adapter.RiwayatDetailAdapter
import com.mutia.kasirapp.model.ResponseGetPenjualan
import com.mutia.kasirapp.model.ResponseRiwayatDetail
import com.mutia.kasirapp.presenter.RiwayatPresenter
import com.mutia.kasirapp.presenter.RiwayatView
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import java.text.NumberFormat
import java.util.*

class DetailRiwayatActivity : AppCompatActivity(), RiwayatView {

    private var presenter: RiwayatPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)

        backDetail.setOnClickListener {
            finish()
        }

        val id = intent.getStringExtra("ID").toString()

        presenter = RiwayatPresenter(this)
        presenter!!.getRiwayatDetail(id)
    }

    override fun onSuccess(msg: String, data: List<ResponseGetPenjualan>?) {
        //
    }

    override fun onDetailSuccess(msg: String, data: List<ResponseRiwayatDetail>?, totBayar: String) {
        val detailAdapter = RiwayatDetailAdapter(data)
        rvDetailRiwayat.adapter = detailAdapter

        totHargaDetail.text = totBayar.toInt().convertRupiah()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
}