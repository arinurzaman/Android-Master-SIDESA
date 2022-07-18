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
import kotlinx.android.synthetic.main.activity_kematian.pbkematian
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KematianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kematian)

        btn_kirim.setOnClickListener {
            kirim()
        }
    }

    fun kirim () {
        if (edt_nama_pelapor.text.isEmpty()) {
            edt_nama_pelapor.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_nama_pelapor.requestFocus()
            return
        } else if (edt_umur_pelapor.text.isEmpty()) {
            edt_umur_pelapor.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_umur_pelapor.requestFocus()
            return
        } else if (edt_pekerjaan_pelapor.text.isEmpty()) {
            edt_pekerjaan_pelapor.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_pekerjaan_pelapor.requestFocus()
            return
        } else if (edt_alamat_pelapor.text.isEmpty()) {
            edt_alamat_pelapor.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_alamat_pelapor.requestFocus()
            return
        } else if (edt_agama_pelapor.text.isEmpty()) {
            edt_agama_pelapor.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_agama_pelapor.requestFocus()
            return
        } else if (edt_nik_pelapor.text.isEmpty()) {
            edt_nik_pelapor.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_nik_pelapor.requestFocus()
            return
        } else if (edt_nama_jenazah.text.isEmpty()) {
            edt_nama_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_nama_jenazah.requestFocus()
            return
        } else if (edt_jk_jenazah.text.isEmpty()) {
            edt_jk_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_jk_jenazah.requestFocus()
            return
        } else if (edt_tempat_lahir_jenazah.text.isEmpty()) {
            edt_tempat_lahir_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_tempat_lahir_jenazah.requestFocus()
            return
        } else if (edt_tanggal_lahir_jenazah.text.isEmpty()) {
            edt_tanggal_lahir_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_tanggal_lahir_jenazah.requestFocus()
            return
        } else if (edt_agama_jenazah.text.isEmpty()) {
            edt_agama_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_agama_jenazah.requestFocus()
            return
        } else if (edt_ayah_jenazah.text.isEmpty()) {
            edt_ayah_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_ayah_jenazah.requestFocus()
            return
        } else if (edt_ibu_jenazah.text.isEmpty()) {
            edt_ibu_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_ibu_jenazah.requestFocus()
            return
        } else if (edt_alamat_jenazah.text.isEmpty()) {
            edt_alamat_jenazah.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_alamat_jenazah.requestFocus()
            return
        } else if (edt_harimeninggal.text.isEmpty()) {
            edt_harimeninggal.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_harimeninggal.requestFocus()
            return
        } else if (edt_tanggalmeninggal.text.isEmpty()) {
            edt_tanggalmeninggal.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_tanggalmeninggal.requestFocus()
            return
        } else if (edt_tempatmeninggal.text.isEmpty()) {
            edt_tempatmeninggal.error = "Kolom Nama Pelapor Tidak Boleh Kosong"
            edt_tempatmeninggal.requestFocus()
            return
        }

        pbkematian.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.kematian(
            edt_nama_pelapor.text.toString(),
            edt_umur_pelapor.text.toString(),
            edt_pekerjaan_pelapor.text.toString(),
            edt_alamat_pelapor.text.toString(),
            edt_agama_pelapor.text.toString(),
            edt_nik_pelapor.text.toString(),
            edt_nama_jenazah.text.toString(),
            edt_jk_jenazah.text.toString(),
            edt_tempat_lahir_jenazah.text.toString(),
            edt_tanggal_lahir_jenazah.text.toString(),
            edt_agama_jenazah.text.toString(),
            edt_ayah_jenazah.text.toString(),
            edt_ibu_jenazah.text.toString(),
            edt_alamat_jenazah.text.toString(),
            edt_harimeninggal.text.toString(),
            edt_tanggalmeninggal.text.toString(),
            edt_tempatmeninggal.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle Gagal
                pbkematian.visibility = View.GONE
                Toast.makeText(this@KematianActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle Berhasil
                pbkematian.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success ==  1) {
                    val intent = Intent(this@KematianActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@KematianActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}