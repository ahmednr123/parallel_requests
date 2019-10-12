package com.traffic.Requirements;

import org.omg.CORBA.Request;

import java.util.ArrayList;

public class RequestManager {
    ArrayList<RequestThread> requests = new ArrayList<>();

    public RequestManager (ArrayList<RequestThread> requests) {
        this.requests = requests;
    }

    public void execute () {
        for (RequestThread request : requests) {
            request.execute();
        }
    }
}
