package com.swensonhe.common.ui

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

typealias BindingInitializer = (LayoutInflater) -> ViewBinding

abstract class BaseActivity<V : ViewBinding, P: BasePresenter<*>> : AppCompatActivity() {
    private var _binding: V? = null
    protected val binding: V
        get() = _binding ?: throw IllegalStateException("ViewBinding is not initialized")
    abstract val bindingInitializer: BindingInitializer

    abstract fun initViews()

    abstract val presenter: P

    protected inline fun <reified T> collectWithLifecycle(
        flow: Flow<T>,
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline collector: suspend (T) -> Unit,
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(lifecycleState) {
                flow.collectLatest { collector(it) }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.detachView()
    }
}