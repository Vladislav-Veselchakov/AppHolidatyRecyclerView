package com.vl.appholidatyrecyclerview;

import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetcherThread extends Thread {
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    private Handler mHandler;
    private String sAddress;
    private String urlString;

    public DataFetcherThread(Handler handler, String url) {
        this.mHandler = handler;
        this.urlString = url;
    }

    @Override
    public void run() {
        try {
//            URL url = new URL("https://" + sAddress);
            // +++ AntipovV 25.06.2026 16:22
//            URL url = new URL(urlString);
//
//            HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//            reader.close();
//
            // --- AntipovV 25.06.2026 16:22
            sb.append("{\"arrHolidays\" : [");
            sb.append("        {\"name\": \"holiday1\", \"date\": \"01-01-2222\"},");
            sb.append("        {\"name\": \"holiday2\", \"date\": \"02-01-2222\"},");
            sb.append("        {\"name\": \"holiday3\", \"date\": \"03-01-2222\"},");
            sb.append("        {\"name\": \"holiday4\", \"date\": \"04-01-2222\"},");
            sb.append("        {\"name\": \"holiday5\", \"date\": \"05-01-2222\"},");
            sb.append("        {\"name\": \"holiday6\", \"date\": \"06-01-2222\"},");
            sb.append("        {\"name\": \"holiday7\", \"date\": \"07-01-2222\"},");
            sb.append("        {\"name\": \"holiday8\", \"date\": \"08-01-2222\"},");
            sb.append("        {\"name\": \"holiday9\", \"date\": \"09-01-2222\"},");
            sb.append("        {\"name\": \"holiday10\", \"date\": \"10-01-2222\"},");
            sb.append("        {\"name\": \"holiday11\", \"date\": \"11-01-2222\"},");
            sb.append("        {\"name\": \"holiday12\", \"date\": \"121-01-2222\"},");
            sb.append("        {\"name\": \"holiday13\", \"date\": \"13-01-2222\"},");
            sb.append("        {\"name\": \"holiday14\", \"date\": \"14-01-2222\"},");
            sb.append("        {\"name\": \"holiday15\", \"date\": \"15-01-2222\"}");
            sb.append("    ]");
            sb.append("}");

            Message message = mHandler.obtainMessage(SUCCESS, sb.toString());
            mHandler.sendMessage(message);


        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(ERROR);
        }
    }
}
