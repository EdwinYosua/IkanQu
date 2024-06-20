package com.capstoneapp.ikanqu.network.response

import android.gesture.Prediction
import com.google.gson.annotations.SerializedName

data class ModelResponse(

    @field:SerializedName("error")
    val error: String? = null,

    @SerializedName("prediction")
    val prediction: Prediction
)
