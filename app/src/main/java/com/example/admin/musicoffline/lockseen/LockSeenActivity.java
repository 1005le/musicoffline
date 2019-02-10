package com.example.admin.musicoffline.lockseen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.musicoffline.R;

public class LockSeenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_seen);

        Intent intent = new Intent(getApplicationContext(), MediaPlayerService.class);
        intent.setAction(MediaPlayerService.ACTION_PLAY);
        startService(intent);
    }
}
