package com.orlando.androidbase.state

sealed class Result<out R> {

    class Success<out T>(val data: T) : Result<T>()
    class Error<out T>(val error: String, val errorCode: Int = -1,val errorBody:String="") : Result<T>()
    class ErrorNetwork<out T>(val error: String) : Result<T>()
    data object Loading : Result<Nothing>()
    data object EmptyList : Result<Nothing>()

}
