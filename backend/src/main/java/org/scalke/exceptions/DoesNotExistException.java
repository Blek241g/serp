package org.scalke.exceptions;

public class DoesNotExistException extends Exception{
    public DoesNotExistException(String entityName, String field, String value){
        super(String.format("%s with %s => %s does not exist!" , entityName, value, field));
    }
}
