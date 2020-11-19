package com.sosesib.backend.models.response.generators;

import com.sosesib.backend.models.SOSResponse;
import org.springframework.stereotype.Component;

import static com.sosesib.backend.models.response.generators.SOSResponseType.SUCCESS;

@Component
public class SOSResponseGenerator {
    private final static String QuerySuccessMessage = "Query Successful";

    public static <T> SOSResponse<T> GenerateSuccessfulQueryResponse(T payload){
        return new SOSResponse<T>(SUCCESS,QuerySuccessMessage,payload);
    }
}
