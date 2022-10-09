package com.wang.simplecat;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class SimpleCat {
    
    public static void run(String[] args) throws Exception {
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.start();
    }
}
