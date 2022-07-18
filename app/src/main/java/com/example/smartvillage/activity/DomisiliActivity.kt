package com.example.smartvillage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.smartvillage.MainActivity
import com.example.smartvillage.R
import com.example.smartvillage.app.ApiConfig
import com.example.smartvillage.model.ResponModel
import kotlinx.android.synthetic.main.activity_domisili.*
import kotlinx.android.synthetic.main.activity_domisili.pbdomisili
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DomisiliActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domisili)

        btn_kirim.setOnClickListener {
            kirim()
        }
    }

    fun kirim() {
        if (edt_nik.text.isEmpty()) {
            edt_nik.error = "Kolom nik tidak boleh kosong"
            edt_nik.requestFocus()
            return
        } else if (edt_nama.text.isEmpty()) {
            edt_nama.error = "Kolom nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        } else if (edt_jk.text.isEmpty()) {
            edt_jk.error = "Kolom jenis kelamin tidak boleh kosong"
            edt_jk.requestFocus()
            return
        } else if (edt_tempatlahir.text.isEmpty()) {
            edt_tempatlahir.error = "Kolom tempat lahir tidak boleh kosong"
            edt_tempatlahir.requestFocus()
            return
        } else if (edt_tanggallahir.text.isEmpty()) {
            edt_tanggallahir.error = "Kolom tanggal lahir tidak boleh kosong"
            edt_tanggallahir.requestFocus()
            return
        } else if (edt_warga.text.isEmpty()) {
            edt_warga.error = "Kolom kewarganegaraan tidak boleh kosong"
            edt_warga.requestFocus()
            return
        } else if (edt_agama.text.isEmpty()) {
            edt_agama.error = "Kolom agama tidak boleh kosong"
            edt_agama.requestFocus()
            return
        } else if (edt_pekerjaan.text.isEmpty()) {
            edt_pekerjaan.error = "Kolom pekerjaan tidak boleh kosong"
            edt_pekerjaan.requestFocus()
            return
        } else if (edt_alamat.text.isEmpty()) {
            edt_alamat.error = "Kolom alamat tidak boleh kosong"
            edt_alamat.requestFocus()
            return
        }

        pbdomisili.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.domisili(
            edt_nik.text.toString(),
            edt_nama.text.toString(),
            edt_jk.text.toString(),
            edt_tempatlahir.text.toString(),
            edt_tanggallahir.text.toString(),
            edt_warga.text.toString(),
            edt_agama.text.toString(),
            edt_pekerjaan.text.toString(),
            edt_alamat.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // handle gagal
                pbdomisili.visibility = View.GONE
                Toast.makeText(this@DomisiliActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // handel success
                pbdomisili.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success ==  1) {
                    val intent = Intent(this@DomisiliActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@DomisiliActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

}