package com.example.bicycle.domain

/**
 * @author Henrik Solberg
 * */
data class StationStatusResponse(
    val last_updated: Long,
    val ttl: Int,
    val version: String,
    val data: StationStatusData
)

data class StationStatusData(
    val stations: List<StationStatus>
)

data class StationStatus(
    val station_id: String,
    val is_installed: Boolean,
    val is_renting: Boolean,
    val is_returning: Boolean,
    val last_reported: Long,
    val num_vehicles_available: Int,
    val num_bikes_available: Int,
    val num_docks_available: Int,
    val vehicle_types_available: List<VehicleType>
)

data class VehicleType(
    val vehicle_type_id: String,
    val count: Int
)

