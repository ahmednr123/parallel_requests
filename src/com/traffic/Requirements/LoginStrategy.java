package com.traffic.Requirements;

import java.net.HttpURLConnection;
import java.util.Map;

public interface LoginStrategy {
    public String getUrl ();
    public String getMethod();
    public Map<String, String> getParameters ();
    public String saveSession (HttpURLConnection connection);
}
