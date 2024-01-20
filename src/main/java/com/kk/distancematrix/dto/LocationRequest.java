package com.kk.distancematrix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private Integer id;
    private Double latitude;
    private Double longitude;


}
