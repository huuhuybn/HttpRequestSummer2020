package com.example.mab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {


    EditText edtBody, edtTitle, edtId;
    String link = "https://jsonplaceholder.typicode.com/posts";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        edtBody = findViewById(R.id.edtBody);
        edtTitle = findViewById(R.id.edtTitle);
        edtId = findViewById(R.id.edtId);
        textView = findViewById(R.id.textView);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String body = edtBody.getText().toString().trim();
                String title = edtTitle.getText().toString().trim();


                PostLoader postLoader = new PostLoader(id, body, title);
                postLoader.setTextView(textView);
                postLoader.execute(link);



            }
        });

    }


}