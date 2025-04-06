package com.example.bicycle.service

import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

/**
 * @author Henrik Solberg
 * */
class BicycleServiceTest {
    private val mockedOkHttpClient = mockk<OkHttpClient>()
    private val bicycleService = BicycleService()

    @Test
    fun `deserialize and check response for stations information`() {
        every { mockedOkHttpClient.newCall(any()).execute() } returns makeTestData("src/test/resources/stations_information.json")

        val response = bicycleService.lookupStationInformation()

        assertEquals(256, response.size)
    }

    @Test
    fun `deserialize and check response for station status`() {
        every { mockedOkHttpClient.newCall(any()).execute() } returns makeTestData("src/test/resources/stations_status.json")

        val response = bicycleService.lookupStationStatus()

        assertEquals(256, response.size)
    }


    private fun makeTestData(jsonFileLocation:String): Response {
        val jsonResponse = File(jsonFileLocation).readText(Charsets.UTF_8)
        val mockResponse = Response.Builder()
            .request(Request.Builder().url("https://someendpoint.json").build())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(jsonResponse.toResponseBody("application/json".toMediaType()))
            .build()
        return mockResponse
    }
}