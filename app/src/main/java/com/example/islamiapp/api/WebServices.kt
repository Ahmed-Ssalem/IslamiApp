package com.example.islamiapp.api

import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("radios/radio_arabic.json")
    fun getRadiosChannel() : Call<RadioResponse>


}