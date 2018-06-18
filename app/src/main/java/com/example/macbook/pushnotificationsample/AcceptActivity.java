package com.example.macbook.pushnotificationsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AcceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        OmintNotification notification = (OmintNotification) intent.getSerializableExtra(OmintNotificationManager.OMINT_NOTIFICATION_EXTRA);
        if (notification != null){
            OmintNotificationManager.handleOmintNotificationIntent(this, intent);
            TextView textView = findViewById(R.id.acceptTextView);
            textView.setText("Datos: " + notification.toString());
        }

    }

}
