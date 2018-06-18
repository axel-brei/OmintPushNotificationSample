package com.example.macbook.pushnotificationsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DeniedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denied);
        Intent intent = getIntent();
        OmintNotification notification = (OmintNotification) intent.getSerializableExtra(OmintNotificationManager.OMINT_NOTIFICATION_EXTRA);
        if (notification != null){
            OmintNotificationManager.handleOmintNotificationIntent(this, intent);
            TextView textView = findViewById(R.id.deniedTextView);
            textView.setText(notification.toString());
        }
    }
}
