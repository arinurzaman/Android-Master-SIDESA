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
import kotlinx.android.synthetic.main.activity_blm_nikah.btn_kirim
import kotlinx.android.synthetic.main.activity_kelahiran.*
import kotlinx.android.synthetic.main.activity_kematian.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlmNikahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blm_nikah)

        btn_kirim.setOnClickListener {
            kirim ()
        }
    }

    fun kirim(){
        if (edt_nik_blmnikah.text.isEmpty()) {
            edt_nik_blmnikah.error = "Kolom NIK tidak boleh kosong"
            edt_nik_blmnikah.requestFocus()
            return
        } else if (edt_nama_blmnikah.text.isEmpty()) {
            edt_nama_blmnikah.error = "Kolom Nama tidak boleh kosong"
            edt_nama_blmnikah.requestFocus()
            return
        } else if (edt_tempatlahir_blmnikah.text.isEmpty()) {
            edt_tempatlahir_blmnikah.error = "Kolom Tempat Lahir tidak boleh kosong"
            edt_tempatlahir_blmnikah.requestFocus()
            return
        } else if (edt_tanggal_lahir_blmnikah.text.isEmpty()) {
            edt_tanggal_lahir_blmnikah.error = "Kolom Tanggal Lahir tidak boleh kosong"
            edt_tanggal_lahir_blmnikah.requestFocus()
            return
        } else if (edt_jk_blmnikah.text.isEmpty()) {
            edt_jk_blmnikah.error = "Kolom Jenis Kelamin tidak boleh kosong"
            edt_jk_blmnikah.requestFocus()
            return
        } else if (edt_agama_blmnikah.text.isEmpty()) {
            edt_agama_blmnikah.error = "Kolom Agama tidak boleh kosong"
            edt_agama_blmnikah.requestFocus()
            return
        } else if (edt_pekerjaan_blmnikah.text.isEmpty()) {
            edt_pekerjaan_blmnikah.error = "Kolom Pekerjaan tidak boleh kosong"
            edt_pekerjaan_blmnikah.requestFocus()
            return
        } else if (edt_alamat_blmnikah.text.isEmpty()) {
            edt_alamat_blmnikah.error = "Kolom Alamat tidak boleh kosong"
            edt_alamat_blmnikah.requestFocus()
            return
        }

        pbblmnikah.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.blmnikah(
            edt_nik_blmnikah.text.toString(),
            edt_nama_blmnikah.text.toString(),
            edt_tempatlahir_blmnikah.text.toString(),
            edt_tanggal_lahir_blmnikah.text.toString(),
            edt_jk_blmnikah.text.toString(),
            edt_agama_blmnikah.text.toString(),
            edt_pekerjaan_blmnikah.text.toString(),
            edt_alamat_blmnikah.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle gagal
                pbblmnikah.visibility = View.GONE
                Toast.makeText(this@BlmNikahActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle berhasil
                pbblmnikah.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success ==  1) {
                    val intent = Intent(this@BlmNikahActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@BlmNikahActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}