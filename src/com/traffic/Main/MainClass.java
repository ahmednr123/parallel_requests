package com.traffic.Main;

import com.traffic.RequestThreads.GetMessages;
import com.traffic.RequestThreads.Login;
import com.traffic.RequestThreads.SendMessage;

public class MainClass {

    public static void main (String[] args) {
        Login.setURL("http://localhost:8080/ChatApp/login");
        Login.setCredentials("user5", "1234");

        System.out.println("JSESSION: " + Login.getSession());

        SendMessage request = new SendMessage(Login.getSession(), "28", "300 bleh synchronous messages");
        request.execute();

        /*GetMessages request = new GetMessages(Login.getSession(), "28", "2");
        request.execute();*/
    }
}