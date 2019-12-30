package com.bridgelabz;

public class MoodAnalyzeException extends RuntimeException {

  public enum ExceptionType {
        ENTERED_EMPTY,ENTERED_NULL,CLASS_NOT_FOUND,METHOD_NOT_FOUND,
    }
    public ExceptionType type;

    public MoodAnalyzeException(ExceptionType type,String string) {
        super(string);
        this.type=type;
    }
}
