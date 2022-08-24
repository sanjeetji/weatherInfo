package com.sanjeet.weatherinfo.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.gms.location.*
import com.sanjeet.weatherinfo.R
import com.sanjeet.weatherinfo.databinding.ActivityMainBinding
import com.sanjeet.weatherinfo.model.DateTime
import com.sanjeet.weatherinfo.model.TimeTemp
import com.sanjeet.weatherinfo.model.WeatherInfo
import com.sanjeet.weatherinfo.repository.WeatherRepository
import com.sanjeet.weatherinfo.service.RetrofitService
import com.sanjeet.weatherinfo.util.Constant.APP_ID
import com.sanjeet.weatherinfo.view.adpter.DateTimeAdapter
import com.sanjeet.weatherinfo.view.adpter.TimeTempAdapter
import com.sanjeet.weatherinfo.viewModel.WeatherViewModel
import com.sanjeet.weatherinfo.viewModel.WeatherViewModelFactory


class MainActivity : AppCompatActivity(){


    //location var
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var latitudeLabel: String? = null
    private var longitudeLabel: String? = null
    private var weatherInfo:MutableList<WeatherInfo>?=null


    val PERMISSION_ID = 42

    lateinit var viewModel: WeatherViewModel
    private val retrofitService = RetrofitService.getInstance()
    lateinit var dateTimeAdapter:DateTimeAdapter
    lateinit var timeTempAdapter: TimeTempAdapter
    lateinit var binding:ActivityMainBinding
    private val TAG = "MainActivity"
    private var dateTimeList:MutableList<DateTime>?=null
    private var timeTempList:MutableList<TimeTemp>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this, WeatherViewModelFactory(WeatherRepository(retrofitService))).get(WeatherViewModel::class.java)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()


        weatherInfo = arrayListOf()
        dateTimeList = arrayListOf()
        timeTempList = arrayListOf()
        dateTimeAdapter = DateTimeAdapter()
        timeTempAdapter = TimeTempAdapter()

        dateTimeList?.add(DateTime("Mon","21/03", R.drawable.ic_cloud))
        dateTimeList?.add(DateTime("Mon","21/03", R.drawable.ic_cloud))
        dateTimeList?.add(DateTime("Mon","21/03", R.drawable.ic_cloud))
        dateTimeList?.add(DateTime("Mon","21/03", R.drawable.ic_cloud))
        dateTimeList?.add(DateTime("Mon","21/03", R.drawable.ic_cloud))


        timeTempList?.add(TimeTemp("14:30", R.drawable.ic_cloud,"35"))
        timeTempList?.add(TimeTemp("14:30", R.drawable.ic_cloud,"35"))
        timeTempList?.add(TimeTemp("14:30", R.drawable.ic_cloud,"35"))
        timeTempList?.add(TimeTemp("14:30", R.drawable.ic_cloud,"35"))
        timeTempList?.add(TimeTemp("14:30", R.drawable.ic_cloud,"35"))


        //set Recyclerview
        binding.dayRv.apply {
            adapter = dateTimeAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        dateTimeAdapter.setContentList(dateTimeList as ArrayList<DateTime>)

        //set Recyclerview
        binding.dayRv2.apply {
            adapter = timeTempAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        timeTempAdapter.setContentList(timeTempList as ArrayList<TimeTemp>)


        //First Line Chart
        binding.lineChart.isDragEnabled = true
        binding.lineChart.setScaleEnabled(false)
        binding.lineChart.getLegend().setEnabled(false)
        val leftAxies = binding.lineChart.axisLeft
        val righAsies = binding.lineChart.axisRight
        val des: Description = binding.lineChart.getDescription()
        des.isEnabled = false
        leftAxies.isEnabled = false
        righAsies.isEnabled = false

        val yValues:ArrayList<Entry> = arrayListOf()
        yValues.add(Entry(0F,36.9f))
        yValues.add(Entry(1F,39.7f))
        yValues.add(Entry(2F,39.0f))
        yValues.add(Entry(3F,38.6f))
        yValues.add(Entry(4F,39.5f))
        yValues.add(Entry(5F,39.8f))
        yValues.add(Entry(6F,41.5f))
        val lineDataSet1:LineDataSet = LineDataSet(yValues,"")//Data set 1
        lineDataSet1.fillAlpha = 110
        val dataSets:ArrayList<LineDataSet> = arrayListOf()
        dataSets.add(lineDataSet1)
        lineDataSet1.valueTextColor = resources.getColor(R.color.white)
        lineDataSet1.color = resources.getColor(R.color.white)
        lineDataSet1.setCircleColor(R.color.white)
        val data:LineData = LineData(dataSets as List<ILineDataSet>?)
        binding.lineChart.data = data


        //Second Line Chart
        binding.lineChart2.isDragEnabled = true
        binding.lineChart2.setScaleEnabled(false)
        binding.lineChart2.getLegend().setEnabled(false)
        val leftAxies2 = binding.lineChart2.axisLeft
        val righAsies2 = binding.lineChart2.axisRight
        val des2: Description = binding.lineChart2.getDescription()
        des2.isEnabled = false
        leftAxies2.isEnabled = false
        righAsies2.isEnabled = false

        val yValues2:ArrayList<Entry> = arrayListOf()
        yValues2.add(Entry(0F,36.9f))
        yValues2.add(Entry(1F,39.7f))
        yValues2.add(Entry(2F,39.0f))
        yValues2.add(Entry(3F,38.6f))
        yValues2.add(Entry(4F,39.5f))
        yValues2.add(Entry(5F,39.8f))
        yValues2.add(Entry(6F,41.5f))
        val lineDataSet2:LineDataSet = LineDataSet(yValues2,"")//Data set 1
        lineDataSet2.fillAlpha = 110
        val dataSets2:ArrayList<LineDataSet> = arrayListOf()
        dataSets2.add(lineDataSet2)
        lineDataSet2.valueTextColor = resources.getColor(R.color.white)
        lineDataSet2.color = resources.getColor(R.color.white)
        lineDataSet2.setCircleColor(R.color.white)
        val data2:LineData = LineData(dataSets2 as List<ILineDataSet>?)
        binding.lineChart2.data = data2

    }

    private fun getCurrentWeatherInfo(latitudeLabel: String?, longitudeLabel: String?, appId: String) {

        val info = viewModel.getCurrentWeatherInfo(latitudeLabel!!,longitudeLabel!!,appId)
        Log.e("======","Info is : $info")
    }

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        Log.e("========","Latitude is : ${location.latitude.toString()}")
                        Log.e("========","Longitude is : ${location.longitude.toString()}")
                        latitudeLabel = location.latitude.toString()
                        longitudeLabel = location.longitude.toString()
                        getCurrentWeatherInfo(latitudeLabel,longitudeLabel,APP_ID)
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location? = locationResult.lastLocation
            Log.e("========","Latitude is : ${mLastLocation?.latitude.toString()}")
            Log.e("========","Longitude is : ${mLastLocation?.longitude.toString()}")
            latitudeLabel = mLastLocation?.latitude.toString()
            longitudeLabel = mLastLocation?.longitude.toString()
            getCurrentWeatherInfo(latitudeLabel,longitudeLabel,APP_ID)
        }
    }
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

}