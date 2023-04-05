package com.swensonhe.weatherapp

import com.swensonhe.common.ui.BasePresenterContract
import com.swensonhe.common.ui.BaseViewContract

interface MainContracts {

    interface View : BaseViewContract<Presenter> {

    }

    interface Presenter : BasePresenterContract<View> {

    }
}