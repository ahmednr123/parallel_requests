package com.traffic.RequestThreads;

import com.traffic.Util.SimpleHttp;
import org.json.xjson.XJSONObject;

import java.util.Map;

public class HttpConfig {
    public String url;
    public String method;
    public Map<String, String> reqParameters;
    public SimpleHttp httpClient;

    public
    HttpConfig () {
        this("http://localhost", "GET", null, null);
    }

    public
    HttpConfig (String url, String method) {
        this(url, method, null, null);
    }

    public
    HttpConfig (String url, String method, Map<String, String> reqParameters, SimpleHttp httpClient) {
        this.url = url;
        this.method = method;
        this.reqParameters = reqParameters;
        this.httpClient = httpClient;
    }
}
