package com.sosesib.backend.models.responses;

import com.sosesib.backend.models.response.generators.SOSResponseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SOSResponse<T> {

    private SOSResponseType type;
    private String message;
    private T payload;

    public SOSResponse(SOSResponseType type, String message, T payload){
        this.type=type;
        this.message=message;
        this.payload=payload;
    }

}
