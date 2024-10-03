package com.orlando.androidbase.presentation.extensions

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.orlando.androidbase.presentation.alerts.MainAlert
import com.orlando.androidbase.presentation.alerts.MainAlert.Companion.ERROR_MESSAGE
import com.orlando.androidbase.presentation.features.MainActivity
import com.orlando.androidbase.state.Result
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.ToolbarViewBinding


fun Fragment.showLog(message: String, tag: String = javaClass.name) {
    Log.w(tag, message)
}


fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}

fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}

fun Fragment.shouldShowProgress(isLoading: Boolean) {
    (requireActivity() as MainActivity).shouldShowProgress(isLoading)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}


fun Fragment.navigate(action: NavDirections) {
    findNavController().navigate(action)
}


fun Fragment.showErrorApi(shouldCloseTheViewOnApiError: Boolean = false) {
    val dialog = MainAlert(kindOfMessage = ERROR_MESSAGE,
        messageBody = getString(R.string.error_service),
        clickOnAccept = {
            if (shouldCloseTheViewOnApiError) {
                findNavController().popBackStack()
            }
        })
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}

fun Fragment.showErrorNetwork(shouldCloseTheViewOnApiError: Boolean = false) {
    val dialog = MainAlert(kindOfMessage = ERROR_MESSAGE,
        messageBody = getString(R.string.verifica_conexion),
        clickOnAccept = {
            if (shouldCloseTheViewOnApiError) {
                findNavController().popBackStack()
            }
        })
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}


fun Fragment.configure(toolbar: ToolbarViewBinding, title: String = "") {
    toolbar.apply {
        toolbarBack.setOnClickListener {
            findNavController().popBackStack()
        }
        toolbarTitle.text = title
    }
}

fun <T> LiveData<T>.observeAsEvent(owner: LifecycleOwner, observer: Observer<in T>) {
    var previousKey: Any? = value ?: NULL
    observe(owner) { value ->
        if (previousKey == NULL || previousKey != value) {
            previousKey = value
            observer.onChanged(value)
        }
    }
}

private const val NULL = "NULL"


fun <T> Fragment.observeApiResult(
    liveData: LiveData<Result<T>>?,
    onLoading: () -> Unit = { },
    onFinishLoading: () -> Unit = { },
    haveTheViewProgress: Boolean = true,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: (() -> Unit)? = null,
    noData: () -> Unit = {},
    onSuccess: (data: T) -> Unit,
) {
    liveData?.observe(viewLifecycleOwner) { apiState ->
        fun handleStatusOnLoading(isLoading: Boolean) {
            if (isLoading) {
                onLoading()
            } else {
                onFinishLoading()
            }
        }

        val isLoading = apiState is Result.Loading
        if (haveTheViewProgress) {
            shouldShowProgress(isLoading)
        } else {
            handleStatusOnLoading(isLoading)
        }
        when (apiState) {
            is Result.Success -> {
                if (apiState.data != null) {
                    onSuccess(apiState.data)
                }
            }

            is Result.Error -> {
                if (onError == null) {
                    showErrorApi(shouldCloseTheViewOnApiError)
                } else {
                    onError()
                }
            }

            is Result.ErrorNetwork -> {
                showErrorNetwork(shouldCloseTheViewOnApiError)
            }

            is Result.EmptyList -> {
                noData()
            }

            else -> {}
        }
    }
}
