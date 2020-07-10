
package com.example.mab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GetActivity extends AppCompatActivity {

    TextView tvText;
    String link = "http://dotplays.com/wp-json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        tvText = findViewById(R.id.tvData);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetLoader getLoader = new GetLoader(tvText);
                getLoader.execute(link);
            }
        });

    }
}