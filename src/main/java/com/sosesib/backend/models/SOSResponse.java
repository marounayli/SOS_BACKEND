package com.sosesib.backend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SOSResponse<T> {

    private String type;
    private String message;
    private T payload;

    public SOSResponse(String type, String message, T payload){
        this.type=type;
        this.message=message;
        this.payload=payload;
    }

}
