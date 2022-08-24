package com.sanjeet.weatherinfo.service

import com.sanjeet.weatherinfo.model.WeatherInfo
import com.sanjeet.weatherinfo.util.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {


    @GET("weather")
    fun getCurrentWeatherInfo(@Query("lat") lat:String,@Query("lon") lon:String,
    @Query("appid") appid:String): Call<WeatherInfo>


    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}