package com.traffic.Main;

import com.traffic.RequestThreads.GetMessages;
import com.traffic.RequestThreads.SendMessage;

public class MainClass {

    public static void main (String[] args) {
        GetMessages request = new GetMessages("28", "2");
        request.execute();
    }
}