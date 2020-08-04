package com.parents.weatherapp.presentation.fivedayforecast

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.parents.weatherapp.BR
import com.parents.weatherapp.R
import com.parents.weatherapp.databinding.ActivityFiveDayForecastBinding
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FiveDayForecastActivity :
    BaseActivity<ActivityFiveDayForecastBinding, FiveDayForecastViewModel>() {
    val mViewModel: FiveDayForecastViewModel by viewModel()
    lateinit var mViewBinding: ActivityFiveDayForecastBinding
    override val viewModel: FiveDayForecastViewModel
        get() = mViewModel
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_five_day_forecast
    private lateinit var fiveDayForecastAdapter: FiveDayForecastAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = viewDataBinding!!
        mViewBinding.progressbar.show()
        val city = intent.extras?.getParcelable<City>(KEY)
        mViewModel.getFiveDayForecast(city!!)
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
        mViewModel.fiveDayForecast.observe(this, Observer {
            fiveDayForecastAdapter = FiveDayForecastAdapter(it!!)
            mViewBinding.recyclerview.adapter = fiveDayForecastAdapter
        })
    }

    companion object {
        const val KEY = "CITY"
    }
}