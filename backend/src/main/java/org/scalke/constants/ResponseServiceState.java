package org.scalke.constants;

import lombok.Getter;

@Getter
public enum ResponseServiceState {
    SUCCESS("success"), BAD("bad"), FAILURE("failure"), ERROR("error");

    private final String value;

    private ResponseServiceState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
