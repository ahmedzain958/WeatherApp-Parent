package com.parents.weatherapp.domain.model.fivedayforecast

import com.google.gson.annotations.SerializedName

data class FiveDayForecast(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    @SerializedName("list")
    val list: List<ListItem>? = null,
    val message: Int? = null
)