package com.example.bicycle.rest

import com.example.bicycle.service.BicycleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BicycleRestController {
    private val bicycleService = BicycleService()

    @GetMapping("/oslo-bysykkel")
    fun generateHtml(): String {
        return bicycleService.generateHtmlMapInfo()
    }
}