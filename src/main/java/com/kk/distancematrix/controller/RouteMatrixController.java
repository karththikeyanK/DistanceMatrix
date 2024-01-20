package com.kk.distancematrix.controller;


import com.kk.distancematrix.dto.LocationRequest;
import com.kk.distancematrix.dtoMapper.LocationMapper;
import com.kk.distancematrix.facade.LocationFacade;
import com.kk.distancematrix.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/google-route-matrix")
public class RouteMatrixController {

    private final LocationMapper locationMapper;
    private final LocationFacade locationFacade;

    @PostMapping("/getDistanceMatrix")
    public ResponseEntity<ApiResponse> getDistanceMatrix(@RequestBody List<LocationRequest> locationRequestList) {
        return ResponseEntity.ok(new ApiResponse<>(ApiResponse.SUCCESS, "Success", locationFacade.getDistanceMatrix(locationRequestList)));
    }

    @PostMapping("/getResponse")
    public ResponseEntity<ApiResponse> getEfficientPath(@RequestBody List<LocationRequest> locationRequestList) {
        return ResponseEntity.ok(new ApiResponse<>(ApiResponse.SUCCESS, "Success", locationFacade.getEfficientPath(locationRequestList)));
    }
}
