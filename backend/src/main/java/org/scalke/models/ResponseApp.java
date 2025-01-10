package org.scalke.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseApp<T> {
    String message;
    private T data;
    private ErrorDetails error;
}
