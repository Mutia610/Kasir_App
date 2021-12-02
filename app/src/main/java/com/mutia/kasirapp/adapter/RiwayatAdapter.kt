package com.mutia.kasirapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mutia.kasirapp.R
import com.mutia.kasirapp.model.ResponseGetPenjualan
import com.mutia.kasirapp.model.ResponsePreview
import kotlinx.android.synthetic.main.layout_item_preview.view.*
import kotlinx.android.synthetic.main.layout_item_riwayat.view.*
import java.text.NumberFormat
import java.util.*

class RiwayatAdapter (val data: List<ResponseGetPenjualan>?, val itemClick: OnClickListenerRiwayat) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tanggal = view.tanggal
        val totHarga = view.totHargaRiwayat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_item_riwayat,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        val tanggal = item?.tanggal
        val harga = item?.harga?.toInt()
        val id = item?.id.toString()


        holder.tanggal.text = tanggal
        holder.totHarga.text = harga?.convertRupiah()

        holder.view.setOnClickListener {
            itemClick.detail(id)
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    interface OnClickListenerRiwayat{
        fun detail(id: String)
    }
}