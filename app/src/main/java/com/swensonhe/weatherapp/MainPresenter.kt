package com.swensonhe.weatherapp

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.swensonhe.common.di.IsOnTablet
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.common.entities.WeatherException
import com.swensonhe.common.ui.BasePresenter
import com.swensonhe.common.ui.DEFAULT_QUERY_DELAY
import com.swensonhe.weatherapp.domain.usecase.datetime.DateTimeUseCase
import com.swensonhe.weatherapp.domain.usecase.search.SearchUseCase
import com.swensonhe.weatherapp.domain.usecase.weather.ForecastUseCase
import com.swensonhe.weatherapp.model.Coordinates
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val owner: LifecycleOwner,
    private val searchUseCase: SearchUseCase,
    private val forecastUseCase: ForecastUseCase,
    private val dateTimeUseCase: DateTimeUseCase,
    @IsOnTablet val isOnTablet: Boolean,
) : BasePresenter<MainContracts.View>(), MainContracts.Presenter {

    private var searchJob: Job? = null
    private var forecastJob: Job? = null

    private val shouldShowSearchPanel = MutableStateFlow(false)
    private val currentLocationCoordinates = MutableStateFlow(DEFAULT_LOCATION_COORDINATES)
    private val searchList = MutableStateFlow<List<Location>>(emptyList())

    override fun start() {
        collectorJobs += owner.collectWithLifecycle(
            currentLocationCoordinates,
            collector = ::fetchForecast
        )
        collectorJobs += owner.collectWithLifecycle(searchList) {
            view?.updateSearchResults(it)
        }
        collectorJobs += owner.collectWithLifecycle(error) { error ->
            if (error == null) return@collectWithLifecycle
            error.messageResId?.let {
                view?.showError(it)
                return@collectWithLifecycle
            }
            error.message?.let {
                view?.showError(it)
                return@collectWithLifecycle
            }
        }
        collectorJobs += owner.collectWithLifecycle(shouldShowSearchPanel) { should ->
            if (should) {
                showSearchPanel()
            } else {
                hideSearchPanel()
            }
        }
    }

    override fun detachView() {
        super.detachView()
        searchJob?.cancel()
        searchJob = null
        forecastJob?.cancel()
        forecastJob = null
    }

    override fun searchLocation(query: String?) {
        if (query.isNullOrEmpty() || query.isBlank()) {
            view?.hideSearchProgress()
            view?.emptySearchList()
            return
        }

        if (searchJob?.isActive == true)
            searchJob?.cancel()

        view?.showSearchProgress()
        searchJob = owner.lifecycleScope.launch {
            delay(DEFAULT_QUERY_DELAY)
            when (val result = searchUseCase.searchCity(query)) {
                is ActionResult.Error -> {
                    updateError(result.e)
                    view?.hideSearchProgress()
                }
                is ActionResult.Failure -> {
                    updateError(result.e.message?.let { WeatherException(message = it) })
                    Log.e(TAG, "searchLocation: ", result.e)
                    view?.hideSearchProgress()
                }
                is ActionResult.Success -> {
                    searchList.update { result.data }
                    view?.hideSearchProgress()
                }
            }
        }
    }

    override fun updateCurrentLocation(locationCoordinates: Coordinates) {
        currentLocationCoordinates.update { locationCoordinates }
    }

    private fun hideSearchPanel() {
        view?.emptySearchList()
        view?.hideSearchPanel()
        if (isOnTablet) {
            view?.showSearchIcon()
        }
        searchJob?.cancel()
        searchList.update { emptyList() }
    }

    private fun showSearchPanel() {
        if (isOnTablet) {
            view?.hideSearchIcon()
        }
        view?.initSearchPanel()
    }

    override fun setShouldShowSearchPanel(should: Boolean) {
        shouldShowSearchPanel.update { should }
    }

    /**
     * Will be called only on table mode.
     * */
    override fun reverseSearchPanelVisibility() {
        shouldShowSearchPanel.update { !shouldShowSearchPanel.value }
    }

    private fun fetchForecast(location: Coordinates) {
        forecastJob?.cancel()
        view?.showProgress()
        forecastJob = owner.lifecycleScope.launch {
            when (val result = forecastUseCase.getLocationForecast(
                location.lat,
                location.lon,
                3
            )) {
                is ActionResult.Error -> {
                    updateError(result.e)
                    view?.hideProgress()
                }
                is ActionResult.Failure -> {
                    updateError(result.e.message?.let { WeatherException(message = it) })
                    result.e.printStackTrace()
                    view?.hideProgress()
                }
                is ActionResult.Success -> {
                    view?.hideProgress()
                    view?.setForecast(
                        result.data,
                        dateTimeUseCase.getTodayDate(Locale.getDefault())
                    )
                }
            }
        }
    }

    companion object {
        val TAG: String = MainPresenter::class.java.simpleName
    }
}