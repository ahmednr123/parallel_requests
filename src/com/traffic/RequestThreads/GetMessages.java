package com.traffic.RequestThreads;

import com.traffic.Requirements.HttpConfig;
import com.traffic.Requirements.RequestRunner;
import com.traffic.Requirements.RequestThread;
import com.traffic.Requirements.ThreadRunner;
import com.traffic.Util.SimpleHttp;

import java.util.HashMap;
import java.util.Map;

public class GetMessages implements RequestThread {
    private String chat_id;
    private String type;

    public
    GetMessages (String chat_id, String type) {
        this.chat_id = chat_id;
        this.type = type;
    }

    public void execute () {
        RequestRunner runner = new RequestRunner("GetMessagesTest-300", 1);
        SimpleHttp httpClient = new SimpleHttp(new LoginSession());

        HttpConfig config = new HttpConfig();
        config.url = "http://localhost:8080/ChatApp/messages";
        config.method = "GET";
        config.httpClient = httpClient;

        Map<String,String> parameters = new HashMap<>();
        parameters.put("chat_id", chat_id);
        parameters.put("type", type);

        config.reqParameters = parameters;
        runner.run(new ThreadRunner(config, runner));
    }
}
