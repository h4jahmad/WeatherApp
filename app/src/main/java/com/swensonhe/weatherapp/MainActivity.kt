package com.swensonhe.weatherapp

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