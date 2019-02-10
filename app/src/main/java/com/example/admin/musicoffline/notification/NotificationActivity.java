package com.example.admin.musicoffline.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.musicoffline.R;

public class NotificationActivity extends AppCompatActivity {

    Button openActivityBtn , openBigContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        openActivityBtn = findViewById(R.id.btnOpenActicity);
        openBigContent= findViewById(R.id.btnBigContent);

        openActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationGenerator.openActivityNotification(getApplicationContext());

            }
        });
        openBigContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               NotificationGenerator.customBigNotification(getApplicationContext());
            }
        });
    }
}
