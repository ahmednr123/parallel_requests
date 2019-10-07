package com.traffic.RequestThreads;

import com.traffic.Util.SimpleHttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login {

    static String cookie = null;
    static String username;
    static String password;
    static String url;

    private Login () { }

    public static
    void setURL (String url) {
        Login.url = url;
    }

    public static
    void setCredentials (String username, String password) {
        Login.username = username;
        Login.password = password;
    }

    public static String getSession () {
        if (cookie == null) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("username", username);
            parameters.put("password", password);
            try {
                cookie = SimpleHttp.request("POST", url, parameters);

                if (cookie == null)
                    throw new RuntimeException("Login error: Authentication failed!");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return cookie;
    }
}
