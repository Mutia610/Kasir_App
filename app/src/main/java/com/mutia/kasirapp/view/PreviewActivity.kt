package com.mutia.kasirapp.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mutia.kasirapp.R
import com.mutia.kasirapp.adapter.PreviewAdapter
import com.mutia.kasirapp.model.ResponsePreview
import com.mutia.kasirapp.presenter.PreviewPresenter
import com.mutia.kasirapp.presenter.PreviewView
import com.mutia.kasirapp.presenter.ProdukPresenter
import kotlinx.android.synthetic.main.activity_preview.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class PreviewActivity : AppCompatActivity(), PreviewView {

    private var presenter : PreviewPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        presenter = PreviewPresenter(this)

        backPreview.setOnClickListener {
            finish()
        }

        val getData = intent.getStringArrayListExtra("DATA")
        val preview = ArrayList<ResponsePreview>()
        var totalBayar = 0

        val byk = getData?.size?.minus(1)

        for (i in 0..byk!!){
            if (getData[i] != "-"){
                Log.d("Pre ", "onCreate $byk: ${getData.get(i)}")
                val dataProduk = getData[i].split("-").toTypedArray()
                preview.add(ResponsePreview(dataProduk[0].toInt(), dataProduk[1], dataProduk[2], dataProduk[3], dataProduk[4]))

                totalBayar += dataProduk[4].toInt()
            }
        }

        val previewAdapter = PreviewAdapter(preview)
        rvPreview.adapter = previewAdapter

        totalHarga.text = totalBayar.convertRupiah()

        btnSelesai.setOnClickListener {
            val x = preview.size - 1
            for (i in 0..x){
                presenter!!.insertPembelian(preview.get(i).id.toString(), preview.get(i).jumlah.toString())
                Log.d("Hasil", "onCreate: " + preview.get(i).id.toString() + preview.get(i).jumlah.toString())
            }
        }
    }

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    override fun onSuccess(msg: String, idProduk: String, jumlah: String) {
        android.app.AlertDialog.Builder(this).apply {
            setTitle(msg)
            setIcon(R.drawable.ic_check)
            setCancelable(false)
            setPositiveButton("OK") { dialogInterface, i ->
                startActivity(Intent(this@PreviewActivity, MainActivity::class.java))
                finish()
            }
        }.show()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}