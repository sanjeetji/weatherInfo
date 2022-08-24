package com.sanjeet.weatherinfo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanjeet.weatherinfo.model.WeatherInfo
import com.sanjeet.weatherinfo.repository.WeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel constructor(private val repository: WeatherRepository):ViewModel() {

    val data = MutableLiveData<WeatherInfo>()
    val errorMessage = MutableLiveData<String>()

    fun getCurrentWeatherInfo(lat:String,lon:String,appid:String){
        val response = repository.getCurrentWeatherInfo(lat, lon, appid)
        response.enqueue(object : Callback<WeatherInfo>{
            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}