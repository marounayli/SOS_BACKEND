package com.sosesib.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Aggregation<X> {
    private LocalDateTime lowDate;
    private LocalDateTime highDate;
    private X aggregationValue;
}
