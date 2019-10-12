package com.traffic.RequestThreads;

import com.traffic.Requirements.HttpConfig;
import com.traffic.Requirements.RequestRunner;
import com.traffic.Requirements.RequestThread;
import com.traffic.Requirements.ThreadRunner;
import com.traffic.Util.SimpleHttp;

import java.util.HashMap;
import java.util.Map;

public class SendMessage implements RequestThread {

    private String chat_id;
    private String message;

    public
    SendMessage (String chat_id, String message) {
        this.chat_id = chat_id;
        this.message = message;
    }

    public void execute () {
        RequestRunner runner = new RequestRunner("SendMessageTest-300", 300);
        SimpleHttp httpClient = new SimpleHttp(new LoginSession());

        HttpConfig config = new HttpConfig();
        config.url = "http://localhost:8080/ChatApp/message";
        config.method = "POST";
        config.httpClient = httpClient;

        Map<String,String> parameters = new HashMap<>();
        parameters.put("chat_id", chat_id);
        parameters.put("message", message);

        config.reqParameters = parameters;
        runner.run(new ThreadRunner(config, runner));
    }
}
