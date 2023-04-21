package com.swensonhe.weatherapp

import androidx.annotation.StringRes
import com.swensonhe.common.entities.Location
import com.swensonhe.common.ui.BasePresenterContract
import com.swensonhe.common.ui.BaseViewContract
import com.swensonhe.weatherapp.model.Coordinates
import com.swensonhe.weatherapp.model.UiForecast

interface MainContracts {

    interface View : BaseViewContract<Presenter> {
        fun showSearchProgress()
        fun hideSearchProgress()
        fun updateSearchResults(locations: List<Location>)
        fun showError(@StringRes messageResId: Int)
        fun showError(message: String)
        fun emptySearchList()
        fun hideSearchPanel()
        fun initSearchPanel()
        fun showProgress()
        fun hideProgress()
        fun setForecast(data: UiForecast, currentDate: String)
    }

    interface Presenter : BasePresenterContract<View> {
        fun searchLocation(query: String?)
        fun updateCurrentLocation(locationCoordinates: Coordinates)
        fun closeSearchPanel()
        fun showSearchPanel()

    }
}