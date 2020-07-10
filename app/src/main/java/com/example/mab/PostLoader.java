package com.example.mab;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PostLoader extends AsyncTask<String, Integer, String> {

    String id, body, title;

    public PostLoader(String id, String body, String title) {
        this.id = id;
        this.body = body;
        this.title = title;
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        String data = "";
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //Content-Type: application/x-www-form-urlencoded
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            // khoi tao param
            StringBuilder params = new StringBuilder();

            params.append("title");
            params.append("=");
            params.append(title);

            params.append("&");
            params.append("body");
            params.append("=");
            params.append(body);

            params.append("&");
            params.append("userId");
            params.append("=");
            params.append(id);

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(os, "UTF-8"));

            // dua param vao body cua request
            writer.append(params);

            // giai phong bo nho
            writer.flush();
            // ket thuc truyen du lieu vao output
            writer.close();
            os.close();

            // lay du lieu tra ve
            StringBuilder response = new StringBuilder();

            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) response.append(scanner.nextLine());

            
            return response.toString();

        } catch (Exception e) {
            Log.e("ABC", e.getMessage());
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) textView.setText(s);
    }

    TextView textView;

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
