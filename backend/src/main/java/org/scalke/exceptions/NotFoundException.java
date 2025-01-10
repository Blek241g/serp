package org.scalke.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String entityName, String field, String value){
        super(String.format("%s with %s => %s not found!" , entityName, value, field));
    }
}
