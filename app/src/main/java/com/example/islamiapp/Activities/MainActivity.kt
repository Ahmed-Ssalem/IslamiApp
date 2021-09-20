package com.example.islamiapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.islamiapp.Fragments.HadithFragment
import com.example.islamiapp.Fragments.QuranFragment
import com.example.islamiapp.Fragments.RadioFragment
import com.example.islamiapp.Fragments.SebhaFragment
import com.example.islamiapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sura.*

class MainActivity : AppCompatActivity() {

    lateinit var moshaf : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moshaf = findViewById(R.id.moshaf)
        initView()
        click()
        homeIC()
    }

    fun initView() {
        sebha_ic.setColorFilter(resources.getColor(R.color.white))
        quran_ic.setColorFilter(resources.getColor(R.color.yellow))
        hadith_ic.setColorFilter(resources.getColor(R.color.white))
        moshaf.text = "المصحف قراءة"
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, QuranFragment())
            .commit()
    }

    fun click() {
        quran_ic.setOnClickListener {
            sebha_ic.setColorFilter(resources.getColor(R.color.white))
            quran_ic.setColorFilter(resources.getColor(R.color.yellow))
            hadith_ic.setColorFilter(resources.getColor(R.color.white))
            radio_ic.setColorFilter(resources.getColor(R.color.white))
            moshaf.text = "المصحف قراءة"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, QuranFragment())
                .commit()
        }
        hadith_ic.setOnClickListener{
            sebha_ic.setColorFilter(resources.getColor(R.color.white))
            quran_ic.setColorFilter(resources.getColor(R.color.white))
            hadith_ic.setColorFilter(resources.getColor(R.color.yellow))
            radio_ic.setColorFilter(resources.getColor(R.color.white))
            moshaf.text = "الحديث الشريف"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,HadithFragment())
                .commit()

        }
        sebha_ic.setOnClickListener {
            sebha_ic.setColorFilter(resources.getColor(R.color.yellow))
            quran_ic.setColorFilter(resources.getColor(R.color.white))
            hadith_ic.setColorFilter(resources.getColor(R.color.white))
            radio_ic.setColorFilter(resources.getColor(R.color.white))
            moshaf.text = "السبحة الإلكترونية"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SebhaFragment())
                .commit()
        }
        radio_ic.setOnClickListener {
            sebha_ic.setColorFilter(resources.getColor(R.color.white))
            quran_ic.setColorFilter(resources.getColor(R.color.white))
            hadith_ic.setColorFilter(resources.getColor(R.color.white))
            radio_ic.setColorFilter(resources.getColor(R.color.yellow))
            moshaf.text = "الراديو"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RadioFragment())
                .commit()
        }

    }
    fun homeIC(){
        home_ic3.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}