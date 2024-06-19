package com.capstoneapp.ikanqu.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

//	@field:SerializedName("token")
//	val token: String? = null
)

data class LoginSuccess(
    val name: String?,
    val loginResponse: LoginResponse
)
