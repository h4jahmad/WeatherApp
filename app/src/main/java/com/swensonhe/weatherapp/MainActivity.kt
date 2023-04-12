package com.swensonhe.weatherapp

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.swensonhe.common.ui.BaseActivity
import com.swensonhe.common.ui.BindingInitializer
import com.swensonhe.common.ui.showSnackbar
import com.swensonhe.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), MainContracts.View {

    override val bindingInitializer: BindingInitializer
        get() = ActivityMainBinding::inflate

    @Inject
    lateinit var presenter: MainContracts.Presenter
    private val listAdapter: ForecastAdapter = ForecastAdapter()

    override fun initViews() {
        binding.activityMainForecastList.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = listAdapter
        }

        presenter.attachView(this)
        presenter.start()

        binding.activityMainSearchAction.setOnClickListener {
            binding.layoutSearch.root.isVisible = true
        }
        binding.layoutSearch.searchActionClose.setOnClickListener {
            binding.layoutSearch.root.isVisible = false
        }
        binding.layoutSearch.searchLayoutClosePaneAction.setOnClickListener {
            binding.layoutSearch.root.isVisible = false
        }
    }

    private fun initCollectors() {
        collectWithLifecycle(presenter.observeError()) { error ->
            if (error != null) {
                binding.root.showSnackbar(error.messageResId, error.message)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}