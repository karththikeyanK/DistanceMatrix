package com.kk.distancematrix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private List<LocationSingleResponse> locationResponseList;
    private List<Integer> path;
    private int totalDistance;
    private int totalTime;

}
