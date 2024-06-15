package com.capstoneapp.ikanqu.network

sealed class ApiResult<out R> private constructor() {
    data class ApiSuccess<out T>(val data: T) : ApiResult<T>()
    data class ApiError(val error: String) : ApiResult<Nothing>()
    data object ApiLoading : ApiResult<Nothing>()
}