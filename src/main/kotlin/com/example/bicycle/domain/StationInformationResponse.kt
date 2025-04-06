package com.example.bicycle.domain

/**
 * @author Henrik Solberg
 * */
data class StationInformationResponse(
    val last_updated: Long,
    val ttl: Int,
    val version: String,
    val data: StationInformationData
)

data class StationInformationData(
    val stations: List<StationInformation>
)

data class StationInformation(
    val station_id: String,
    val name: String,
    val address: String,
    val cross_street: String,
    val lat: Double,
    val lon: Double,
    val is_virtual_station: Boolean,
    val capacity: Int,
    val rental_uris: RentalUris,
    val station_area: StationArea?,
    // not part of information vertical endpoint contract but derived from StationStatus vertical.
    var num_vehicles_available: Int? = null,
)

data class RentalUris(
    val android: String,
    val ios: String
)

data class StationArea(
    val type: String,
    val coordinates: List<List<List<List<Double>>>> // Nested lists for coordinates
)