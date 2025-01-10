package org.scalke.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.scalke.constants.ResponseServiceState;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseServiceApp {
    private ResponseServiceState state;
    private String message;
}
