package com.orlando.androidbase.presentation.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orlando.androidbase.presentation.helpers.NetworkHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import com.orlando.data.di.CoroutineDispatchers
import com.orlando.androidbase.state.Result


abstract class BaseViewModel(
    protected val coroutineDispatchers: CoroutineDispatchers,
    val networkHelper: NetworkHelper
) : ViewModel() {

    enum class ErrorType {
        NETWORK,
        TIMEOUT,
        UNKNOWN
    }

    suspend inline fun <T> safeApiCall(
        result: MutableLiveData<Result<T>>,
        coroutineDispatchers: CoroutineDispatchers,
        crossinline apiToCall: suspend () -> Unit,
    ) {
        viewModelScope.launch(coroutineDispatchers.io) {
            try {
                withContext(coroutineDispatchers.main) {
                    result.value = Result.Loading
                }
                if (!networkHelper.isNetworkConnected()) {
                    result.value = Result.ErrorNetwork("")
                    return@launch
                }
                apiToCall()
                withContext(coroutineDispatchers.main) {
                    result.value = null
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    e.printStackTrace()
                    Log.e("ApiCalls", "Call error: ${e.localizedMessage} code:$e", e.cause)
                    when (e) {
                        is HttpException -> {
                            val errorBody = e.response()?.errorBody()
                            val errorCode = e.response()?.code()
                            result.value = Result.Error(
                                error = e.message(),
                                errorCode = errorCode ?: -1,
                                errorBody = errorBody.toString()
                            )
                            Log.w("Call error :", "code:$errorCode")
                        }

                        is SocketTimeoutException -> result.value =
                            Result.Error(ErrorType.TIMEOUT.name)

                        is IOException -> result.value = Result.Error(ErrorType.NETWORK.name)
                        else -> result.value = Result.Error(ErrorType.UNKNOWN.name)
                    }
                }
            }
        }
    }


}



