package com.traffic.Main;

import com.traffic.RequestThreads.GetMessages;
import com.traffic.RequestThreads.SendMessage;
import com.traffic.Requirements.RequestManager;
import com.traffic.Requirements.RequestThread;

import java.util.ArrayList;

public class MainClass {

    public static void main (String[] args) {
        ArrayList<RequestThread> requests = new ArrayList<>();
        requests.add(new GetMessages("28", "2"));
        requests.add(new SendMessage("28", "this is a message"));

        RequestManager manager = new RequestManager(requests);
        manager.execute();
    }
}