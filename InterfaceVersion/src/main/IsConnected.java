package main;

import java.io.IOException;

public  class IsConnected {
    public  static  boolean IsConnecting() throws InterruptedException, IOException {

        Process p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 www.google.com");
        int returnVal = p1.waitFor();

 return  returnVal==0 ? true : false;
    }



}
