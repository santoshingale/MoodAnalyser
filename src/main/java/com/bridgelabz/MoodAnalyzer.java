package com.bridgelabz;

import java.util.Objects;

public class MoodAnalyzer {
    private String message ;

    public MoodAnalyzer(String message) {
        this.message = message;
    }
    public MoodAnalyzer() {}

    public String analyser() throws MoodAnalyzeException {
        try {
            if( message.length() == 0 ) {
                throw new MoodAnalyzeException(MoodAnalyzeException.ExceptionType.ENTERED_EMPTY,"invalid input");
            }

            if (message.contains("sad") || message.contains("Sad")) {
                return "Sad";
            } else
                return "Happy";
        }
        catch (NullPointerException e) {
            throw new MoodAnalyzeException(MoodAnalyzeException.ExceptionType.ENTERED_NULL,"invalid input");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoodAnalyzer that = (MoodAnalyzer) o;
        return Objects.equals(message,that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }


}
