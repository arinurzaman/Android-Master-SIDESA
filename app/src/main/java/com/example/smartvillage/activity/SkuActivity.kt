package com.example.smartvillage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.smartvillage.MainActivity
import com.example.smartvillage.R
import com.example.smartvillage.app.ApiConfig
import com.example.smartvillage.model.ResponModel
import kotlinx.android.synthetic.main.activity_kematian.*
import kotlinx.android.synthetic.main.activity_sku.*
import kotlinx.android.synthetic.main.activity_sku.btn_kirim
import kotlinx.android.synthetic.main.activity_sku.pbsku
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sku)

        btn_kirim.setOnClickListener {
            kirim ()
        }
    }

    fun kirim () {
        if (edt_nik.text.isEmpty()) {
            edt_nik.error = "Kolom NIK tidak boleh kosong"
            edt_nik.requestFocus()
            return
        } else if (edt_nama.text.isEmpty()) {
            edt_nama.error = "Kolom Nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        } else if (edt_umur.text.isEmpty()) {
            edt_umur.error = "Kolom Umur tidak boleh kosong"
            edt_umur.requestFocus()
            return
        } else if (edt_alamat.text.isEmpty()) {
            edt_alamat.error = "Kolom Alamat tidak boleh kosong"
            edt_alamat.requestFocus()
            return
        } else if (edt_nohp.text.isEmpty()) {
            edt_nohp.error = "Kolom No HP tidak boleh kosong"
            edt_nohp.requestFocus()
            return
        } else if (edt_nama_usaha.text.isEmpty()) {
            edt_nama_usaha.error = "Kolom NIK tidak boleh kosong"
            edt_nama_usaha.requestFocus()
            return
        }

        pbsku.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.sku(
            edt_nik.text.toString(),
            edt_nama.text.toString(),
            edt_umur.text.toString(),
            edt_alamat.text.toString(),
            edt_nohp.text.toString(),
            edt_nama_usaha.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle Gagal
                pbsku.visibility = View.GONE
                Toast.makeText(this@SkuActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle Berhasil
                pbsku.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success ==  1) {
                    val intent = Intent(this@SkuActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@SkuActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }
}