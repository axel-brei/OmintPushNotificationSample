package com.example.macbook.pushnotificationsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

public class OmintNotificationManager {

    private static final String ADMIN_CHANNEL_ID = "ADMIN_CHANNEL";
    public static final String OMINT_NOTIFICATION_EXTRA = "OMINT_NOTIFICATION";


    private static final int NOTIFICATION_ACCEPT_ACTION = 0;
    private static final int NOTIFICATION_CANCEL_ACTION = 1;
    public static final String RESPUESTA_NOTIFICACION = "RESPUESTA_NOTIFICACION";

    private Context context;
    private NotificationManager notificationManager;

    public OmintNotificationManager(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public  void sendNotificationWithOMintMessage(RemoteMessage omintMessage){

            OmintNotification omintNotification = OmintNotification.getInstance(omintMessage);
            Intent intent = new Intent(context,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(OMINT_NOTIFICATION_EXTRA,omintNotification);
            intent.putExtra(OMINT_NOTIFICATION_EXTRA,omintNotification);
            PendingIntent mainPendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, ADMIN_CHANNEL_ID)
                    .setCustomBigContentView(setNotifContent(omintNotification))
                    .setSmallIcon(R.drawable.artcalado)
                    .setColor(context.getResources().getColor(R.color.omintColor))
                    .setColorized(true)
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .addAction(getActionList(omintNotification).get(NOTIFICATION_ACCEPT_ACTION))
                    .addAction(getActionList(omintNotification).get(NOTIFICATION_CANCEL_ACTION))
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

            notificationBuilder.setContentIntent(mainPendingIntent);



        if (notificationManager != null) {
            notificationManager.notify(omintNotification.getNotifID(), notificationBuilder.build());
        }
    }

    private List<NotificationCompat.Action> getActionList(OmintNotification notification){
        List<NotificationCompat.Action> actionList = new ArrayList<>(2);

        Intent acceptIntent = new Intent(context,AcceptActivity.class);
        acceptIntent.putExtra(OMINT_NOTIFICATION_EXTRA,notification);
        NotificationCompat.Action acceptAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_done_black_24dp,
                "Aceptar"
                ,PendingIntent.getActivity(context,0,acceptIntent,PendingIntent.FLAG_ONE_SHOT))
                .build();
        actionList.add(NOTIFICATION_ACCEPT_ACTION,acceptAction);

        Intent deniedIntent = new Intent(context,DeniedActivity.class);
        deniedIntent.putExtra(OMINT_NOTIFICATION_EXTRA,notification);
        NotificationCompat.Action cancelAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_clear_black_24dp,
                "Cancelar",
                PendingIntent.getActivity(context,0,deniedIntent,PendingIntent.FLAG_ONE_SHOT))
                .build();
        actionList.add(NOTIFICATION_CANCEL_ACTION,cancelAction);

        return actionList;
    }

    private  RemoteViews setNotifContent(OmintNotification notifContent){
        RemoteViews normalNotif = new RemoteViews(context.getPackageName(),R.layout.notification_layout_normal);
        normalNotif.setTextViewText(R.id.omintNotificationCentroMedico,notifContent.getCentroMedico());
        normalNotif.setTextViewText(R.id.omintNotificationHorario,notifContent.getFechaTurno() + " - " + notifContent.getHoraTurno());
        return normalNotif;
    }


    public static void handleOmintNotificationIntent(Context context,@NonNull Intent intent){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        OmintNotification notification = (OmintNotification) intent.getSerializableExtra(OMINT_NOTIFICATION_EXTRA);
        if (notification != null){
            manager.cancel(notification.getNotifID());
        }
    }
}
