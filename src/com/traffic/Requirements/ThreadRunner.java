package com.traffic.Requirements;

import org.json.xjson.XJSONException;
import org.json.xjson.XJSONObject;

public class ThreadRunner implements Runnable {
    RequestRunner runner;
    HttpConfig config;

    public
    ThreadRunner (HttpConfig config, RequestRunner runner) {
        this.runner = runner;
        this.config = config;
    }

    public void run()
    {
        XJSONObject xobj = new XJSONObject();
        String resp = null;
        long startTime = System.currentTimeMillis();

        try {
            resp = config.httpClient.request(config.method, config.url, config.reqParameters);
        } catch (Exception e) {
            System.out.println ("Exception is caught");
        } finally {
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;

            try {
                xobj.put("timetaken", timeElapsed);
                xobj.put("started_at", startTime - runner.startTime);
                xobj.put("ended_at", endTime - runner.startTime);
                xobj.put("body", resp);
            } catch (XJSONException e) {
                e.printStackTrace();
            }

            runner.requests.add(xobj);

            System.out.println("Thread:["+Thread.currentThread().getId()+ "] " +
                    "Timetaken: " + timeElapsed + "ms, " +
                    "(Started at: " + (startTime- runner.startTime) + "ms " +
                    "Ended at: " + (endTime- runner.startTime) + "ms)\n" + resp + "\n");

            runner.output += "Thread:["+Thread.currentThread().getId()+ "] " +
                    "Timetaken: " + timeElapsed + "ms, " +
                    "(Started at: " + (startTime- runner.startTime) + "ms " +
                    "Ended at: " + (endTime- runner.startTime) + "ms)\n" + resp + "\n\n";

            runner.latch.countDown();
        }
    }
}
