package com.mutia.kasirapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mutia.kasirapp.R
import com.mutia.kasirapp.model.ResponseGetProduk
import kotlinx.android.synthetic.main.layout_item_produk.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ProdukAdapter(val data: List<ResponseGetProduk>?, val itemClick: OnClickListener) : RecyclerView.Adapter<ProdukAdapter.ViewHolder>()  {

    var isOnTextChanged = false
    var totalHarga = 0
    var jmlArray = ArrayList<String>()
    var produkArray = ArrayList<String>()

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val namaProduk = view.namaProduk
        val hargaProduk = view.hargaProduk
        val jmlBeli = view.jumlahBeli
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_item_produk,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        val nama = item?.nama
        val harga = item?.harga?.toInt()
        val id = item?.id

        holder.namaProduk.text = nama
        holder.hargaProduk.text = harga?.convertRupiah()

        holder.jmlBeli.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                isOnTextChanged = true
            }

            override fun afterTextChanged(editable: Editable) {
                totalHarga = 0
                if (isOnTextChanged) {
                    isOnTextChanged = false
                    try {
                        totalHarga = 0
                        for (i in 0..position) {
                            if (i != position) {
                                jmlArray.add("0")
                                produkArray.add("-")
                            } else {
                                jmlArray.add("0")
                                produkArray.add("-")

                                var hargaItem = harga?.times(editable.toString().toInt())
                                jmlArray.set(position, hargaItem.toString())
                                if (hargaItem != 0){
                                    produkArray.set(position, "$id-$nama-$harga-$editable-$hargaItem")
                                }
                                break
                            }
                        }
                        for (i in 0..jmlArray.size - 1) {
                            val tempTotal: Int = jmlArray.get(i).toInt()
                            totalHarga += tempTotal
                        }
                        itemClick.jumlah(totalHarga.toString(), produkArray)
                    } catch (e: NumberFormatException) {
                        totalHarga = 0
                        run {
                            var i = 0
                            while (i <= position) {
                                Log.d("TimesRemoved", " : $i")
                                if (i == position) {
                                    jmlArray.set(position, "0")
                                    produkArray.set(position, "-")
                                }
                                i++
                            }
                        }
                        var i = 0
                        while (i <= jmlArray.size - 1) {
                            val tempTotalExpenase: Int = jmlArray[i].toInt()
                            totalHarga += tempTotalExpenase
                            i++
                        }
                        itemClick.jumlah(totalHarga.toString(), produkArray)
                    }
                }
            }
        })
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    interface OnClickListener {
        fun jumlah(jml: String, data: ArrayList<String>)
    }


}