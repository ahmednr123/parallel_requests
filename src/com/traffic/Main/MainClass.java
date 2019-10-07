package com.traffic.Main;

import com.traffic.RequestThreads.Login;
import com.traffic.RequestThreads.SendMessage;

public class MainClass {

    public static void main (String[] args) {
        Login.setURL("http://localhost:8080/ChatApp/login");
        Login.setCredentials("phonyBeachBoy", "1234");

        System.out.println("JSESSION: " + Login.getSession());

        SendMessage request = new SendMessage(Login.getSession(), "28", "more messages");
        request.execute();
    }
}