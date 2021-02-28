package app.erickalvz.data.remote.models


import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CollectionDto<T>(
    val status: String,
    val totalResults: Int,
    val articles: List<T>
)