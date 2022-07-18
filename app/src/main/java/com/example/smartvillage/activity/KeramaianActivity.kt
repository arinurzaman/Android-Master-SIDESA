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
import kotlinx.android.synthetic.main.activity_blm_nikah.*
import kotlinx.android.synthetic.main.activity_kematian.*
import kotlinx.android.synthetic.main.activity_keramaian.*
import kotlinx.android.synthetic.main.activity_keramaian.btn_kirim
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeramaianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keramaian)

        btn_kirim.setOnClickListener {
            kirim ()
        }
    }

    fun kirim (){
        if (edt_nik_panitia.text.isEmpty()) {
            edt_nik_panitia.error = "Kolom NIK tidak boleh kosong"
            edt_nik_panitia.requestFocus()
            return
        } else if (edt_nama_panitia.text.isEmpty()) {
            edt_nama_panitia.error = "Kolom NIK tidak boleh kosong"
            edt_nama_panitia.requestFocus()
            return
        } else if (edt_umur_panitia.text.isEmpty()) {
            edt_umur_panitia.error = "Kolom NIK tidak boleh kosong"
            edt_umur_panitia.requestFocus()
            return
        } else if (edt_pekerjaan_panitia.text.isEmpty()) {
            edt_pekerjaan_panitia.error = "Kolom NIK tidak boleh kosong"
            edt_pekerjaan_panitia.requestFocus()
            return
        } else if (edt_alamat_panitia.text.isEmpty()) {
            edt_alamat_panitia.error = "Kolom NIK tidak boleh kosong"
            edt_alamat_panitia.requestFocus()
            return
        } else if (edt_tgl_kegiatan.text.isEmpty()) {
            edt_tgl_kegiatan.error = "Kolom NIK tidak boleh kosong"
            edt_tgl_kegiatan.requestFocus()
            return
        } else if (edt_tempat_kegiatan.text.isEmpty()) {
            edt_tempat_kegiatan.error = "Kolom NIK tidak boleh kosong"
            edt_tempat_kegiatan.requestFocus()
            return
        } else if (edt_kegiatan.text.isEmpty()) {
            edt_kegiatan.error = "Kolom NIK tidak boleh kosong"
            edt_kegiatan.requestFocus()
            return
        }

        pbkeramaian.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.keramaian(
            edt_nik_panitia.text.toString(),
            edt_nama_panitia.text.toString(),
            edt_umur_panitia.text.toString(),
            edt_pekerjaan_panitia.text.toString(),
            edt_alamat_panitia.text.toString(),
            edt_tgl_kegiatan.text.toString(),
            edt_tempat_kegiatan.text.toString(),
            edt_kegiatan.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle Gagal
                pbkeramaian.visibility = View.GONE
                Toast.makeText(this@KeramaianActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle Berhasil
                pbkeramaian.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success ==  1) {
                    val intent = Intent(this@KeramaianActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@KeramaianActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}