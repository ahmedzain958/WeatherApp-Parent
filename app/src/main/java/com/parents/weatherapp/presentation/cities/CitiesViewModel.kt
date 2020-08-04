package com.parents.weatherapp.presentation.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.ErrorModel
import com.parents.weatherapp.domain.usecase.base.UseCaseResponse
import com.parents.weatherapp.domain.usecase.cities.SaveCurrentLocationCityUseCase
import com.parents.weatherapp.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Ahmed Zain on 8/3/2020.
 */
@ExperimentalCoroutinesApi
class CitiesViewModel constructor(
    private val saveCurrentLocationCityUseCase: SaveCurrentLocationCityUseCase
) : BaseViewModel() {
    val cities: LiveData<List<City>>
        get() = _cities
    private val _cities by lazy { MutableLiveData<List<City>>() }

    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar
    private val _showProgressbar by lazy { MutableLiveData<Boolean>() }

    val messageData: LiveData<String>
        get() = _messageData
    private val _messageData by lazy { MutableLiveData<String>() }

    fun saveCurrentLocationCity(city: City) {
        _showProgressbar.value = true
        saveCurrentLocationCityUseCase.invoke(
            SaveCurrentLocationCityUseCase.Params(city), object : UseCaseResponse<List<City>> {
                override fun onSuccess(result: List<City>) {
                    _cities.value = result
                    if (result.isNotEmpty())
                        _showProgressbar.value = false
                }

                override fun onError(errorModel: ErrorModel?) {
                    _messageData.value = errorModel?.message
                    _showProgressbar.value = false
                }
            })
    }

}