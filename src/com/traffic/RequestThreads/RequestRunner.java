package com.traffic.RequestThreads;

import org.json.xjson.XJSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestRunner {

    public int threads = 2;
    public long startTime;
    public String output = "";
    public int threads_completed = 0;
    public static ArrayList<XJSONObject> requests = new ArrayList<>();
    public CountDownLatch latch = null;

    private String test_name;

    public
    RequestRunner(String test_name, int threads) {
        this.test_name = test_name;
        this.threads = threads;
    }

    public
    void run (Runnable requestThread) {
        startTime = System.currentTimeMillis();
        output += "Starting Test\n\n";
        System.out.println("Starting Test\n");

        latch = new CountDownLatch(threads);

        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < threads; i++) {
            service.execute(requestThread);
        }

        service.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        output += "[Total time: " + (System.currentTimeMillis() - this.startTime + "ms]");
        saveLogFile();
    }

    private void saveLogFile () {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String file_name = test_name + "-" + formatter.format(date);

        try {
            FileWriter fw = new FileWriter("./tests/" + file_name + ".txt");
            fw.write(this.output);
            fw.close();
            System.out.println("Written to file: " + file_name + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}