package com.example.islamiapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.islamiapp.Activities.HadithActivity
import com.example.islamiapp.Activities.SuraActivity
import com.example.islamiapp.Adapters.HadithAdapter
import com.example.islamiapp.Adapters.SuraAdapter
import com.example.islamiapp.Constants
import com.example.islamiapp.R
import kotlinx.android.synthetic.main.fragment_hadith.*
import kotlinx.android.synthetic.main.fragment_quran.*


class HadithFragment : Fragment() {

    val hadithNames: List<String> = listOf(
        "١ ",
        "٢ ",
        "٣ ",
        "٤ ",
        "٥ ",
        "٦ ",
        "٧ ",
        "٨ ",
        "٩ ",
        "١٠ ",
        "١١ ",
        "١٢ ",
        "١٣ ",
        "١٤ ",
        "١٥ ",
        "١٦ ",
        "١٧ ",
        "١٨ ",
        "١٩ ",
        "٢٠ ",
        "٢١ ",
        "٢٢ ",
        "٢٣ ",
        "٢٤ ",
        "٢٥ ",
        "٢٦ ",
        "٢٧ ",
        "٢٨ ",
        "٢٩ ",
        "٣٠ ",
        "٣١ ",
        "٣٢ ",
        "٣٣ ",
        "٣٤ ",
        "٣٥ ",
        "٣٦ ",
        "٣٧ ",
        "٣٨ ",
        "٣٩ ",
        "٤٠ ",
        "٤١ ",
        "٤٢ ",
        "٤٣ ",
        "٤٤ ",
        "٤٥ ",
        "٤٦ ",
        "٤٧ ",
        "٤٨ ",
        "٤٩ ",
        "٥٠ "
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hadith, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initHadithNamesRecycler()
    }

    fun initHadithNamesRecycler() {

        // link the adapter with the recyclerView
        var adapter = HadithAdapter(hadithNames)
        hadith_name_recycler.adapter = adapter

        // to swap slowly
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(hadith_name_recycler)

        // on click listener for every hadith

        adapter.setOnTextClickListener(object : HadithAdapter.onItemClickListener {
            override fun onItemClick(position: Int, hadithName: String) {
                val intent = Intent(context, HadithActivity::class.java)
                intent.putExtra(Constants.POSSITION_EXTRA2, position)
                //intent.putExtra(Constants.Hadith_NAME_EXTRA, hadithName)
                startActivity(intent)
            }


        })
    }

}