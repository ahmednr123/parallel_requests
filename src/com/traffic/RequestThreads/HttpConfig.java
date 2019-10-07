package com.traffic.RequestThreads;

import org.json.xjson.XJSONObject;

import java.util.Map;

public class HttpConfig {
    public String url;
    public String method;
    public Map<String, String> reqParameters;
    public String cookie;

    public
    HttpConfig () {
        this("http://localhost", "GET", null, null);
    }

    public
    HttpConfig (String url, String method) {
        this(url, method, null, null);
    }

    public
    HttpConfig (String url, String method, Map<String, String> reqParameters, String cookie) {
        this.url = url;
        this.method = method;
        this.reqParameters = reqParameters;
        this.cookie = cookie;
    }
}
