package com.traffic.RequestThreads;

import java.util.HashMap;
import java.util.Map;

public class SendMessage implements RequestThread {

    private String cookie;
    private String chat_id;
    private String message;

    public
    SendMessage (String cookie, String chat_id, String message) {
        this.cookie = cookie;
        this.chat_id = chat_id;
        this.message = message;
    }

    public void execute () {
        RequestRunner runner = new RequestRunner("SendMessageTest", 1);
        HttpConfig config = new HttpConfig();
        config.url = "http://localhost:8080/ChatApp/message";
        config.method = "POST";
        config.cookie = cookie;

        Map<String,String> parameters = new HashMap<>();
        parameters.put("chat_id", chat_id);
        parameters.put("message", message);

        config.reqParameters = parameters;
        runner.run(new ThreadRunner(config, runner));
    }
}
