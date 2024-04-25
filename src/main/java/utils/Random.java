package utils;

import java.util.stream.IntStream;

public class Random {

    private static final java.util.Random RANDOM = new java.util.Random();

    public static String generateRandomIP(){
        StringBuilder randomIP = new StringBuilder();
        for(int i=0; i<3; i++){
            randomIP.append(RANDOM.nextInt(256));
            randomIP.append(".");
        }
        randomIP.append(RANDOM.nextInt(256));
        return randomIP.toString();
    }

    public static java.util.Random getRandom(){
        return RANDOM;
    }
}
