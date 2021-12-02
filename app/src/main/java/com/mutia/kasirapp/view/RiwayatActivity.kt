package com.mutia.kasirapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mutia.kasirapp.R
import com.mutia.kasirapp.adapter.RiwayatAdapter
import com.mutia.kasirapp.model.ResponseGetPenjualan
import com.mutia.kasirapp.model.ResponseRiwayatDetail
import com.mutia.kasirapp.presenter.ProdukPresenter
import com.mutia.kasirapp.presenter.RiwayatPresenter
import com.mutia.kasirapp.presenter.RiwayatView
import kotlinx.android.synthetic.main.activity_riwayat.*

class RiwayatActivity : AppCompatActivity(), RiwayatView {

    private var presenter : RiwayatPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        presenter = RiwayatPresenter(this)
        presenter!!.getRiwayat()

        backRiwayat.setOnClickListener {
            finish()
        }
    }

    override fun onSuccess(msg: String, data: List<ResponseGetPenjualan>?) {
        val riwayatAdapter = RiwayatAdapter(data, object : RiwayatAdapter.OnClickListenerRiwayat{
            override fun detail(id: String) {
                startActivity(Intent(this@RiwayatActivity, DetailRiwayatActivity::class.java).putExtra("ID",id))
            }
        })

        rvRiwayat.adapter = riwayatAdapter
    }

    override fun onDetailSuccess(msg: String, data: List<ResponseRiwayatDetail>?, totBayar: String) {
        //
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}