import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

// Data classes
data class ModelResponse(
    val error: String
)

data class PredictionResponse(
    val prediction: Prediction
)

data class Prediction(
    @SerializedName("class") val clazz: String
)

fun main() {
    val gson = Gson()

    // Example JSON strings
    val errorJson = """{"error": "No image uploaded for prediction."}"""
    val predictionJson1 = """{"prediction": {"class": "Viral diseases White tail disease"}}"""
    val predictionJson2 = """{"prediction": {"class": "Bacterial diseases - Aeromoniasis"}}"""

    // Parsing JSON strings
    val modelResponse: ModelResponse = gson.fromJson(errorJson, ModelResponse::class.java)
    val predictionResponse1: PredictionResponse =
        gson.fromJson(predictionJson1, PredictionResponse::class.java)
    val predictionResponse2: PredictionResponse =
        gson.fromJson(predictionJson2, PredictionResponse::class.java)

    // Print parsed objects
    println(modelResponse)
    println(predictionResponse1)
    println(predictionResponse2)
}
