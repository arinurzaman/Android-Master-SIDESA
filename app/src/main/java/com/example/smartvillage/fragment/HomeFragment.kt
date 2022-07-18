package com.example.smartvillage.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.smartvillage.R
import com.example.smartvillage.activity.*
import com.example.smartvillage.helper.SharedPref

class HomeFragment : Fragment() {

    lateinit var s: SharedPref

    lateinit var domisili: CardView
    lateinit var kematian: CardView
    lateinit var sku: CardView
    lateinit var kelahiran: CardView
    lateinit var belumnikah: CardView
    lateinit var keramaian: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)

        s = SharedPref(requireActivity())

        mainButton()
        return view

    }

    private fun mainButton(){
        domisili.setOnClickListener {
            startActivity(Intent(requireActivity(), DomisiliActivity::class.java))
        }
        kematian.setOnClickListener {
            startActivity(Intent(requireActivity(), KematianActivity::class.java))
        }
        sku.setOnClickListener {
            startActivity(Intent(requireActivity(), SkuActivity::class.java))
        }
        kelahiran.setOnClickListener {
            startActivity(Intent(requireActivity(), KelahiranActivity::class.java))
        }
        belumnikah.setOnClickListener {
            startActivity(Intent(requireActivity(), BlmNikahActivity::class.java))
        }
        keramaian.setOnClickListener {
            startActivity(Intent(requireActivity(), KeramaianActivity::class.java))
        }

    }

    private fun init(view: View){
        domisili = view.findViewById(R.id.domisili)
        kematian = view.findViewById(R.id.kematian)
        sku = view.findViewById(R.id.sku)
        kelahiran = view.findViewById(R.id.kelahiran)
        belumnikah = view.findViewById(R.id.belumnikah)
        keramaian = view.findViewById(R.id.keramaian)
    }



}