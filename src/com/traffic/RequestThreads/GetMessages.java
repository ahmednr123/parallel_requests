package com.traffic.RequestThreads;

import java.util.HashMap;
import java.util.Map;

public class GetMessages implements RequestThread {
    private String cookie;
    private String chat_id;
    private String type;

    public
    GetMessages (String cookie, String chat_id, String type) {
        this.cookie = cookie;
        this.chat_id = chat_id;
        this.type = type;
    }

    public void execute () {
        RequestRunner runner = new RequestRunner("GetMessagesTest-300", 300);
        HttpConfig config = new HttpConfig();
        config.url = "http://localhost:8080/ChatApp/messages";
        config.method = "GET";
        config.cookie = cookie;

        Map<String,String> parameters = new HashMap<>();
        parameters.put("chat_id", chat_id);
        parameters.put("type", type);

        config.reqParameters = parameters;
        runner.run(new ThreadRunner(config, runner));
    }
}
