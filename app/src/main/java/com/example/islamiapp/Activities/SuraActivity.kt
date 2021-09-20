package com.example.islamiapp.Activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islamiapp.Adapters.AyaAdapter
import com.example.islamiapp.Constants
import com.example.islamiapp.R
import kotlinx.android.synthetic.main.activity_sura.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class SuraActivity : AppCompatActivity() {

    var ayat: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura)
        initView()
        backIC()
        homeIC()
    }

    fun initView() {
        val suraName = intent.getStringExtra(Constants.SURA_NAME_EXTRA)
        val suraPosition = intent.getIntExtra(Constants.POSSITION_EXTRA, 0)
        item_sura_name.text = suraName
        readFile(suraPosition)
        val adapter = AyaAdapter(ayat)
        sura_text_recycler.adapter = adapter
    }

    fun readFile(position: Int) {
        val reader: BufferedReader?

        try {
            val file: InputStream = assets.open((position + 1).toString() + ".txt")
            reader = BufferedReader(InputStreamReader(file))
            var line: String? = reader.readLine()
            while (line != null) {
                ayat.add(line)
                line = reader.readLine()
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    fun backIC(){
        backRow_ic.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
    fun homeIC(){
        home_ic.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}