package com.parents.weatherapp.presentation.fivedayforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.ErrorModel
import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast
import com.parents.weatherapp.domain.usecase.base.UseCaseResponse
import com.parents.weatherapp.domain.usecase.fivedayforecast.GetFiveDayForecastUseCase
import com.parents.weatherapp.presentation.base.BaseViewModel

/**
 * Created by Ahmed Zain on 8/4/2020.
 */
class FiveDayForecastViewModel constructor(
    private val getFiveDayForecastUseCase: GetFiveDayForecastUseCase
) : BaseViewModel() {

    val fiveDayForecast: LiveData<FiveDayForecast>
        get() = _fiveDayForecast
    private val _fiveDayForecast by lazy { MutableLiveData<FiveDayForecast>() }

    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar
    private val _showProgressbar by lazy { MutableLiveData<Boolean>() }

    val messageData: LiveData<String>
        get() = _messageData
    private val _messageData by lazy { MutableLiveData<String>() }
    fun getFiveDayForecast(city: City) {
        _showProgressbar.value = true
        getFiveDayForecastUseCase.invoke(
            GetFiveDayForecastUseCase.Params(city.coord!!.lat, city.coord.lon),
            object : UseCaseResponse<FiveDayForecast> {
                override fun onSuccess(result: FiveDayForecast) {
                    _fiveDayForecast.value = result
                    _showProgressbar.value = false
                }

                override fun onError(errorModel: ErrorModel?) {
                    _messageData.value = errorModel?.message
                    _showProgressbar.value = false
                }
            })
    }
}