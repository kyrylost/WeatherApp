package kyrylost.apps.weatherapp.model

import com.google.gson.annotations.SerializedName

data class PossibleCitiesList(
    val _embedded: Embedded,
    val _links: Links,
    val count: Int
) {
    data class Embedded(
        @SerializedName("city:search-results") val citySearchResults: List<CitySearchResult>
    ) {
        data class CitySearchResult(
            val _links: Links,
            val matching_alternate_names: List<MatchingAlternateName>,
            val matching_full_name: String
        ) {
            data class Links(
                @SerializedName("city:item") val cityItem: CityItem
            ) {
                data class CityItem(
                    val href: String
                )
            }

            data class MatchingAlternateName(
                val name: String
            )
        }
    }

    data class Links(
        val curies: List<Cury>,
        val self: Self
    ) {
        data class Cury(
            val href: String,
            val name: String,
            val templated: Boolean
        )

        data class Self(
            val href: String
        )
    }
}