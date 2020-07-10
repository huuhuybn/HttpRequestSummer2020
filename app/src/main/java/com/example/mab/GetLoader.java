package com.example.mab;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetLoader extends AsyncTask<String, Integer, String> {

    TextView tvText;

    public GetLoader(TextView tvText) {
        this.tvText = tvText;
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        String data = "";
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                data += scanner.nextLine();
            }
            scanner.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            Log.e("Â", e.getMessage());
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null)
            tvText.setText(s);
    }
}
