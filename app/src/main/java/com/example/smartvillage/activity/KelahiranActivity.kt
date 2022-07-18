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
import kotlinx.android.synthetic.main.activity_domisili.*
import kotlinx.android.synthetic.main.activity_kelahiran.*
import kotlinx.android.synthetic.main.activity_kelahiran.btn_kirim
import kotlinx.android.synthetic.main.activity_kematian.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelahiranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelahiran)

        btn_kirim.setOnClickListener {
            kirim ()
        }
    }

    fun kirim () {
        if (edt_nama_ibu.text.isEmpty()) {
            edt_nama_ibu.error = "Kolom nama tidak boleh kosong"
            edt_nama_ibu.requestFocus()
            return
        } else if (edt_umur_ibu.text.isEmpty()) {
            edt_umur_ibu.error = "Kolom umur tidak boleh kosong"
            edt_umur_ibu.requestFocus()
            return
        } else if (edt_pekerjaan_ibu.text.isEmpty()) {
            edt_pekerjaan_ibu.error = "Kolom pekerjaan tidak boleh kosong"
            edt_pekerjaan_ibu.requestFocus()
            return
        } else if (edt_namasuami.text.isEmpty()) {
            edt_namasuami.error = "Kolom nama suami tidak boleh kosong"
            edt_namasuami.requestFocus()
            return
        } else if (edt_umursuami.text.isEmpty()) {
            edt_umursuami.error = "Kolom umur suami tidak boleh kosong"
            edt_umursuami.requestFocus()
            return
        } else if (edt_pekerjaansuami.text.isEmpty()) {
            edt_pekerjaansuami.error = "Kolom pekerjaan suami tidak boleh kosong"
            edt_pekerjaansuami.requestFocus()
            return
        } else if (edt_alamatsuami.text.isEmpty()) {
            edt_alamatsuami.error = "Kolom alamat suami tidak boleh kosong"
            edt_alamatsuami.requestFocus()
            return
        } else if (edt_tgl_lahir_anak.text.isEmpty()) {
            edt_tgl_lahir_anak.error = "Kolom tanggal lahir tidak boleh kosong"
            edt_tgl_lahir_anak.requestFocus()
            return
        } else if (edt_jamlahir.text.isEmpty()) {
            edt_jamlahir.error = "Kolom jam lahir tidak boleh kosong"
            edt_jamlahir.requestFocus()
            return
        } else if (edt_nama_anak.text.isEmpty()) {
            edt_nama_anak.error = "Kolom nama anak tidak boleh kosong"
            edt_nama_anak.requestFocus()
            return
        } else if (edt_jk_anak.text.isEmpty()) {
            edt_jk_anak.error = "Kolom jenis kelamin tidak boleh kosong"
            edt_jk_anak.requestFocus()
            return
        } else if (edt_bb.text.isEmpty()) {
            edt_bb.error = "Kolom berat badan tidak boleh kosong"
            edt_bb.requestFocus()
            return
        } else if (edt_pb.text.isEmpty()) {
            edt_pb.error = "Kolom panjang badan tidak boleh kosong"
            edt_pb.requestFocus()
            return
        } else if (edt_tempatlahir_anak.text.isEmpty()) {
            edt_tempatlahir_anak.error = "Kolom tempat lahir tidak boleh kosong"
            edt_tempatlahir_anak.requestFocus()
            return
        } else if (edt_anakke.text.isEmpty()) {
            edt_anakke.error = "Kolom anak ke tidak boleh kosong"
            edt_anakke.requestFocus()
            return
        } else if (edt_agama_anak.text.isEmpty()) {
            edt_agama_anak.error = "Kolom agama tidak boleh kosong"
            edt_agama_anak.requestFocus()
            return
        }

        pbkelahiran.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.kelahiran(
            edt_nama_ibu.text.toString(),
            edt_umur_ibu.text.toString(),
            edt_pekerjaan_ibu.text.toString(),
            edt_namasuami.text.toString(),
            edt_umursuami.text.toString(),
            edt_pekerjaansuami.text.toString(),
            edt_alamatsuami.text.toString(),
            edt_tgl_lahir_anak.text.toString(),
            edt_jamlahir.text.toString(),
            edt_nama_anak.text.toString(),
            edt_jk_anak.text.toString(),
            edt_bb.text.toString(),
            edt_pb.text.toString(),
            edt_tempatlahir_anak.text.toString(),
            edt_anakke.text.toString(),
            edt_agama_anak.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                //handle gagal
                pbkelahiran.visibility = View.GONE
                Toast.makeText(this@KelahiranActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                //handle berhasil
                pbkelahiran.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success ==  1) {
                    val intent = Intent(this@KelahiranActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@KelahiranActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}