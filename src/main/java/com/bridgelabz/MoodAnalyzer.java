package com.bridgelabz;

public class MoodAnalyzer {
    private static String message;

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
}
