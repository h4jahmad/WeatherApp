package com.swensonhe.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

typealias BindingInitializer = (LayoutInflater) -> ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    private var _binding: V? = null
    protected val binding: V
        get() = _binding ?: throw IllegalStateException("ViewBinding is not initialized")
    abstract val bindingInitializer: BindingInitializer

    abstract fun initViews()
    abstract fun initPresenter()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(
            bindingInitializer(layoutInflater).also {
                _binding = it as? V
            }.root
        )
        initViews()
        lifecycleScope.launch {
            delay(300)
            initPresenter()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}