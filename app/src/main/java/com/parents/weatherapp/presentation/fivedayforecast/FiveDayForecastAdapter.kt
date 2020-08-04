package com.parents.weatherapp.presentation.fivedayforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.parents.weatherapp.R
import com.parents.weatherapp.databinding.HolderFivedayforecastBinding
import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast
import com.parents.weatherapp.domain.model.fivedayforecast.ListItem

/**
 * Created by Ahmed Zain on 8/4/2020.
 */
class FiveDayForecastAdapter(private val fiveDayForecast: FiveDayForecast) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderCityBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_fivedayforecast, parent, false
        )
        return FiveDayForecastViewHolder(holderCityBinding)
    }

    override fun getItemCount(): Int {
        return if (fiveDayForecast.list.isNullOrEmpty()) 0 else fiveDayForecast.list.size
    }

    private fun getItem(position: Int): ListItem {
        return fiveDayForecast.list!![position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FiveDayForecastViewHolder).onBind(getItem(position))
    }

    inner class FiveDayForecastViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun onBind(weatherItem: ListItem) {
            val holderFivedayforecastBinding = viewDataBinding as HolderFivedayforecastBinding
            holderFivedayforecastBinding.weatherItem = weatherItem
        }
    }
}