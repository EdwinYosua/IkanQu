import com.google.gson.annotations.SerializedName

// Data classes
data class ModelResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    val prediction: Prediction
)

data class Prediction(
    @SerializedName("class") val clazz: String
)

