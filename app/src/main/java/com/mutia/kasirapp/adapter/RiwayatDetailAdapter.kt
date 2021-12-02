package com.mutia.kasirapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mutia.kasirapp.R
import com.mutia.kasirapp.model.ResponsePreview
import com.mutia.kasirapp.model.ResponseRiwayatDetail
import kotlinx.android.synthetic.main.layout_item_preview.view.*
import java.text.NumberFormat
import java.util.*

class RiwayatDetailAdapter (val data: List<ResponseRiwayatDetail>?) : RecyclerView.Adapter<RiwayatDetailAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val namaProduk = view.namaProdukPreview
        val hargaProduk = view.hargaProdukPreview
        val totalHarga = view.totHargaPreview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_item_preview,
            parent,
            false
        )
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        val nama = item?.nama
        val harga = item?.harga?.toInt()
        val id = item?.id
        val jumlah = item?.qty

        holder.namaProduk.text = nama
        holder.hargaProduk.text = "$jumlah x ${harga?.convertRupiah()}"
        holder.totalHarga.text = (jumlah!! * harga!!).convertRupiah()
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
}