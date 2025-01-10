package org.scalke.helpers;

import org.scalke.models.ErrorDetails;
import org.scalke.models.ResponseApp;

public class ErrorHelper {
    public static ResponseApp<?> setErrorMessage(String message){
        return ResponseApp.builder()
                .error(ErrorDetails.builder()
                        .message(message)
                        .build())
                .build();
    }
}
