import com.google.gson.annotations.SerializedName

// Data classes
data class ModelResponse(
    val error: String,
    val prediction: Prediction

)

data class Prediction(
    @SerializedName("class") val clazz: String
)

