package com.example.bicycle.service

import com.example.bicycle.domain.StationInformation
import com.example.bicycle.domain.StationInformationResponse
import com.example.bicycle.domain.StationStatus
import com.example.bicycle.domain.StationStatusResponse
import com.example.bicycle.generator.MapHtmlGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.stereotype.Service

/**
 * Responsible for communicating with public api for retrieval of bicycle stations.
 * Uses OkHttpClient for performing REST call.
 * Using jackson object mapper for deserialization.
 * */
@Service
class BicycleService {
    private val client = OkHttpClient()
    private val mapper = ObjectMapper().registerModule(KotlinModule())

    /**
     * Deliver station information to frontend.
     * */
    fun lookupStationInformation(): List<StationInformation> {
        val url = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json"
        val response = clientCall(url)
        val responseBody = response.body?.string() ?: throw Exception("Error calling endpoint $url")
        val deserialized = mapper.readValue(responseBody, StationInformationResponse::class.java)
        return deserialized.data.stations.map { it }
    }

    fun lookupStationStatus(): Map<String, StationStatus> {
        val url = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json"
        val response = clientCall(url)
        val responseBody = response.body?.string() ?: throw Exception("Error calling endpoint $url")
        val deserialized = mapper.readValue(responseBody, StationStatusResponse::class.java)
        return deserialized.data.stations.associateBy { it.station_id }
    }

    /**
     * Returns a html page with marker for all stations.
     * */
    fun generateHtmlMapInfo(): String {
        val stationsInformation = lookupStationInformation()
        val stationsStatus = lookupStationStatus()
        applyStatusToStationsInformation(stationsInformation, stationsStatus)
        return MapHtmlGenerator().generateGoogleMapsWebPage(stationsInformation)
    }

    private fun applyStatusToStationsInformation(
        stationsInformation: List<StationInformation>,
        stationsStatus: Map<String, StationStatus>
    ) {
        stationsInformation.forEach { stationInformation ->
            val stationStatus = stationsStatus[stationInformation.station_id]
            if (stationStatus != null) {
                stationInformation.num_vehicles_available = stationStatus.num_vehicles_available
            }
        }
    }

    private fun clientCall(url: String): Response {
        val request = Request.Builder()
            .url(url)
//            .header("HttpHeaders.Client-Identifier", "Henrik Solberg - kodeoppgave")
            .build()
        return client.newCall(request).execute()
    }
}