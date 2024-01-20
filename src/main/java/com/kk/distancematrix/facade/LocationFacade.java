package com.kk.distancematrix.facade;


import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.kk.distancematrix.dto.LocationRequest;
import com.kk.distancematrix.dto.LocationResponse;
import com.kk.distancematrix.dto.LocationSingleResponse;
import com.kk.distancematrix.dtoMapper.LocationMapper;
import com.kk.distancematrix.exception.GeneralRequestAndResponseException;
import com.kk.distancematrix.service.DistanceMatrixService;
import com.kk.distancematrix.service.TSMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LocationFacade {
    private final DistanceMatrixService distanceMatrixService;
    private final TSMService tsmService;
    private final LocationMapper locationMapper;

    public LocationResponse getEfficientPath(List<LocationRequest> locationRequestList){
        log.info("LocationFacade:: getDistanceMatrix():: called");
        List<LatLng> origins = LocationMapper.convertToLatLng(locationRequestList);
        List<LatLng> destinations = LocationMapper.convertToLatLng(locationRequestList);
        DistanceMatrix distanceMatrix = distanceMatrixService.getGoogleDistanceMatrixResponse(origins, destinations);
        if (distanceMatrix == null){
            log.error("LocationFacade:: getDistanceMatrix():: distanceMatrix is null");
            throw new GeneralRequestAndResponseException("Google Map Api Return Null DistanceMatrix" );
        }
        log.info("LocationFacade:: getDistanceMatrix():: completed");
        return getLocationResponse(distanceMatrix, locationRequestList);
    }


    public LocationResponse getLocationResponse(DistanceMatrix distanceMatrix, List<LocationRequest> locationRequestList){
        log.info("LocationFacade:: getLocationResponse():: called");
        DirectionsResult directionsResult = null;
        int[][] graph = distanceMatrixService.createGraph(distanceMatrix);
        log.info("LocationFacade:: getLocationResponse():: graph created");
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;

        List<Integer> path = new ArrayList<>();
        List<Integer> minPath = new ArrayList<>();
        path.add(0);

        log.info("LocationFacade:: getLocationResponse():: TSMService.tsp() called");
        int minCost = tsmService.tsp(graph, visited, 0, n, 1, 0, Integer.MAX_VALUE, path, minPath);
        log.info("LocationFacade:: getLocationResponse():: TSMService.tsp() completed");


        LocationResponse locationResponse = new LocationResponse();
        log.info("LocationFacade:: getLocationResponse():: LocationResponse creating started");
//        minPath.replaceAll(integer -> integer + 1);
        minPath = getActualPath(minPath,locationRequestList);

        List<LocationSingleResponse> locationSingleResponseList = locationMapper.convertToLocationSingleResponseList(minPath, distanceMatrix);

        locationResponse.setTotalDistance(minCost);
        for (LocationSingleResponse locationSingleResponse : locationSingleResponseList) {
            locationResponse.setTotalTime(locationResponse.getTotalTime() + locationSingleResponse.getDuration());
        }

        locationResponse.setPath(minPath);
        locationResponse.setLocationResponseList(locationSingleResponseList);

        log.info("LocationFacade:: getLocationResponse():: LocationResponse created");
        return locationResponse;

    }

    private List<Integer> getActualPath(List<Integer> minPath, List<LocationRequest> locationRequestList){
        List<Integer> actualPath = new ArrayList<>();
        for (int path : minPath){
            log.info("LocationFacade:: getActualPath():: path --> {}", path);
            log.info("LocationFacade:: getActualPath():: locationRequestList.get(path).getId() --> {}", locationRequestList.get(path).getId());
            actualPath.add(locationRequestList.get(path).getId());
        }
        return actualPath;
    }



    public DistanceMatrix getDistanceMatrix(List<LocationRequest> locationRequestList){
        log.info("LocationFacade:: getDistanceMatrix():: called");
        List<LatLng> origins = LocationMapper.convertToLatLng(locationRequestList);
        List<LatLng> destinations = LocationMapper.convertToLatLng(locationRequestList);
        DistanceMatrix distanceMatrix = distanceMatrixService.getGoogleDistanceMatrixResponse(origins, destinations);
        log.info("LocationFacade:: getDistanceMatrix():: completed");
        return distanceMatrix;

    }



}
