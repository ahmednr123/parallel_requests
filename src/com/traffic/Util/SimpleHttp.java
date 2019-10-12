package com.traffic.Util;

import com.traffic.RequestThreads.LoginStrategy;
import org.json.JSONException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class SimpleHttp {
    LoginStrategy loginStrategy;
    String cookie = null;

    public SimpleHttp () { }

    public SimpleHttp (LoginStrategy loginStrategy) {
        if (loginStrategy != null) {
            this.loginStrategy = loginStrategy;
            try {
                request(loginStrategy.getMethod(), loginStrategy.getUrl(), loginStrategy.getParameters());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public
    String request (String method, String URL)
            throws IOException, JSONException
    {
        return request(method, URL, null);
    }

    // Respond with structured data maybe a class
    // with responseCode, extraData(depening upon the responseCode) and body
    public
    String request (String method, String URL, Map<String, String> parameters)
            throws IOException, JSONException
    {
        String str, jsonString = "";

        HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(5000);
        connection.setInstanceFollowRedirects(false);

        if (cookie != null)
            connection.setRequestProperty("Cookie", cookie);

        if (parameters != null) {
            //Send data with request
            connection.setDoOutput(true);

            String reqString = getParameterQuery(parameters);
            System.out.println(reqString);

            OutputStream request = connection.getOutputStream();
            request.write(reqString.getBytes("UTF-8"));
            request.close();
        }

        if (loginStrategy != null && cookie == null) {
            cookie = loginStrategy.saveSession(connection);
        }

        InputStream response;

        try {
            response = connection.getInputStream();
        } catch (Exception e) {
            response = connection.getErrorStream();
        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(response));
        while ((str = bf.readLine()) != null){
            jsonString += str;
        };
        bf.close();

        return jsonString;
    }

    static String getParameterQuery (Map<String, String> parameters) {
        String query = "";

        try {
            StringJoiner sj = new StringJoiner("&");
            for(Map.Entry<String,String> entry : parameters.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));

            query = sj.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return query;
    }
}
