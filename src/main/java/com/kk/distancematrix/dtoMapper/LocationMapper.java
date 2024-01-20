package com.kk.distancematrix.dtoMapper;


import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.kk.distancematrix.dto.LocationRequest;
import com.kk.distancematrix.dto.LocationSingleResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationMapper {

    public static List<LatLng> convertToLatLng(List<LocationRequest> locationRequestList){
        List<LatLng> latLngList = new ArrayList<>();
        for (LocationRequest locationRequest : locationRequestList) {
            LatLng latLng = new LatLng();
            latLng.lat = locationRequest.getLatitude();
            latLng.lng = locationRequest.getLongitude();
            latLngList.add(latLng);
        }
        return latLngList;
    }
    
    public List<LocationSingleResponse> convertToLocationSingleResponseList(List<Integer> minPath, DistanceMatrix distanceMatrix){
        List<LocationSingleResponse> locationSingleResponseList = new ArrayList<>();
        for (int i = 0; i < minPath.size()-1; i++) {
            if (i<minPath.size()-2){
                LocationSingleResponse locationSingleResponse = getLocationSingleResponse(minPath, distanceMatrix, i);
                locationSingleResponseList.add(locationSingleResponse);
            }else {
                LocationSingleResponse locationSingleResponse = getSingleResponse(minPath, distanceMatrix, i);
                locationSingleResponseList.add(locationSingleResponse);
            }
        }
        return locationSingleResponseList;
    }

    @NotNull
    private static LocationSingleResponse getSingleResponse(List<Integer> minPath, DistanceMatrix distanceMatrix, int i) {
        LocationSingleResponse locationSingleResponse = new LocationSingleResponse();
        locationSingleResponse.setStopSequenceNumber(i + 1);
        locationSingleResponse.setStartId(minPath.get(i));
        locationSingleResponse.setDestinationId(minPath.get(0));
        locationSingleResponse.setDistance((int) (distanceMatrix.rows[i].elements[0].distance.inMeters));
        locationSingleResponse.setDuration((int) (distanceMatrix.rows[i].elements[0].duration.inSeconds));
        return locationSingleResponse;
    }

    @NotNull
    private static LocationSingleResponse getLocationSingleResponse(List<Integer> minPath, DistanceMatrix distanceMatrix, int i) {
        LocationSingleResponse locationSingleResponse = new LocationSingleResponse();
        locationSingleResponse.setStopSequenceNumber(i + 1);
        locationSingleResponse.setStartId(minPath.get(i));
        locationSingleResponse.setDestinationId(minPath.get(i + 1));
        locationSingleResponse.setDistance((int) (distanceMatrix.rows[i].elements[i + 1].distance.inMeters));
        locationSingleResponse.setDuration((int) (distanceMatrix.rows[i].elements[i + 1].duration.inSeconds));
        return locationSingleResponse;
    }


}
