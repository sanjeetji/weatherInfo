package com.sanjeet.weatherinfo.repository

import com.sanjeet.weatherinfo.service.RetrofitService

class WeatherRepository constructor(private val retrofitService: RetrofitService) {

    fun getCurrentWeatherInfo(lat:String,lon:String,appid:String) = retrofitService.getCurrentWeatherInfo(
        lat, lon, appid)
}