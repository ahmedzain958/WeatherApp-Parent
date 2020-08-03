package com.parents.weatherapp.presentation.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.parents.weatherapp.R
import com.parents.weatherapp.databinding.HolderCityBinding
import com.parents.weatherapp.domain.model.City
import kotlin.properties.Delegates

class CitiesAdapter(
    var onCityClickListener: OnCityClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mCitiesList: List<City> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    interface OnCityClickListener {
        fun onCityClicked(city: City)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderCityBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_city, parent, false
        )
        return CityViewHolder(holderCityBinding)
    }

    override fun getItemCount(): Int {
        return if (mCitiesList.isNullOrEmpty()) 0 else mCitiesList.size
    }

    private fun getItem(position: Int): City {
        return mCitiesList[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CityViewHolder).onBind(getItem(position))
    }

    inner class CityViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun onBind(city: City) {
            val holderCityBinding = viewDataBinding as HolderCityBinding
            holderCityBinding.buttonFivedayforecast.setOnClickListener {
                onCityClickListener.onCityClicked(city)
            }
            holderCityBinding.city = city
        }
    }

}