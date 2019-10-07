package com.traffic.Main;

import com.traffic.RequestThreads.Login;
import com.traffic.RequestThreads.SendMessage;

public class MainClass {

    public static void main (String[] args) {
        Login.setURL("http://localhost:8080/ChatApp/login");
        Login.setCredentials("ahmed", "1234");

        System.out.println("JSESSION: " + Login.getSession());

        SendMessage request = new SendMessage(Login.getSession(), "28", "this is a message from thread");
        request.execute();
    }
}