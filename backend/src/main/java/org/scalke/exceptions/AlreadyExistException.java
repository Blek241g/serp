package org.scalke.exceptions;

public class AlreadyExistException extends Exception{
    public AlreadyExistException(String entityName, String field, String value){
        super(String.format("%s with %s => %s already exist!" , entityName, value, field));
    }
}
