package com.example.trainingtrackr.utils;

public class InputParser {


    public static int parseInt(String s) {
        if(s.isEmpty() || s.matches("\\s+"))
            return 0;

        return Integer.parseInt(s.trim());
    }
}
