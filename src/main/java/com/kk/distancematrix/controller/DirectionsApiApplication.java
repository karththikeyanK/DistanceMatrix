package com.kk.distancematrix.controller;

import com.kk.distancematrix.dto.LocationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class DirectionsApiApplication {
    @Value("${google.api.key}")
    private String googleApiKey;
    private final RestTemplate restTemplate;

    public DirectionsApiApplication(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/getOptimizedPath")
    public String getOptimizedPath(@RequestBody List<LocationRequest> locationRequests) {
        String baseUrl = "https://maps.googleapis.com/maps/api/directions/json";
        String apiKey = googleApiKey; // Replace with your actual Google Maps API key
        String waypoints = generateWaypoints(locationRequests); // Function to generate waypoints string

        String url = String.format("%s?origin=6.9355,79.8487&destination=6.9146,79.8591&waypoints=%s&key=%s",
                baseUrl, waypoints, apiKey);

        // Making a GET request to the Google Maps Directions API
        String response = restTemplate.getForObject(url, String.class);




        return response;
    }

    private String generateWaypoints(List<LocationRequest> locationRequests) {
        // Assuming you have your waypoints data in a list of objects or some format
        // Here is an example of generating the waypoints string from your given input format
        StringBuilder waypointsBuilder = new StringBuilder();
        waypointsBuilder.append("optimize:true|"); // Set optimize:true to get the optimized route

        // Your list of waypoints in the given format
        List<Map<String, Object>> waypointsList = getWaypointsData(locationRequests); // Replace this with your method to fetch waypoints data

        // Construct the waypoints string
        for (Map<String, Object> waypoint : waypointsList) {
            double latitude = (double) waypoint.get("latitude");
            double longitude = (double) waypoint.get("longitude");
            waypointsBuilder.append(String.format("%f,%f|", latitude, longitude));
        }

        return waypointsBuilder.toString();
    }

    // This method is just an example; you need to replace it with your logic to get waypoints data
    private List<Map<String, Object>> getWaypointsData(List<LocationRequest> locationRequests) {
        // Replace this example with your logic to fetch waypoints data from your source
        // For this example, I'll use a hard-coded list similar to your input format
        List<Map<String, Object>> waypointsList = new ArrayList<>();

        // Add waypoints similar to your provided input format
        // (Replace these with your actual waypoints data)
        // Here is an example for demonstration purposes:
        for (LocationRequest locationRequest : locationRequests) {
            Map<String, Object> waypoint = new HashMap<>();
            waypoint.put("latitude", locationRequest.getLatitude());
            waypoint.put("longitude", locationRequest.getLongitude());
            waypointsList.add(waypoint);
        }

        return waypointsList;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

