package com.example.islamiapp.Activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islamiapp.Adapters.SingleHadithAdapter
import com.example.islamiapp.Constants
import com.example.islamiapp.Fragments.HadithFragment
import com.example.islamiapp.R
import kotlinx.android.synthetic.main.activity_hadith.*
import kotlinx.android.synthetic.main.activity_sura.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class HadithActivity : AppCompatActivity() {

    var hadith : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadith)
        initView()
        backIC()
        homeIC()
    }

    fun initView() {
        //val hadithName = intent.getStringExtra(Constants.Hadith_NAME_EXTRA)
        val hadithPosition = intent.getIntExtra(Constants.POSSITION_EXTRA2, 0)
        //item_hadith_name.text = hadithName
        readFile(hadithPosition)
        val adapter = SingleHadithAdapter(hadith)
        hadith_text_recycler.adapter = adapter
    }

    fun readFile(position: Int) {
        val reader: BufferedReader?

        try {
            val file: InputStream = assets.open((position + 115).toString() + ".txt")
            reader = BufferedReader(InputStreamReader(file))
            var line: String? = reader.readLine()
            while (line != null) {
                hadith.add(line)
                line = reader.readLine()
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    fun backIC(){
        backRow_ic2.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun homeIC(){
        home_ic2.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}