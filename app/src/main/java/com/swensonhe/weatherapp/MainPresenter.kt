package com.swensonhe.weatherapp

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.Location
import com.swensonhe.common.entities.WeatherException
import com.swensonhe.common.ui.BasePresenter
import com.swensonhe.weatherapp.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val scope: CoroutineScope,
    private val searchUseCase: SearchUseCase,
) : BasePresenter<MainContracts.View>(), MainContracts.Presenter {

    private val _currentCity = MutableStateFlow("San Francisco")
    val currentCity = _currentCity.asStateFlow()

    private val _searchList = MutableStateFlow<List<Location>>(emptyList())
    val searchList = _searchList.asStateFlow()
    override fun observeError(): StateFlow<WeatherException?> = error

    override fun start() {
        scope.launch {
            when (val result = searchUseCase.searchCity(currentCity.value)) {
                is ActionResult.Error -> {
                    error.update { result.e }
                }
                is ActionResult.Failure -> {
                    error.update { result.e.message?.let { WeatherException(message = it) } }
                }
                is ActionResult.Success -> {

                }
            }
        }
    }

}