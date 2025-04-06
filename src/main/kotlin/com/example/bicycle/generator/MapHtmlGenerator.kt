package com.example.bicycle.generator

import com.example.bicycle.domain.StationInformation
import com.example.bicycle.domain.StationStatus

class MapHtmlGenerator {

    fun generateGoogleMapsWebPage(stationsInformation: List<StationInformation>): String {
        // TODO: fix problem with ' in name; skip for now
        val filteredAndSortedStations = stationsInformation.filter { !it.name.contains("'") }.sortedBy { it.name }
        val zoom = 12
        val markers = filteredAndSortedStations.joinToString(",\n") {
            """{
            position: {lat: ${it.lat}, lng: ${it.lon}},
            title: '${it.name}: totalt: ${it.capacity}, tilgjengelig: ${it.num_vehicles_available}'
        }"""
        }
        val stationsList = filteredAndSortedStations.joinToString("\n") {
            """
                <div>${it.name}: totalt: ${it.capacity}, tilgjengelig: ${it.num_vehicles_available}</div>
            """.trimIndent()
        }

        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Oslo Bysykkel</title>
                <script src="https://maps.googleapis.com/maps/api/js?key=API_KEY"></script>
                <script>
                    function initMap() {
                        var locations = [
                            $markers
                        ];
                        
                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: ${zoom},
                            center: {lat: ${filteredAndSortedStations.first().lat}, lng: ${filteredAndSortedStations.first().lon}}
                        });
        
                        locations.forEach(function(location) {
                            var marker = new google.maps.Marker({
                                position: location.position,
                                map: map,
                                title: location.title
                            });
                        });
                    }
                </script>
            </head>
            <body onload="initMap()">
                <h1>Oslo Bysykkel</h1>
                <div>
                    <div id="map" style="float:left; height: 800px; width: 70%;"></div>
                    <div style="padding-left: 1rem; overflow: auto; height: 800px;">
                    <h2>Bysykkel stasjoner</h2>
                    $stationsList
                    </div>
                <div>
            </body>
            </html>
        """.trimIndent()
    }
}


