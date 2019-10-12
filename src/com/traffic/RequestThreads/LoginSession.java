package com.traffic.RequestThreads;

import com.traffic.Requirements.LoginStrategy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class LoginSession implements LoginStrategy {
    String cookie = null;
    String url = "http://localhost:8080/ChatApp/login";
    String method = "POST";

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public Map<String, String> getParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", "ahmed");
        parameters.put("password", "1234");
        return parameters;
    }

    @Override
    public String saveSession(HttpURLConnection connection) {
        try {
            if (connection.getResponseCode() == 302) {
                if ("/ChatApp/chat_app.html".equals(connection.getHeaderField("Location"))) {
                    cookie = connection.getHeaderField("Set-Cookie").split(";")[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cookie;
    }
}
