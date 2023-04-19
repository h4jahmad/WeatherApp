package com.swensonhe.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewbinding.ViewBinding

typealias BindingInitializer = (LayoutInflater) -> ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    private var _binding: V? = null
    protected val binding: V
        get() = _binding ?: throw IllegalStateException("ViewBinding is not initialized")
    abstract val bindingInitializer: BindingInitializer

    abstract fun initViews()
    abstract fun initCollectors()

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
        initCollectors()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}