package com.parents.weatherapp.presentation.cities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.parents.weatherapp.BR
import com.parents.weatherapp.R
import com.parents.weatherapp.databinding.ActivityCitiesBinding
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.Coord
import com.parents.weatherapp.presentation.base.BaseActivity
import com.parents.weatherapp.presentation.fivedayforecast.FiveDayForecastActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import java.util.*

@ExperimentalCoroutinesApi
class CitiesActivity : BaseActivity<ActivityCitiesBinding, CitiesViewModel>(),
    CitiesAdapter.OnCityClickListener {

    val mViewModel: CitiesViewModel by viewModel()
    lateinit var mViewBinding: ActivityCitiesBinding
    override val viewModel: CitiesViewModel
        get() = mViewModel
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_cities
    private lateinit var citiesAdapter: CitiesAdapter
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private lateinit var city: City
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = viewDataBinding!!
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        citiesAdapter = CitiesAdapter(this)
        mViewBinding.recyclerview.adapter = citiesAdapter
        mViewBinding.progressbar.show()
        mViewModel.showProgressbar.observe(this, androidx.lifecycle.Observer { isVisible ->
            if (isVisible) mViewBinding.progressbar.show() else mViewBinding.progressbar.hide()
        })
        mViewModel.messageData.observe(this, androidx.lifecycle.Observer { message ->
            Snackbar.make(
                mViewBinding.constraintlayout,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        })
        mViewModel.cities.observe(this, androidx.lifecycle.Observer { cities: List<City> ->
            citiesAdapter.mCitiesList = cities
            Log.d(TAG, "cities count= ${cities.size}")
        })
    }

    public override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                ) {
                    Log.i(TAG, "Requesting permission")
                    requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        REQUEST_PERMISSIONS_REQUEST_CODE
                    )
                } else {//here app opened later after permission firstly denied]
                    city = City()
                    mViewModel.saveCurrentLocationCity(city)
                }
            }
        } else {//permission already granted  before ==> //todo replace location
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient?.lastLocation!!.addOnCompleteListener(this) { task ->
            if (task.isSuccessful && task.result != null) {
                lastLocation = task.result
                if (lastLocation != null) {
                    city = getCity(lastLocation!!.latitude, lastLocation!!.longitude)
                } else {
                    city = City()
                    Toast.makeText(
                        this,
                        "lastLocation=null",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                city = City()
                Toast.makeText(
                    this,
                    "No location detected. Make sure location is enabled on the device.",
                    Toast.LENGTH_LONG
                ).show()
            }
            mViewModel.saveCurrentLocationCity(city)
        }
    }

    private fun getCity(lat: Double, lon: Double): City {
        val geoCoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geoCoder.getFromLocation(lat, lon, 10)
            if (!addresses.isNullOrEmpty()) {
                for (adr in addresses) {
                    if (adr != null && adr.locality != null && adr.locality.isNotEmpty()) {
                        val coord = Coord(lat = adr.latitude, lon = adr.longitude)
                        return City(
                            null,
                            adr.countryName,
                            adr.locality,
                            coord
                        )
                    }
                }
            }
        } catch (e: IOException) {
            return City()//city of london
        }
        return City()//city of london
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                    Toast.makeText(this, "User interaction was cancelled.", Toast.LENGTH_LONG)
                        .show()
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {//PERMISSION_GRANTED
                    // Permission granted.
                    getLastLocation()
                }
                else -> {//denied
                    city = City()//city of london
                    Toast.makeText(this, city.name, Toast.LENGTH_LONG).show()
                    mViewModel.saveCurrentLocationCity(city)
                }
            }
        }
    }

    companion object {
        private val TAG = "LocationProvider"
        private val REQUEST_PERMISSIONS_REQUEST_CODE = 340
    }

    override fun onCityClicked(city: City) {
        startActivity(Intent(this, FiveDayForecastActivity::class.java).putExtra(FiveDayForecastActivity.KEY,city))
    }

    override fun onCityDelete(city: City) {
        TODO("Not yet implemented")
    }
}