package com.kk.distancematrix.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class DistanceMatrixService {
    @Value("${google.api.key}")
    private String googleApiKey;
   public DistanceMatrix getGoogleDistanceMatrixResponse(List<LatLng> origins, List<LatLng> destinations) {
       try {
           log.info("DistanceMatrixService::getDistanceMatrix() called");
           String apiKey = googleApiKey;
           GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
           log.info("DistanceMatrixService::DistanceMatrixApiRequest called to get distance matrix from google");
           DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                   .origins(origins.toArray(new LatLng[0]))
                   .destinations(destinations.toArray(new LatLng[0]))
                   .mode(TravelMode.DRIVING);

           DistanceMatrix matrix = request.await();

           log.info("DistanceMatrixService::DistanceMatrix Completed successfully");
           context.shutdown();
           return matrix;
       } catch (ApiException | InterruptedException | IOException e) {
           log.error("DistanceMatrixService::Exception in getDistanceMatrix: {}", e.getMessage());
           throw new RuntimeException(e);
       }

   }

   public int[][]  createGraph(DistanceMatrix distanceMatrix){
       int[][] graph = new int[distanceMatrix.rows.length][distanceMatrix.rows.length];
       for (int i = 0; i < distanceMatrix.rows.length; i++) {
           DistanceMatrixRow row = distanceMatrix.rows[i];
           for (int j = 0; j < row.elements.length; j++) {
               DistanceMatrixElement element = row.elements[j];
               graph[i][j] = (int) element.distance.inMeters;
           }
       }
       return graph;
   }







//    DistanceMatrixRow[] rows = matrix.rows;
//    int[][] distanceGraph = new int[rows.length][rows.length];
//           for (int i = 0; i < rows.length; i++) {
//        DistanceMatrixRow row = rows[i];
//        for (int j = 0; j < row.elements.length; j++) {
//            DistanceMatrixElement element = row.elements[j];
//            distanceGraph[i][j] = (int) element.distance.inMeters;
//            System.out.println("Origin: " + origins.get(i) + ", Destination: " + destinations.get(j));
//            System.out.println("Distance: " + element.distance.inMeters + " meters");
//            System.out.println("Duration: " + element.duration.inSeconds + " seconds");
//        }
//    }


}
