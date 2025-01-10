package org.scalke.models;

import lombok.*;

import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ErrorDetails {
    private Map<String, Object> playload;
    private String message;
//    private String title;
//    private Date timestamp;
}
