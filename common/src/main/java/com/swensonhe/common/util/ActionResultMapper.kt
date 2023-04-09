package com.swensonhe.common.util

import com.swensonhe.common.R
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.WeatherException
import retrofit2.Response

internal fun <T> mapResponse(response: Response<T>): ActionResult<T> = when (response.code()) {
    ResponseCodes.AUTHENTICATION_ERROR_CODE -> {
        ActionResult.Error(
            WeatherException(
                messageResId = R.string.toast_authentication_error,
            )
        )
    }
    in ResponseCodes.SERVER_ERROR_RESPONSE_RANGE -> {
        ActionResult.Error(
            WeatherException(
                messageResId = R.string.toast_server_error,
            )
        )
    }
    in ResponseCodes.CLIENT_ERROR_RESPONSE_RANGE -> {
        ActionResult.Error(WeatherException(messageResId = R.string.toast_client_error))
    }
    in ResponseCodes.INFO_RESPONSE_RANGE -> {
        ActionResult.Error(WeatherException(messageResId = R.string.toast_informational_error))
    }
    in ResponseCodes.REDIRECT_RESPONSE_RANGE -> {
        ActionResult.Error(WeatherException(messageResId = R.string.toast_redirection_error))
    }
    in ResponseCodes.SUCCESS_RESPONSE_RANGE -> {
        response.body()?.let {
            ActionResult.Success(it)
        } ?: ActionResult.Error(WeatherException(message = "No Data Found."))
    }
    else -> ActionResult.Error(
        WeatherException(
            messageResId = R.string.toast_unknown_error,
        )
    )
}
