package com.traffic.Util;

import org.json.JSONException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class SimpleHttp {
    public
    static String request (String method, String URL)
            throws IOException, JSONException
    {
        return request(method, URL, null, null);
    }

    public
    static String request (String method, String URL, Map<String, String> parameters)
            throws IOException, JSONException
    {
        return request(method, URL, parameters, null);
    }

    public
    static String request (String method, String URL, Map<String, String> parameters, String Cookie)
            throws IOException, JSONException
    {
        String str, jsonString = "";

        HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(5000);
        connection.setInstanceFollowRedirects(false);

        if (Cookie != null)
            connection.setRequestProperty("Cookie", Cookie);

        if (parameters != null) {
            //Send data with request
            connection.setDoOutput(true);

            String reqString = getParameterQuery(parameters);
            System.out.println(reqString);

            OutputStream request = connection.getOutputStream();
            request.write(reqString.getBytes("UTF-8"));
            request.close();
        }

        // JUST FOR LOGIN.
        // NOT REUSABLE FOR ALL APPLICATIONS.
        if (connection.getResponseCode() == 302) {
            if ("/ChatApp/chat_app.html".equals(connection.getHeaderField("Location"))) {
                return connection.getHeaderField("Set-Cookie").split(";")[0];
            } else {
                return null;
            }
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
