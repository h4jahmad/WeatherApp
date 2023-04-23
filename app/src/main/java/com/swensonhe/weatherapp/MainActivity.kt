package com.swensonhe.weatherapp

import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import coil.load
import com.swensonhe.common.entities.Location
import com.swensonhe.common.ui.BaseActivity
import com.swensonhe.common.ui.BindingInitializer
import com.swensonhe.common.ui.showSnackbar
import com.swensonhe.weatherapp.databinding.ActivityMainBinding
import com.swensonhe.weatherapp.model.UiForecast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), MainContracts.View {

    override val bindingInitializer: BindingInitializer
        get() = ActivityMainBinding::inflate

    @Inject
    lateinit var presenter: MainContracts.Presenter
    private val forecastAdapter = ForecastAdapter()
    private var searchListAdapter: SearchResultAdapter? = null
    override fun initViews(): Unit = with(binding) {
        searchListAdapter = SearchResultAdapter {
            presenter.updateCurrentLocation(it)
            presenter.setShouldShowSearchPanel(false)
        }
        activityMainForecastList.apply {
            itemAnimator = DefaultItemAnimator()
            val lm = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(context, lm.orientation).apply {
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.item_divider_forecast
                )?.let(this::setDrawable)
            })
            adapter = forecastAdapter
        }

        layoutSearch.searchLayoutResultList.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = searchListAdapter
        }
        activityMainSearchAction.setOnClickListener {
            if (isOnTablet()) {
                presenter.reverseSearchPanelVisibility()
                return@setOnClickListener
            }
            presenter.setShouldShowSearchPanel(true)
        }
        layoutSearch.searchActionClose?.setOnClickListener {
            presenter.setShouldShowSearchPanel(false)
        }
        layoutSearch.searchLayoutClosePaneAction?.setOnClickListener {
            presenter.setShouldShowSearchPanel(false)
        }
    }

    override fun initPresenter() {
        presenter.attachView(this@MainActivity)
        presenter.start()
    }

    override fun initSearchPanel() = with(binding) {
        layoutSearch.root.circleReveal(activityMainSearchAction)

        layoutSearch.searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchLocation(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchLocation(newText)
                return true
            }
        })
    }

    override fun showProgress() {
        changeContentVisibility(false)
        binding.activityMainProgress.isVisible = true
    }

    override fun hideProgress() {
        changeContentVisibility(true)
        binding.activityMainProgress.isVisible = false
    }

    override fun setForecast(
        data: UiForecast,
        currentDate: String
    ): Unit = with(binding) {
        activityMainCityName.text = data.location.name
        activityMainDateLabel.text = currentDate
        activityMainCurrentWeatherImage.load(
            data.current.condition.icon
        )
        activityMainCurrentTempLabel.text = data.current.tempF.toString()
        activityMainCurrentTempUnitLabel.setText(R.string.fahrenheit_unit)
        activityMainCurrentWeatherLabel.text = getString(
            R.string.place_holder_today_condition,
            data.current.condition.text,
            getString(if (data.current.isDay) R.string.day else R.string.night)
        )
        activityMainCurrentWindLabel.text = getString(
            R.string.place_holder_wind,
            data.current.windMph
        )
        activityMainCurrentHumidityLabel.text = getString(
            R.string.place_holder_humidity,
            data.current.humidity
        )
        forecastAdapter.submitList(data.forecastDays)
    }

    override fun hideSearchIcon() = with(binding) {
        activityMainSearchAction.rotate {
            activityMainSearchAction.setImageResource(R.drawable.ic_arrow_right)
        }
    }

    override fun showSearchIcon() = with(binding) {
        activityMainSearchAction.rotate {
            activityMainSearchAction.setImageResource(R.drawable.ic_search)
        }
    }

    override fun hideSearchPanel() = with(binding.layoutSearch) {
        root.circleHide(binding.activityMainSearchAction) {
            searchInput.setQuery("", false)
            searchInput.clearFocus()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showSearchProgress() {
        binding.layoutSearch.searchLayoutClosePaneProgress.isVisible = true
    }

    override fun hideSearchProgress() {
        binding.layoutSearch.searchLayoutClosePaneProgress.isVisible = false
    }

    override fun updateSearchResults(locations: List<Location>) {
        if (locations.isNotEmpty()) {
            binding.layoutSearch.searchLayoutResultList.isVisible = true
            binding.layoutSearch.searchLayoutClosePaneAction?.isVisible = true
        } else {
            binding.layoutSearch.searchLayoutResultList.isVisible = false
            binding.layoutSearch.searchLayoutClosePaneAction?.isVisible = false
        }
        searchListAdapter?.submitList(locations)
    }

    override fun showError(messageResId: Int) {
        binding.root.showSnackbar(messageResId)
        changeContentVisibility(false)
    }

    override fun showError(message: String) {
        binding.root.showSnackbar(message = message)
    }

    override fun emptySearchList() = with(binding.layoutSearch) {
        searchListAdapter?.submitList(emptyList())
        searchLayoutResultList.isVisible = false
        searchLayoutClosePaneAction?.isVisible = false
    }

    private fun changeContentVisibility(isVisible: Boolean) = with(binding) {
        activityMainCityName.isVisible = isVisible
        activityMainDateLabel.isVisible = isVisible
        activityMainCurrentWeatherImage.isVisible = isVisible
        activityMainCurrentTempLabel.isVisible = isVisible
        activityMainCurrentTempUnitLabel.isVisible = isVisible
        activityMainCurrentWeatherLabel.isVisible = isVisible
        activityMainCurrentWindIcon.isVisible = isVisible
        activityMainCurrentWindLabel.isVisible = isVisible
        activityMainCurrentHumidityIcon.isVisible = isVisible
        activityMainCurrentHumidityLabel.isVisible = isVisible
        activityMainForecastList.isVisible = isVisible
    }

}