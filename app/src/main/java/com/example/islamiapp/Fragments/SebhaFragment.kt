package com.example.islamiapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.islamiapp.R
import kotlinx.android.synthetic.main.fragment_sebha.*

class SebhaFragment : Fragment() ,AdapterView.OnItemSelectedListener{

    var counter = 0
    var totalCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sebha, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

    }

    fun initView(){

        tasbiha_counter.text = "" +  counter
        total_tasbih_counter.text = "" + totalCounter

        tasbih.setOnClickListener {
            counter++
            totalCounter++
            tasbiha_counter.text = "" +  counter
            total_tasbih_counter.text = "" + totalCounter
        }

        refresh.setOnClickListener {
            counter = 0
            tasbiha_counter.text = "" +  counter
            totalCounter = 0
            total_tasbih_counter.text = "" + totalCounter
        }

        spinner_doaa.onItemSelectedListener = this
        
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        counter = 0
        tasbiha_counter.text = "" +  counter
    }


}