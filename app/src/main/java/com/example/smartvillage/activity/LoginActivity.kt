package com.example.smartvillage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.smartvillage.MainActivity
import com.example.smartvillage.R
import com.example.smartvillage.app.ApiConfig
import com.example.smartvillage.helper.SharedPref
import com.example.smartvillage.model.ResponModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s:SharedPref
    lateinit var fcm: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)
        getFcm()

        btn_login.setOnClickListener {
            login()
        }
    }

    private fun getFcm(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Respon", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            fcm = token.toString()
            // Log and toast
            Log.d("respon fcm:", token.toString())
        })
    }

    fun login (){
        if (edt_niklogin.text.isEmpty()){
            edt_niklogin.error = "Kolom NIK tidak boleh kosong"
            edt_niklogin.requestFocus()
            return
        } else if (edt_passwordlogin.text.isEmpty()){
            edt_passwordlogin.error = "Masukkan Password"
            edt_passwordlogin.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
       ApiConfig.instanceRetrofit.login(edt_niklogin.text.toString(),edt_passwordlogin.text.toString(), fcm).enqueue(object : Callback<ResponModel> {
           override fun onFailure(call: Call<ResponModel>, t: Throwable) {
               pb.visibility = View.GONE
               Toast.makeText(this@LoginActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
           }

           override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
               pb.visibility = View.GONE
               val respon = response.body()!!
               if (respon.success == 1){
                   s.setStatusLogin(true)
                   val intent = Intent(this@LoginActivity, MainActivity::class.java)
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                   startActivity(intent)
                   finish()
                   Toast.makeText(this@LoginActivity, "Selamat Datang", Toast.LENGTH_SHORT).show()
               } else{
                   Toast.makeText(this@LoginActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
               }
           }
       })
    }



}