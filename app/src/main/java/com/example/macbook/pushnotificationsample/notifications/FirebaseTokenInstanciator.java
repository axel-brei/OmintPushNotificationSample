package com.example.macbook.pushnotificationsample.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import static android.content.ContentValues.TAG;

public class FirebaseTokenInstanciator extends FirebaseInstanceIdService {

    public static final String FIREBASE_TOKEN = "FIREBASE_TOKEN";

    public FirebaseTokenInstanciator() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putString(FIREBASE_TOKEN,refreshedToken).apply();
        // TODO get the new token to the user
        displayNewTokenNotification(this,refreshedToken);
    }

    private void sendTokenToServer(String token){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();

    }

    public static void displayNewTokenNotification(Context context,String newToken){
        Intent gmail = new Intent(Intent.ACTION_SEND);
        gmail.putExtra(Intent.EXTRA_SUBJECT, "Firebase Token");
        gmail.setType("plain/text");
        gmail.putExtra(Intent.EXTRA_TEXT, newToken);
        context.startActivity(gmail);
    }
}
