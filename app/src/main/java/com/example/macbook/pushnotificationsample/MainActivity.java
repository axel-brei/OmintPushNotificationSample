package com.example.macbook.pushnotificationsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.macbook.pushnotificationsample.notifications.FirebaseTokenInstanciator;
import com.example.macbook.pushnotificationsample.notifications.NotificationSingupService;
import com.google.firebase.iid.FirebaseInstanceId;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String token = FirebaseInstanceId.getInstance().getToken();
        TextView mTextView = findViewById(R.id.mTextView);
        Button button = findViewById(R.id.mainButton);
        mTextView.setText(token);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseTokenInstanciator.displayNewTokenNotification(MainActivity.this,token);
            }
        });
        startService(new Intent(this, NotificationSingupService.class));
    }


}

