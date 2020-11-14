package com.sosesib.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Aggregation {
    private LocalDateTime lowDate;
    private LocalDateTime highDate;
    private Double aggregationValue;
}
