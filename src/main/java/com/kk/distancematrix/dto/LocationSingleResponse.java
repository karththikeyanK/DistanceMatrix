package com.kk.distancematrix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationSingleResponse {
  private int stopSequenceNumber;
  private int startId;
  private int destinationId;
  private int distance;
  private int duration;
}
